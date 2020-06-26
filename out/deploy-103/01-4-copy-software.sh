#!/bin/sh
##集群搭建--复制安装包到指定环境##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###1.复制配置文件到bin目录下做备份
function copy_software(){
	for node_name in ${NODE_NAMES[@]}
	do
		echo ">>> ${node_name}"
		
		## 初始化环境配置
		ssh -p $SSH_PORT root@${node_name} "sh $K8S_BIN_DIR/environment.sh"
		ssh -p $SSH_PORT root@${node_name} "sh $K8S_BIN_DIR/system-init.sh"
	done
}


function main(){
copy_software
}

main "$@"