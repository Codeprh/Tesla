#!/bin/sh
set -e
##集群搭建--部署高可用 kube-apiserver 集群##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.创建 kubernetes 证书和私钥
function create_kuber(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > $K8S_WORK_DIR/kubernetes-csr.json <<EOF
{
  "CN": "kubernetes",
  "hosts": [
    "127.0.0.1",
    ${APISERVER_IPS},
    "${CLUSTER_KUBERNETES_SVC_IP}",
    "kubernetes",
    "kubernetes.default",
    "kubernetes.default.svc",
    "kubernetes.default.svc.cluster",
    "kubernetes.default.svc.cluster.local."
  ],
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "Guangzhou",
      "L": "Guangzhou",
      "O": "k8s",
      "OU": "System"
    }
  ]
}
EOF

	#生成证书和私钥：
	cfssl gencert -ca=$K8S_WORK_DIR/ca.pem \
	  -ca-key=$K8S_WORK_DIR/ca-key.pem \
	  -config=$K8S_WORK_DIR/ca-config.json \
	  -profile=kubernetes kubernetes-csr.json | cfssljson -bare kubernetes
	echo "创建的证书如下："
	ls kubernetes*pem

	#将生成的证书和私钥文件拷贝到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p $K8S_CERT_DIR"
		scp -P $SSH_PORT kubernetes*.pem root@${node_ip}:$K8S_CERT_DIR/
	  done
}
  
  
###2.创建加密配置文件
function create_pwd_config(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > encryption-config.yaml <<EOF
kind: EncryptionConfig
apiVersion: v1
resources:
  - resources:
      - secrets
    providers:
      - aescbc:
          keys:
            - name: key1
              secret: ${ENCRYPTION_KEY}
      - identity: {}
EOF

	#将加密配置文件拷贝到 master 节点的 $K8S_CERT_PARENT_DIR 目录下：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT encryption-config.yaml root@${node_ip}:$K8S_CERT_PARENT_DIR/
	  done
}
  
  
###3.创建审计策略文件  
function create_audit_policy(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > audit-policy.yaml <<EOF
apiVersion: audit.k8s.io/v1beta1
kind: Policy
rules:
  # The following requests were manually identified as high-volume and low-risk, so drop them.
  - level: None
    resources:
      - group: ""
        resources:
          - endpoints
          - services
          - services/status
    users:
      - 'system:kube-proxy'
    verbs:
      - watch

  - level: None
    resources:
      - group: ""
        resources:
          - nodes
          - nodes/status
    userGroups:
      - 'system:nodes'
    verbs:
      - get

  - level: None
    namespaces:
      - kube-system
    resources:
      - group: ""
        resources:
          - endpoints
    users:
      - 'system:kube-controller-manager'
      - 'system:kube-scheduler'
      - 'system:serviceaccount:kube-system:endpoint-controller'
    verbs:
      - get
      - update

  - level: None
    resources:
      - group: ""
        resources:
          - namespaces
          - namespaces/status
          - namespaces/finalize
    users:
      - 'system:apiserver'
    verbs:
      - get

  # Don't log HPA fetching metrics.
  - level: None
    resources:
      - group: metrics.k8s.io
    users:
      - 'system:kube-controller-manager'
    verbs:
      - get
      - list

  # Don't log these read-only URLs.
  - level: None
    nonResourceURLs:
      - '/healthz*'
      - /version
      - '/swagger*'

  # Don't log events requests.
  - level: None
    resources:
      - group: ""
        resources:
          - events

  # node and pod status calls from nodes are high-volume and can be large, don't log responses for expected updates from nodes
  - level: Request
    omitStages:
      - RequestReceived
    resources:
      - group: ""
        resources:
          - nodes/status
          - pods/status
    users:
      - kubelet
      - 'system:node-problem-detector'
      - 'system:serviceaccount:kube-system:node-problem-detector'
    verbs:
      - update
      - patch

  - level: Request
    omitStages:
      - RequestReceived
    resources:
      - group: ""
        resources:
          - nodes/status
          - pods/status
    userGroups:
      - 'system:nodes'
    verbs:
      - update
      - patch

  # deletecollection calls can be large, don't log responses for expected namespace deletions
  - level: Request
    omitStages:
      - RequestReceived
    users:
      - 'system:serviceaccount:kube-system:namespace-controller'
    verbs:
      - deletecollection

  # Secrets, ConfigMaps, and TokenReviews can contain sensitive & binary data,
  # so only log at the Metadata level.
  - level: Metadata
    omitStages:
      - RequestReceived
    resources:
      - group: ""
        resources:
          - secrets
          - configmaps
      - group: authentication.k8s.io
        resources:
          - tokenreviews
  # Get repsonses can be large; skip them.
  - level: Request
    omitStages:
      - RequestReceived
    resources:
      - group: ""
      - group: admissionregistration.k8s.io
      - group: apiextensions.k8s.io
      - group: apiregistration.k8s.io
      - group: apps
      - group: authentication.k8s.io
      - group: authorization.k8s.io
      - group: autoscaling
      - group: batch
      - group: certificates.k8s.io
      - group: extensions
      - group: metrics.k8s.io
      - group: networking.k8s.io
      - group: policy
      - group: rbac.authorization.k8s.io
      - group: scheduling.k8s.io
      - group: settings.k8s.io
      - group: storage.k8s.io
    verbs:
      - get
      - list
      - watch

  # Default level for known APIs
  - level: RequestResponse
    omitStages:
      - RequestReceived
    resources:
      - group: ""
      - group: admissionregistration.k8s.io
      - group: apiextensions.k8s.io
      - group: apiregistration.k8s.io
      - group: apps
      - group: authentication.k8s.io
      - group: authorization.k8s.io
      - group: autoscaling
      - group: batch
      - group: certificates.k8s.io
      - group: extensions
      - group: metrics.k8s.io
      - group: networking.k8s.io
      - group: policy
      - group: rbac.authorization.k8s.io
      - group: scheduling.k8s.io
      - group: settings.k8s.io
      - group: storage.k8s.io

  # Default level for all other requests.
  - level: Metadata
    omitStages:
      - RequestReceived
EOF

	#分发审计策略文件：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT audit-policy.yaml root@${node_ip}:$K8S_CERT_PARENT_DIR/audit-policy.yaml
	  done
}
  
