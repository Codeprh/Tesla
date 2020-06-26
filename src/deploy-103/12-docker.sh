#!/bin/sh
set -e

##集群搭建--部署 docker 组件##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.下载和分发 docker 二进制文件
function issue_docker(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	tar -xvf docker-18.09.6.tgz
	
	#分发二进制文件到所有 worker 节点：
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT docker/*  root@${node_ip}:$K8S_BIN_DIR/
		ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	  done
}
  
###2.创建和分发 systemd unit 文件
function create_issue_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > docker.service <<EOF
[Unit]
Description=Docker Application Container Engine
Documentation=http://docs.docker.io

[Service]
WorkingDirectory=##DOCKER_DIR##
Environment="PATH=$K8S_BIN_DIR:/bin:/sbin:/usr/bin:/usr/sbin"
EnvironmentFile=-/run/flannel/docker
ExecStart=$K8S_BIN_DIR/dockerd \$DOCKER_NETWORK_OPTIONS
ExecReload=/bin/kill -s HUP \$MAINPID
Restart=on-failure
RestartSec=5
LimitNOFILE=infinity
LimitNPROC=infinity
LimitCORE=infinity
Delegate=yes
KillMode=process

[Install]
WantedBy=multi-user.target
EOF

	#分发 systemd unit 文件到所有 worker 机器:
	sed -i -e "s|##DOCKER_DIR##|${DOCKER_DIR}|" docker.service
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT docker.service root@${node_ip}:/etc/systemd/system/
	  done
}
   
###3.配置和分发 docker 配置文件  
function create_issue_config(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > docker-daemon.json <<EOF
{
    "registry-mirrors": ["https://docker.mirrors.ustc.edu.cn","https://hub-mirror.c.163.com"],
    "insecure-registries": ["docker02:35000"],
    "max-concurrent-downloads": 20,
    "live-restore": true,
    "max-concurrent-uploads": 10,
    "debug": true,
    "data-root": "${DOCKER_DIR}/data",
    "exec-root": "${DOCKER_DIR}/exec",
    "log-opts": {
      "max-size": "100m",
      "max-file": "5"
    }
}
EOF

#分发 docker 配置文件到所有 worker 节点：
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p  /etc/docker/ ${DOCKER_DIR}/{data,exec}"
		scp -P $SSH_PORT docker-daemon.json root@${node_ip}:/etc/docker/daemon.json
	  done
}
  
###4.启动 docker 服务
function start_docker(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable docker && systemctl restart docker"
	  done
} 

###5.检查服务运行状态
function check_service(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status docker"
	  done
}

###6.检查 docker0 网桥
function check_network(){
	 source $K8S_BIN_DIR/environment.sh
	for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "/usr/sbin/ip addr show flannel.1 && /usr/sbin/ip addr show docker0"
	  done
}
   
###7.查看 docker 的状态信息
function docker_info(){
for node_ip in ${WOKER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "ps -elfH|grep docker"
		ssh -p $SSH_PORT root@${node_ip} "docker info"
	  done
}
 
 
function main(){
	issue_docker
	create_issue_service
	create_issue_config
	start_docker
	check_service
	check_network
	docker_info
	echo "=====success====="
}
main "$@"
  
  
  
  
  
  
  
  
  
