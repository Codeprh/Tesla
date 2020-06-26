#!/bin/sh
set -e
##集群搭建--部署coredns插件##
###需要在master节点上执行###
##author julian##
##date 2019-10-31##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh
source $K8S_BIN_DIR/environment.sh

###1.解压文件
function unzip_file(){
	cd $K8S_WORK_DIR/kubernetes/
	tar -xzvf kubernetes-src.tar.gz
}
###2.修改重要参数
function update_config(){
	cd $K8S_WORK_DIR/kubernetes/cluster/addons/dns/coredns
	cp coredns.yaml.base coredns.yaml
	sed -i -e "s/__PILLAR__DNS__DOMAIN__/${CLUSTER_DNS_DOMAIN}/" -e "s/__PILLAR__DNS__SERVER__/${CLUSTER_DNS_SVC_IP}/" coredns.yaml
	#修改镜像地址
	sed -i "s/k8s.gcr.io/gcr.azk8s.cn\/google_containers/" coredns.yaml
	#设置node节点label为middlep009
	sed -i "s/beta.kubernetes.io\/os: linux/kubernetes.io/hostname: middlep009/" coredns.yaml
}

###3.创建coredns
function create_coredns(){
	kubectl create -f coredns.yaml

	echo "检查kube-system相关内容"
	kubectl get all -n kube-system
	
	echo "需要到容器内验证是否可以访问域名"
}

function main(){
	unzip_file
	update_config
	create_coredns
	echo "====success===="
}

main "$@"