###4.创建后续访问 metrics-server 使用的证书
function create_metrics_server_cert(){

	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	#创建证书签名请求:
	cat > proxy-client-csr.json <<EOF
{
  "CN": "aggregator",
  "hosts": [],
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "Guangzhou",
      "L": "Guangzhou",
      "O": "k8s",
      "OU": "System"
    }
  ]
}
EOF

	#生成证书和私钥：
	cfssl gencert -ca=$K8S_CERT_DIR/ca.pem \
	  -ca-key=$K8S_CERT_DIR/ca-key.pem  \
	  -config=$K8S_CERT_DIR/ca-config.json  \
	  -profile=kubernetes proxy-client-csr.json | cfssljson -bare proxy-client
	ls proxy-client*.pem

	#将生成的证书和私钥文件拷贝到所有 master 节点
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT proxy-client*.pem root@${node_ip}:$K8S_CERT_DIR/
	  done
}

###5.创建 kube-apiserver systemd unit 模板文件
function create_kube_apiserver_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > kube-apiserver.service.template <<EOF
[Unit]
Description=Kubernetes API Server
Documentation=https://github.com/GoogleCloudPlatform/kubernetes
After=network.target

[Service]
WorkingDirectory=${K8S_DIR}/kube-apiserver
ExecStart=$K8S_BIN_DIR/kube-apiserver \\
  --advertise-address=##NODE_IP## \\
  --default-not-ready-toleration-seconds=360 \\
  --default-unreachable-toleration-seconds=360 \\
  --feature-gates=DynamicAuditing=true \\
  --max-mutating-requests-inflight=2000 \\
  --max-requests-inflight=4000 \\
  --default-watch-cache-size=200 \\
  --delete-collection-workers=2 \\
  --encryption-provider-config=$K8S_CERT_PARENT_DIR/encryption-config.yaml \\
  --etcd-cafile=$K8S_CERT_DIR/ca.pem \\
  --etcd-certfile=$K8S_CERT_DIR/kubernetes.pem \\
  --etcd-keyfile=$K8S_CERT_DIR/kubernetes-key.pem \\
  --etcd-servers=${ETCD_ENDPOINTS} \\
  --bind-address=##NODE_IP## \\
  --secure-port=6443 \\
  --tls-cert-file=$K8S_CERT_DIR/kubernetes.pem \\
  --tls-private-key-file=$K8S_CERT_DIR/kubernetes-key.pem \\
  --insecure-port=0 \\
  --audit-dynamic-configuration \\
  --audit-log-maxage=15 \\
  --audit-log-maxbackup=3 \\
  --audit-log-maxsize=100 \\
  --audit-log-truncate-enabled \\
  --audit-log-path=${K8S_DIR}/kube-apiserver/audit.log \\
  --audit-policy-file=$K8S_CERT_PARENT_DIR/audit-policy.yaml \\
  --profiling \\
  --anonymous-auth=false \\
  --client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --enable-bootstrap-token-auth \\
  --requestheader-allowed-names="aggregator" \\
  --requestheader-client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --requestheader-extra-headers-prefix="X-Remote-Extra-" \\
  --requestheader-group-headers=X-Remote-Group \\
  --requestheader-username-headers=X-Remote-User \\
  --service-account-key-file=$K8S_CERT_DIR/ca.pem \\
  --authorization-mode=Node,RBAC \\
  --runtime-config=api/all=true,settings.k8s.io/v1alpha1=true \\
  --enable-admission-plugins=NodeRestriction,PodPreset \\
  --allow-privileged=true \\
  --apiserver-count=3 \\
  --event-ttl=168h \\
  --kubelet-certificate-authority=$K8S_CERT_DIR/ca.pem \\
  --kubelet-client-certificate=$K8S_CERT_DIR/kubernetes.pem \\
  --kubelet-client-key=$K8S_CERT_DIR/kubernetes-key.pem \\
  --kubelet-https=true \\
  --kubelet-timeout=10s \\
  --proxy-client-cert-file=$K8S_CERT_DIR/proxy-client.pem \\
  --proxy-client-key-file=$K8S_CERT_DIR/proxy-client-key.pem \\
  --service-cluster-ip-range=${SERVICE_CIDR} \\
  --service-node-port-range=${NODE_PORT_RANGE} \\
  --logtostderr=true \\
  --v=2
