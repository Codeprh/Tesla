#!/bin/bash
#在路径 ~ 下执行


#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

ssh-keygen -t rsa
for node_name in ${NODE_NAMES[@]}
	do
	  echo ">>> ${node_name}"
	  ssh-copy-id -p $SSH_PORT root@$node_name
	done