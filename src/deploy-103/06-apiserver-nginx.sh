#!/bin/sh
set -e
##集群搭建--kube-apiserver 高可用之 nginx 代理##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###0.升级C工具
function install_c(){
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "yum -y install gcc kernel-devel"
		#吉野家改为离线装      gcc跟libgcc原本已经装好了     好像都装了  直接跳过
		cd $K8S_WORK_DIR
		#scp -P $SSH_PORT gcc-4.8.5-39.el7.x86_64.rpm  root@${node_ip}:$K8S_WORK_DIR/
		#scp -P $SSH_PORT libgcc-4.8.5-39.el7.x86_64.rpm  root@${node_ip}:$K8S_WORK_DIR/
		#scp -P $SSH_PORT kernel-devel-3.10.0-514.26.2.el7.x86_64.rpm  root@${node_ip}:$K8S_WORK_DIR/
		
		#yum -y install $K8S_WORK_DIR/gcc-4.8.5-39.el7.x86_64.rpm
		#yum -y install $K8S_WORK_DIR/libgcc-4.8.5-39.el7.x86_64.rpm
		#yum -y install $K8S_WORK_DIR/kernel-devel-3.10.0-514.26.2.el7.x86_64.rpm
	  done
}

###1.编译 nginx
function make_nginx(){
	cd $K8S_WORK_DIR
	tar -xzvf nginx-1.15.3.tar.gz

	#配置编译参数：
	cd $K8S_WORK_DIR/nginx-1.15.3
	mkdir -p nginx-prefix
	./configure --with-stream --without-http --prefix=$(pwd)/nginx-prefix --without-http_uwsgi_module --without-http_scgi_module --without-http_fastcgi_module

	#编译和安装：
	cd $K8S_WORK_DIR/nginx-1.15.3
	make && make install
}

###2.验证编译的 nginx
function valid_nginx(){
	echo "nginx版本信息:"
	cd $K8S_WORK_DIR/nginx-1.15.3
	./nginx-prefix/sbin/nginx -v

	echo "查看 nginx 动态链接的库："
	ldd ./nginx-prefix/sbin/nginx
}

###3.安装和部署 nginx
function install_nginx(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh

	#创建目录结构：
	for node_ip in ${NODE_IPS[@]}
	  do
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p $KUBE_NGINX_DIR/{conf,logs,sbin}"
	  done

	#拷贝二进制程序： 
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p $KUBE_NGINX_DIR/{conf,logs,sbin}"
		scp -P $SSH_PORT $K8S_WORK_DIR/nginx-1.15.3/nginx-prefix/sbin/nginx  root@${node_ip}:$KUBE_NGINX_DIR/sbin/kube-nginx
		ssh -p $SSH_PORT root@${node_ip} "chmod a+x $KUBE_NGINX_DIR/sbin/*"
	  done
	  
	#配置 nginx，开启透明转发功能：
	#组装server列表，注意换行后面的 双引号"不能删
	server_texts=""
	for node_ip in ${NODE_IPS[@]}
	  do
		server_texts=$server_texts"server "$node_ip":6443       max_fails=3 fail_timeout=30s;
		"
	  done
	cat > kube-nginx.conf <<EOF
worker_processes 1;

events {
    worker_connections  1024;
}

stream {
    upstream backend {
        hash \$remote_addr consistent;
        $server_texts
    }

    server {
        listen 127.0.0.1:8443;
        proxy_connect_timeout 1s;
        proxy_pass backend;
    }
}
EOF
  
	#分发配置文件：
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-nginx.conf  root@${node_ip}:$KUBE_NGINX_DIR/conf/kube-nginx.conf
	  done
} 
  
###4.配置 systemd unit 文件，启动服务
function start_nginx(){
	cd $K8S_WORK_DIR
	cat > kube-nginx.service <<EOF
[Unit]
Description=kube-apiserver nginx proxy
After=network.target
After=network-online.target
Wants=network-online.target

[Service]
Type=forking
ExecStartPre=$KUBE_NGINX_DIR/sbin/kube-nginx -c $KUBE_NGINX_DIR/conf/kube-nginx.conf -p $KUBE_NGINX_DIR -t
ExecStart=$KUBE_NGINX_DIR/sbin/kube-nginx -c $KUBE_NGINX_DIR/conf/kube-nginx.conf -p $KUBE_NGINX_DIR
ExecReload=$KUBE_NGINX_DIR/sbin/kube-nginx -c $KUBE_NGINX_DIR/conf/kube-nginx.conf -p $KUBE_NGINX_DIR -s reload
PrivateTmp=true
Restart=always
RestartSec=5
StartLimitInterval=0
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOF

	#分发 systemd unit 文件：
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT kube-nginx.service  root@${node_ip}:/etc/systemd/system/
	  done
	  
	#启动 kube-nginx 服务：
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable kube-nginx && systemctl restart kube-nginx"
	  done
}
  
###5.检查 kube-nginx 服务运行状态
function check_nginx(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status kube-nginx"
	  done
}

function main(){
	install_c
	make_nginx
	valid_nginx
	install_nginx
	start_nginx
	check_nginx
	echo "=====success====="
}

main "$@"
  
  
  
  
  
  
  
