#!/bin/sh
set -e
##集群搭建--ca证书的创建##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.安装cfssl工具集
function install_cfssl(){
	sudo mkdir -p /opt/k8s/cert && cd $K8S_WORK_DIR
	wget https://pkg.cfssl.org/R1.2/cfssl_linux-amd64
	mv cfssl_linux-amd64 $K8S_BIN_DIR/cfssl

	wget https://pkg.cfssl.org/R1.2/cfssljson_linux-amd64
	mv cfssljson_linux-amd64 $K8S_BIN_DIR/cfssljson

	wget https://pkg.cfssl.org/R1.2/cfssl-certinfo_linux-amd64
	mv cfssl-certinfo_linux-amd64 $K8S_BIN_DIR/cfssl-certinfo

	chmod +x $K8S_BIN_DIR/*
	export PATH=$K8S_BIN_DIR:$PATH
}

###2.创建根证书CA
function create_ca(){
	# 创建配置文件
	cd $K8S_WORK_DIR
	cat > ca-config.json <<EOF
{
  "signing": {
    "default": {
      "expiry": "${CERT_DATE}"
    },
    "profiles": {
      "kubernetes": {
        "usages": [
            "signing",
            "key encipherment",
            "server auth",
            "client auth"
        ],
        "expiry": "${CERT_DATE}"
      }
    }
  }
}
EOF

	#创建证书签名请求文件
	cat > ca-csr.json <<EOF
{
  "CN": "kubernetes",
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
  ],
  "ca": {
    "expiry": "${CA_CERT_DATE}"
 }
}
EOF

	#生成ca证书和私钥
	cfssl gencert -initca ca-csr.json | cfssljson -bare ca
	echo "证书文件如下："
	ls ca*
}

###3.分发证书文件
function issue_cert(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	for node_ip in ${NODE_IPS[@]}
	  do
		echo ">>> ${node_ip}"
		ssh -p $SSH_PORT root@${node_ip} "mkdir -p $K8S_CERT_DIR"
		scp -P $SSH_PORT ca*.pem ca-config.json root@${node_ip}:$K8S_CERT_DIR
	  done
}



function main(){
install_cfssl
create_ca
issue_cert
	echo "=====success====="
}

main "$@"
