#!/bin/sh
set -e
##集群搭建--部署flannel网络##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.下载和分发 flanneld 二进制文件
function issue_flannel(){
	cd $K8S_WORK_DIR
	mkdir -p flannel
	tar -xzvf flannel-v0.11.0-linux-amd64.tar.gz -C flannel

	#分发二进制文件到集群所有节点：
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT flannel/{flanneld,mk-docker-opts.sh} root@${node_ip}:$K8S_BIN_DIR/
		ssh -p $SSH_PORT root@${node_ip} "chmod +x $K8S_BIN_DIR/*"
	  done
}
  
###2.创建 flannel 证书和私钥
function create_flannel_cert(){
	cd $K8S_WORK_DIR
	cat > flanneld-csr.json <<EOF
{
  "CN": "flanneld",
  "hosts": [],
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "Guangzhou",
      "L": "Guangzhou",
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
	  -profile=kubernetes flanneld-csr.json | cfssljson -bare flanneld
	ls flanneld*pem

	#将生成的证书和私钥分发到所有节点（master 和 worker）
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p $FLANNEL_CERT_DIR"
		scp -P $SSH_PORT flanneld*.pem root@${node_ip}:$FLANNEL_CERT_DIR
	  done
}
  
###3.向 etcd 写入集群 Pod 网段信息
###注意：本步骤只需执行一次。
function write_etcd(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	etcdctl \
	  --endpoints=${ETCD_ENDPOINTS} \
	  --ca-file=$K8S_WORK_DIR/ca.pem \
	  --cert-file=$K8S_WORK_DIR/flanneld.pem \
	  --key-file=$K8S_WORK_DIR/flanneld-key.pem \
	  mk ${FLANNEL_ETCD_PREFIX}/config '{"Network":"'${CLUSTER_CIDR}'", "SubnetLen": 21, "Backend": {"Type": "vxlan"}}'
} 
  
###4.创建 flanneld 的 systemd unit 文件
function create_flannel_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > flanneld.service << EOF
[Unit]
Description=Flanneld overlay address etcd agent
After=network.target
After=network-online.target
Wants=network-online.target
After=etcd.service
Before=docker.service

[Service]
Type=notify
ExecStart=$K8S_BIN_DIR/flanneld \\
  -etcd-cafile=$K8S_CERT_DIR/ca.pem \\
  -etcd-certfile=$FLANNEL_CERT_DIR/flanneld.pem \\
  -etcd-keyfile=$FLANNEL_CERT_DIR/flanneld-key.pem \\
  -etcd-endpoints=${ETCD_ENDPOINTS} \\
  -etcd-prefix=${FLANNEL_ETCD_PREFIX} \\
  -iface=${IFACE} \\
  -ip-masq
ExecStartPost=$K8S_BIN_DIR/mk-docker-opts.sh -k DOCKER_NETWORK_OPTIONS -d /run/flannel/docker
Restart=always
RestartSec=5
StartLimitInterval=0

[Install]
WantedBy=multi-user.target
RequiredBy=docker.service
EOF

}

###5.分发 flanneld systemd unit 文件到所有节点
function issue_flannel_service(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		scp -P $SSH_PORT flanneld.service root@${node_ip}:/etc/systemd/system/
	  done
}

###6. 启动 flanneld 服务
function start_flannel_service(){
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable flanneld && systemctl restart flanneld"
	  done
}
  
###7.检查启动结果
function check_flannel(){
	echo "检查启动结果"
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "systemctl status flanneld"
	  done
}  

###8.检查分配给各 flanneld 的 Pod 网段信息
function check_flannel_network(){
	source $K8S_BIN_DIR/environment.sh
	
	echo "查看集群 Pod 网段(/16)："
	etcdctl \
	  --endpoints=${ETCD_ENDPOINTS} \
	  --ca-file=$K8S_CERT_DIR/ca.pem \
	  --cert-file=$FLANNEL_CERT_DIR/flanneld.pem \
	  --key-file=$FLANNEL_CERT_DIR/flanneld-key.pem \
	  get ${FLANNEL_ETCD_PREFIX}/config
	 
	echo "查看已分配的 Pod 子网段列表(/24):"
	etcdctl \
	  --endpoints=${ETCD_ENDPOINTS} \
	  --ca-file=$K8S_CERT_DIR/ca.pem \
	  --cert-file=$FLANNEL_CERT_DIR/flanneld.pem \
	  --key-file=$FLANNEL_CERT_DIR/flanneld-key.pem \
	  ls ${FLANNEL_ETCD_PREFIX}/subnets
	
	echo "查看某一 Pod 网段对应的节点 IP 和 flannel 接口地址:"
	etcdctl \
	  --endpoints=${ETCD_ENDPOINTS} \
	  --ca-file=$K8S_CERT_DIR/ca.pem \
	  --cert-file=$FLANNEL_CERT_DIR/flanneld.pem \
	  --key-file=$FLANNEL_CERT_DIR/flanneld-key.pem \
	get ${FLANNEL_ETCD_PREFIX}/subnets/$(etcdctl \
	  --endpoints=${ETCD_ENDPOINTS} \
	  --ca-file=$K8S_CERT_DIR/ca.pem \
	  --cert-file=$FLANNEL_CERT_DIR/flanneld.pem \
	  --key-file=$FLANNEL_CERT_DIR/flanneld-key.pem \
	ls ${FLANNEL_ETCD_PREFIX}/subnets|awk -F"/" 'NR==1{print $5}')
}	  

  
function main(){
	issue_flannel
	create_flannel_cert
	write_etcd
	create_flannel_service
	issue_flannel_service
	start_flannel_service
	check_flannel
	check_flannel_network
	echo "=====success====="
}

main "$@"