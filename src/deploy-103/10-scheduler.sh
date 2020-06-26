#!/bin/sh
set -e

##集群搭建--部署高可用 kube-scheduler 集群 集群##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.创建 kube-scheduler 证书和私钥
function create_scheduler_cert(){
	cd $K8S_WORK_DIR

	#创建证书签名请求：
	cat > $K8S_WORK_DIR/kube-scheduler-csr.json <<EOF
{
    "CN": "system:kube-scheduler",
    "hosts": [
      "127.0.0.1",
      ${SCHEDULER_MANAGER_IPS}
    ],
    "key": {
        "algo": "rsa",
        "size": 2048
    },
    "names": [
      {
        "C": "CN",
        "ST": "GuangZhou",
        "L": "GuangZhou",
        "O": "system:kube-scheduler",
        "OU": "System"
      }
    ]
}
EOF

	#生成证书和私钥：
	cfssl gencert -ca=$K8S_WORK_DIR/ca.pem \
	  -ca-key=$K8S_WORK_DIR/ca-key.pem \
	  -config=$K8S_WORK_DIR/ca-config.json \
	  -profile=kubernetes kube-scheduler-csr.json | cfssljson -bare kube-scheduler
	ls kube-scheduler*pem

	#将生成的证书和私钥分发到所有 master 节点：
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT  kube-scheduler*.pem root@${node_ip}:$K8S_CERT_DIR/
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
	  --kubeconfig=kube-scheduler.kubeconfig

	kubectl config set-credentials system:kube-scheduler \
	  --client-certificate=kube-scheduler.pem \
	  --client-key=kube-scheduler-key.pem \
	  --embed-certs=true \
	  --kubeconfig=kube-scheduler.kubeconfig

	kubectl config set-context system:kube-scheduler \
	  --cluster=kubernetes \
	  --user=system:kube-scheduler \
	  --kubeconfig=kube-scheduler.kubeconfig

	kubectl config use-context system:kube-scheduler --kubeconfig=kube-scheduler.kubeconfig

	#分发 kubeconfig 到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-scheduler.kubeconfig root@${node_ip}:$K8S_CERT_PARENT_DIR/
	  done
}  
 
###3.创建 kube-scheduler 配置文件
function create_scheduler_config(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat >kube-scheduler.yaml.template <<EOF
apiVersion: kubescheduler.config.k8s.io/v1alpha1
kind: KubeSchedulerConfiguration
bindTimeoutSeconds: 600
clientConnection:
  burst: 200
  kubeconfig: "$K8S_CERT_PARENT_DIR/kube-scheduler.kubeconfig"
  qps: 100
enableContentionProfiling: false
enableProfiling: true
hardPodAffinitySymmetricWeight: 1
healthzBindAddress: ##NODE_IP##:10251
leaderElection:
  leaderElect: true
metricsBindAddress: ##NODE_IP##:10251
EOF

	#替换模板文件中的变量：
	for (( i=0; i < ${#MASTER_IPS}; i++ ))
	  do
		sed -e "s/##NODE_NAME##/${NODE_NAMES[i]}/" -e "s/##NODE_IP##/${MASTER_IPS[i]}/" kube-scheduler.yaml.template > kube-scheduler-${MASTER_IPS[i]}.yaml
	  done
	ls kube-scheduler*.yaml

	#分发 kube-scheduler 配置文件到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-scheduler-${node_ip}.yaml root@${node_ip}:$K8S_CERT_PARENT_DIR/kube-scheduler.yaml
	  done
}
   
###4.创建 kube-scheduler systemd unit 模板文件
function create_scheduler_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	cat > kube-scheduler.service.template <<EOF
[Unit]
Description=Kubernetes Scheduler
Documentation=https://github.com/GoogleCloudPlatform/kubernetes

[Service]
WorkingDirectory=${K8S_DIR}/kube-scheduler
ExecStart=$K8S_BIN_DIR/kube-scheduler \\
  --config=$K8S_CERT_PARENT_DIR/kube-scheduler.yaml \\
  --bind-address=##NODE_IP## \\
  --secure-port=10259 \\
  --port=0 \\
  --tls-cert-file=$K8S_CERT_DIR/kube-scheduler.pem \\
  --tls-private-key-file=$K8S_CERT_DIR/kube-scheduler-key.pem \\
  --authentication-kubeconfig=$K8S_CERT_PARENT_DIR/kube-scheduler.kubeconfig \\
  --client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --requestheader-allowed-names="" \\
  --requestheader-client-ca-file=$K8S_CERT_DIR/ca.pem \\
  --requestheader-extra-headers-prefix="X-Remote-Extra-" \\
  --requestheader-group-headers=X-Remote-Group \\
  --requestheader-username-headers=X-Remote-User \\
  --authorization-kubeconfig=$K8S_CERT_PARENT_DIR/kube-scheduler.kubeconfig \\
  --logtostderr=true \\
  --v=2
Restart=always
RestartSec=5
StartLimitInterval=0

[Install]
WantedBy=multi-user.target
EOF
}

###5.为各节点创建和分发 kube-scheduler systemd unit 文件
function issue_scheduler_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	#替换模板文件中的变量，为各节点创建 systemd unit 文件：
	for (( i=0; i < ${#MASTER_IPS}; i++ ))
	  do
		sed -e "s/##NODE_NAME##/${NODE_NAMES[i]}/" -e "s/##NODE_IP##/${MASTER_IPS[i]}/" kube-scheduler.service.template > kube-scheduler-${MASTER_IPS[i]}.service 
	  done
	ls kube-scheduler*.service

	#分发 systemd unit 文件到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-scheduler-${node_ip}.service root@${node_ip}:/etc/systemd/system/kube-scheduler.service
	  done
  
}  
  
###6.启动 kube-scheduler 服务
function start_scheduler_service(){
source $K8S_BIN_DIR/environment.sh
for node_ip in ${MASTER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "mkdir -p ${K8S_DIR}/kube-scheduler"
    ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable kube-scheduler && systemctl restart kube-scheduler"
  done
}
  
###7.检查服务运行状态
function check_scheduler_service(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status kube-scheduler"
	  done
}
  
###8.查看当前的 leader
function see_scheduler_leader(){
	sleep 2
	kubectl get endpoints kube-scheduler --namespace=kube-system  -o yaml
}
 

function main(){
	create_scheduler_cert
	create_issue_kubeconfig
	create_scheduler_config
	create_scheduler_service
	issue_scheduler_service
	start_scheduler_service
	check_scheduler_service
	see_scheduler_leader
	echo "=====success====="
} 

main "$@"
  
  
  
  
  