#!/bin/sh
set -e
##集群搭建--初始化worker 节点##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.安装相关组件
function init(){
source $K8S_BIN_DIR/environment.sh
for node_ip in ${WOKER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    scp -P $SSH_PORT $K8S_WORK_DIR/kubernetes/server/bin/{kube-proxy,kubelet} root@${node_ip}:$K8S_BIN_DIR/
    ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	#吉野家的服务器上面已经装了
    #ssh -p $SSH_PORT root@${node_ip} "yum install -y epel-release"
	#01-1-system-init.sh 中好像已经装好了
    #ssh -p $SSH_PORT root@${node_ip} "yum install -y conntrack ipvsadm ntp ntpdate ipset jq iptables curl sysstat libseccomp && modprobe ip_vs "
  done
}

function main(){
	init
	echo "=====success====="
}
main "$@" 
  
  
  
  
  
  
  
  
  
  
  
  
