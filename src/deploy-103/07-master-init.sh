#!/bin/sh
set -e
##集群搭建--初始化master 节点##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.分发kubernetes-server二进制文件
function issue_kubernetes(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	tar -xzvf kubernetes-server-linux-amd64.tar.gz
	cd kubernetes
	tar -xzvf  kubernetes-src.tar.gz

	#将二进制文件拷贝到所有 master 节点：
	for node_ip in ${MASTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT $K8S_WORK_DIR/kubernetes/server/bin/{apiextensions-apiserver,cloud-controller-manager,kube-apiserver,kube-controller-manager,kube-proxy,kube-scheduler,kubeadm,kubectl,kubelet,mounter} root@${node_ip}:$K8S_BIN_DIR/
		ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	  done
}
  
function main(){
	issue_kubernetes
	echo "=====success====="
}

main "$@"
  
  
  
  
