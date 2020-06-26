#!/bin/sh
set -e
##集群搭建--部署高可用 kube-controller-manager 集群##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.创建 kube-controller-manager 证书和私钥
function create_controller_manager_cert(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	#创建证书签名请求：
	cat > $K8S_WORK_DIR/kube-controller-manager-csr.json <<EOF
{
    "CN": "system:kube-controller-manager",
    "key": {
        "algo": "rsa",
        "size": 2048
    },
    "hosts": [
      "127.0.0.1",
      $CONTROLLER_MANAGER_IPS
    ],
    "names": [
      {
        "C": "CN",
        "ST": "GuangZhou",
        "L": "GuangZhou",
        "O": "system:kube-controller-manager",
        "OU": "System"
      }
    ]
}
EOF
  
	#生成证书和私钥：
	cfssl gencert -ca=$K8S_WORK_DIR/ca.pem \
	  -ca-key=$K8S_WORK_DIR/ca-key.pem \
	  -config=$K8S_WORK_DIR/ca-config.json \
	  -profile=kubernetes kube-controller-manager-csr.json | cfssljson -bare kube-controller-manager
	echo "生成的证书如下："
	ls kube-controller-manager*pem
	
	#将生成的证书和私钥分发到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-controller-manager*.pem root@${node_ip}:$K8S_CERT_DIR/
	  done
} 
  
  
###2.创建和分发 kubeconfig 文件
function create_issue_kubeconfig(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	kubectl config set-cluster kubernetes \
	  --certificate-authority=$K8S_WORK_DIR/ca.pem \
	  --embed-certs=true \
	  --server=${KUBE_APISERVER} \
	  --kubeconfig=kube-controller-manager.kubeconfig

	kubectl config set-credentials system:kube-controller-manager \
	  --client-certificate=kube-controller-manager.pem \
	  --client-key=kube-controller-manager-key.pem \
	  --embed-certs=true \
	  --kubeconfig=kube-controller-manager.kubeconfig

	kubectl config set-context system:kube-controller-manager \
	  --cluster=kubernetes \
	  --user=system:kube-controller-manager \
	  --kubeconfig=kube-controller-manager.kubeconfig

	kubectl config use-context system:kube-controller-manager --kubeconfig=kube-controller-manager.kubeconfig

	#分发 kubeconfig 到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-controller-manager.kubeconfig root@${node_ip}:$K8S_CERT_PARENT_DIR/
	  done
}

###3.创建 kube-controller-manager systemd unit 模板文件
function create_controller_manager_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > kube-controller-manager.service.template <<EOF
	[Unit]
Description=Kubernetes Controller Manager
Documentation=https://github.com/GoogleCloudPlatform/kubernetes

[Service]
WorkingDirectory=${K8S_DIR}/kube-controller-manager
ExecStart=$K8S_BIN_DIR/kube-controller-manager \\
  --profiling \\
  --cluster-name=kubernetes \\
  --controllers=*,bootstrapsigner,tokencleaner \\
  --kube-api-qps=1000 \\
  --kube-api-burst=2000 \\
  --leader-elect \\
  --use-service-account-credentials\\
  --concurrent-service-syncs=2 \\
  --bind-address=##NODE_IP## \\
  --secure-port=10252 \\
  --tls-cert-file=$K8S_CERT_DIR/kube-controller-manager.pem \\
  --tls-private-key-file=$K8S_CERT_DIR/kube-controller-manager-key.pem \\
  --port=0 \\
  --authentication-kubeconfig=$K8S_CERT_PARENT_DIR/kube-controller-manager.kubeconfig \\
  --client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --requestheader-allowed-names="" \\
  --requestheader-client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --requestheader-extra-headers-prefix="X-Remote-Extra-" \\
  --requestheader-group-headers=X-Remote-Group \\
  --requestheader-username-headers=X-Remote-User \\
  --authorization-kubeconfig=$K8S_CERT_PARENT_DIR/kube-controller-manager.kubeconfig \\
  --cluster-signing-cert-file=$K8S_CERT_DIR/ca.pem \\
  --cluster-signing-key-file=$K8S_CERT_DIR/ca-key.pem \\
  --experimental-cluster-signing-duration=876000h \\
  --horizontal-pod-autoscaler-sync-period=10s \\
  --concurrent-deployment-syncs=10 \\
  --concurrent-gc-syncs=30 \\
  --node-cidr-mask-size=24 \\
  --service-cluster-ip-range=${SERVICE_CIDR} \\
  --pod-eviction-timeout=6m \\
  --terminated-pod-gc-threshold=10000 \\
  --root-ca-file=$K8S_CERT_DIR/ca.pem \\
  --service-account-private-key-file=$K8S_CERT_DIR/ca-key.pem \\
  --kubeconfig=$K8S_CERT_PARENT_DIR/kube-controller-manager.kubeconfig \\
  --logtostderr=true \\
  --v=2
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF
}

###4.为各节点创建和分发 kube-controller-mananger systemd unit 文件
function issue_controller_manager_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	#替换模板文件中的变量，为各节点创建 systemd unit 文件：
	for (( i=0; i < ${#MASTER_IPS[@]}; i++ ))
	  do
		sed -e "s/##NODE_NAME##/${NODE_NAMES[i]}/" -e "s/##NODE_IP##/${MASTER_IPS[i]}/" kube-controller-manager.service.template > kube-controller-manager-${MASTER_IPS[i]}.service 
	  done
	ls kube-controller-manager*.service

	#分发到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>>service ${node_ip}"
		scp -P $SSH_PORT kube-controller-manager-${node_ip}.service root@${node_ip}:/etc/systemd/system/kube-controller-manager.service
	  done
}
  
###5.启动 kube-controller-manager 服务
function start_controller_manager_service(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p ${K8S_DIR}/kube-controller-manager"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable kube-controller-manager && systemctl restart kube-controller-manager"
	  done
}  
  
###6.检查服务运行状态
function check_controller_manager_service(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status kube-controller-manager"
	  done
	
	sudo netstat -lnpt | grep kube-cont
}
  
  
###7.查看kube-controller-manager 的权限
function see_auth(){
  kubectl describe clusterrole system:kube-controller-manager

  kubectl get clusterrole|grep controller

  kubectl describe clusterrole system:controller:deployment-controller
}
  
###8.查看当前的 leader
function see_leader(){
  kubectl get endpoints kube-controller-manager --namespace=kube-system  -o yaml
}
  
function main(){
	create_controller_manager_cert
	create_issue_kubeconfig
	create_controller_manager_service
	issue_controller_manager_service
	start_controller_manager_service
	check_controller_manager_service
	see_auth
	see_leader
	echo "=====success====="
}

main "$@"

