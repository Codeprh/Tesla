#!/bin/sh
set -e
##集群搭建--部署etcd集群##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###1.分发etcd二进制文件
function issue_etcd(){
	cd $K8S_WORK_DIR
	tar -xvf etcd-v3.3.13-linux-amd64.tar.gz

	#分发二进制文件到集群所有节点：
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${ETCD_CLUSTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT etcd-v3.3.13-linux-amd64/etcd* root@${node_ip}:$K8S_BIN_DIR
		ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	  done
}  

###2.创建与分发etcd证书和私钥
function create_issue_etcd_cert(){
	#创建证书签名请求：
	cd $K8S_WORK_DIR
	cat > etcd-csr.json <<EOF
{
  "CN": "etcd",
  "hosts": [
    "127.0.0.1",
    ${ETCD_IPS}
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
      "O": "k8s",
      "OU": "System"
    }
  ]
}
EOF

#生成证书和私钥：
cfssl gencert -ca=$K8S_WORK_DIR/ca.pem \
    -ca-key=$K8S_WORK_DIR/ca-key.pem \
    -config=$K8S_WORK_DIR/ca-config.json \
    -profile=kubernetes etcd-csr.json | cfssljson -bare etcd
ls etcd*pem

#分发生成的证书和私钥到各 etcd 节点：
source $K8S_BIN_DIR/environment.sh
for node_ip in ${ETCD_CLUSTER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "mkdir -p $ETCD_CERT_DIR"
    scp -P $SSH_PORT etcd*.pem root@${node_ip}:$ETCD_CERT_DIR/
  done
} 
  
###3.创建 etcd 的 systemd unit 模板文件
function create_etcd_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > etcd.service.template <<EOF
[Unit]
Description=Etcd Server
After=network.target
After=network-online.target
Wants=network-online.target
Documentation=https://github.com/coreos

[Service]
Type=notify
WorkingDirectory=${ETCD_DATA_DIR}
ExecStart=$K8S_BIN_DIR/etcd \\
  --data-dir=${ETCD_DATA_DIR} \\
  --wal-dir=${ETCD_WAL_DIR} \\
  --name=##NODE_NAME## \\
  --cert-file=$ETCD_CERT_DIR/etcd.pem \\
  --key-file=$ETCD_CERT_DIR/etcd-key.pem \\
  --trusted-ca-file=$K8S_CERT_DIR/ca.pem \\
  --peer-cert-file=$ETCD_CERT_DIR/etcd.pem \\
  --peer-key-file=$ETCD_CERT_DIR/etcd-key.pem \\
  --peer-trusted-ca-file=$K8S_CERT_DIR/ca.pem \\
  --peer-client-cert-auth \\
  --client-cert-auth \\
  --listen-peer-urls=https://##NODE_IP##:2380 \\
  --initial-advertise-peer-urls=https://##NODE_IP##:2380 \\
  --listen-client-urls=https://##NODE_IP##:2379,http://127.0.0.1:2379 \\
  --advertise-client-urls=https://##NODE_IP##:2379 \\
  --initial-cluster-token=etcd-cluster-0 \\
  --initial-cluster=${ETCD_NODES} \\
  --initial-cluster-state=new \\
  --auto-compaction-mode=periodic \\
  --auto-compaction-retention=1 \\
  --max-request-bytes=33554432 \\
  --quota-backend-bytes=6442450944 \\
  --heartbeat-interval=250 \\
  --election-timeout=2000
Restart=on-failure
RestartSec=5
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOF
}

###4.为各节点创建和分发 etcd systemd unit 文件
function issue_etcd_service(){
	echo "为各节点创建和分发 etcd systemd unit 文件 "
	#替换模板文件中的变量，为各节点创建 systemd unit 文件：
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for (( i=0; i < ${#ETCD_CLUSTER_IPS[@]}; i++ ))
	  do
		sed -e "s/##NODE_NAME##/${ETCD_NAMES[i]}/" -e "s/##NODE_IP##/${ETCD_CLUSTER_IPS[i]}/" etcd.service.template > etcd-${ETCD_CLUSTER_IPS[i]}.service 
	  done
	ls *.service

	#分发生成的 systemd unit 文件：
	for node_ip in ${ETCD_CLUSTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT etcd-${node_ip}.service root@${node_ip}:/etc/systemd/system/etcd.service
	  done
}

###5.启动etcd服务
function start_etcd_service(){
	echo "启动etcd服务 "
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${ETCD_CLUSTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p ${ETCD_DATA_DIR} ${ETCD_WAL_DIR}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable etcd && systemctl restart etcd " &
	  done
}

###6.检查启动结果 
function check_etcd(){
	echo "检查启动结果 "
	sleep 10
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${ETCD_CLUSTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status etcd"
	  done
}
  
###7.验证服务状态
function valid_etcd(){
	echo "验证服务状态 "
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${ETCD_CLUSTER_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ETCDCTL_API=3 $K8S_BIN_DIR/etcdctl \
		--endpoints=https://${node_ip}:2379 \
		--cacert=$K8S_CERT_DIR/ca.pem \
		--cert=$ETCD_CERT_DIR/etcd.pem \
		--key=$ETCD_CERT_DIR/etcd-key.pem endpoint health
	  done
}

###8.查看当前leader
function see_leader(){
	echo "查看当前leader "
	sleep 3
	source $K8S_BIN_DIR/environment.sh
	ETCDCTL_API=3 $K8S_BIN_DIR/etcdctl \
	  -w table --cacert=$K8S_CERT_DIR/ca.pem \
	  --cert=$ETCD_CERT_DIR/etcd.pem \
	  --key=$ETCD_CERT_DIR/etcd-key.pem \
	  --endpoints=${ETCD_ENDPOINTS} endpoint status
}


function main(){
	issue_etcd
	create_issue_etcd_cert
	create_etcd_service
	issue_etcd_service
	start_etcd_service
	check_etcd
	valid_etcd
	see_leader
	echo "=====success====="
}

main "$@"
  
  
