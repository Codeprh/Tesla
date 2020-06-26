#!/bin/sh
##集群搭建--复制配置文件到bin目录下做备份##
##author julian##
##date 2019-10-28##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###1.复制配置文件到bin目录下做备份
function copy_environment(){
	for node_name in ${NODE_NAMES[@]}
	do
		echo ">>> ${node_name}"
		ssh -p $SSH_PORT root@${node_name} "mkdir -p $K8S_BIN_DIR"
		scp -P $SSH_PORT $DIRNAME/00-environment.sh root@$node_name:$K8S_BIN_DIR/environment.sh
		scp -P $SSH_PORT $DIRNAME/00-environment.sh root@$node_name:$K8S_BIN_DIR/00-environment.sh
		scp -P $SSH_PORT $DIRNAME/01-1-system-init.sh root@$node_name:$K8S_BIN_DIR/system-init.sh
	done
}


function main(){
copy_environment
}

main "$@"