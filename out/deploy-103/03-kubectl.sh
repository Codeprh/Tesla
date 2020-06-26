#!/bin/sh
set -e
##集群搭建--kubectl命令行工具安装##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###1.分发kubectl二进制文件
function issue_kubectl(){
	cd $K8S_WORK_DIR
	tar -xzvf kubernetes-client-linux-amd64.tar.gz

	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kubernetes/client/bin/kubectl root@${node_ip}:$K8S_BIN_DIR/
		ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	  done
}

###2.创建admin证书和私钥
function create_admin_cert(){
	cd $K8S_WORK_DIR
	cat > admin-csr.json <<EOF
{
  "CN": "admin",
  "hosts": [],
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "GuangZhou",
      "L": "GuangZhou",
      "O": "system:masters",
      "OU": "System"
    }
  ]
}
EOF

	cfssl gencert -ca=$K8S_WORK_DIR/ca.pem \
	  -ca-key=$K8S_WORK_DIR/ca-key.pem \
	  -config=$K8S_WORK_DIR/ca-config.json \
	  -profile=kubernetes admin-csr.json | cfssljson -bare admin
	echo "生成的证书文件如下："
	ls admin*
}
###3.创建kubeconfig文件
function create_kubeconfig(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	# 设置集群参数
	kubectl config set-cluster kubernetes \
	  --certificate-authority=$K8S_WORK_DIR/ca.pem \
	  --embed-certs=true \
	  --server=${KUBE_APISERVER} \
	  --kubeconfig=kubectl.kubeconfig

	# 设置客户端认证参数
	kubectl config set-credentials admin \
	  --client-certificate=$K8S_WORK_DIR/admin.pem \
	  --client-key=$K8S_WORK_DIR/admin-key.pem \
	  --embed-certs=true \
	  --kubeconfig=kubectl.kubeconfig

	# 设置上下文参数
	kubectl config set-context kubernetes \
	  --cluster=kubernetes \
	  --user=admin \
	  --kubeconfig=kubectl.kubeconfig

	# 设置默认上下文
	kubectl config use-context kubernetes --kubeconfig=kubectl.kubeconfig

}

###4.分发kubeconfig文件
function issue_kubeconfig(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p ~/.kube"
		scp -P $SSH_PORT kubectl.kubeconfig root@${node_ip}:~/.kube/config
	  done
}

function main(){
	issue_kubectl
	create_admin_cert
	create_kubeconfig
	issue_kubeconfig
	echo "=====success====="
}

main "$@"