Restart=on-failure
RestartSec=10
Type=notify
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOF
##  --runtime-config=api/all=true,settings.k8s.io/v1alpha1=true \\
##  --enable-admission-plugins=NodeRestriction,PodPreset \\
##  两个参数中，第一个settings.k8s.io/v1alpha1=true与下面一个PodPreset的配合使用是
##  为了开启“使用podpreset全局配置pod”的功能，podpreset是作用于namespace级别的插件
}

###6.为各节点创建和分发 kube-apiserver systemd unit 文件
function issue_kube_apiserver_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	#替换模板文件中的变量，为各节点生成 systemd unit 文件：
	for (( i=0; i < ${#MASTER_IPS[@]}; i++ ))
	  do
		sed -e "s/##NODE_NAME##/${NODE_NAMES[i]}/" -e "s/##NODE_IP##/${MASTER_IPS[i]}/" kube-apiserver.service.template > kube-apiserver-${MASTER_IPS[i]}.service 
	  done
	ls kube-apiserver*.service

	#分发生成的 systemd unit 文件：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-apiserver-${node_ip}.service root@${node_ip}:/etc/systemd/system/kube-apiserver.service
	  done
}

###7.启动 kube-apiserver 服务
function start_kube_apiserver(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p ${K8S_DIR}/kube-apiserver"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable kube-apiserver && systemctl restart kube-apiserver"
	  done
} 
  
###8.检查 kube-apiserver 运行状态
function check_kube_apiserver(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status kube-apiserver"
	  done
}  
  
###9.打印 kube-apiserver 写入 etcd 的数据
function print_apiserver_etcd(){
	source $K8S_BIN_DIR/environment.sh
	ETCDCTL_API=3 etcdctl \
		--endpoints=${ETCD_ENDPOINTS} \
		--cacert=$K8S_WORK_DIR/ca.pem \
		--cert=$K8S_WORK_DIR/etcd.pem \
		--key=$K8S_WORK_DIR/etcd-key.pem \
		get /registry/ --prefix --keys-only
}
	
	
###10.检查集群信息
function check_custer(){
	sleep 5
	echo "集群信息:"
	kubectl cluster-info
	
	echo "集群所有命名空间:"
	kubectl get all --all-namespaces
	
	echo "集群组件状态:"
	kubectl get componentstatuses
}

###11.检查 kube-apiserver 监听的端口
function check_listen_port(){
	sudo netstat -lnpt|grep kube
}
	
###12.授予 kube-apiserver 访问 kubelet API 的权限
function auth_kubelet_api(){
	kubectl create clusterrolebinding kube-apiserver:kubelet-apis --clusterrole=system:kubelet-api-admin --user kubernetes
}

  
function main(){
	create_kuber
	create_pwd_config
	create_audit_policy
	create_metrics_server_cert
	create_kube_apiserver_service
	issue_kube_apiserver_service
	start_kube_apiserver
	check_kube_apiserver
	print_apiserver_etcd
	check_custer
	check_listen_port
	auth_kubelet_api
	echo "=====success====="
}

main "$@"
  
  
  
  
