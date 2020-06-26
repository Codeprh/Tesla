#!/bin/sh
set -e

##集群搭建--部署 kube-proxy 组件##
##author julian##
##date 2019-10-25##

#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh


###1.创建 kube-proxy 证书
function create_cert(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > kube-proxy-csr.json <<EOF
{
  "CN": "system:kube-proxy",
  "key": {
    "algo": "rsa",
    "size": 2048
  },
  "names": [
    {
      "C": "CN",
      "ST": "BeiJing",
      "L": "BeiJing",
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
	  -profile=kubernetes  kube-proxy-csr.json | cfssljson -bare kube-proxy
	ls kube-proxy*
}
###2.创建和分发 kubeconfig 文件
function issue_kubeconfig(){
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	kubectl config set-cluster kubernetes \
	  --certificate-authority=$K8S_WORK_DIR/ca.pem \
	  --embed-certs=true \
	  --server=${KUBE_APISERVER} \
	  --kubeconfig=kube-proxy.kubeconfig

	kubectl config set-credentials kube-proxy \
	  --client-certificate=kube-proxy.pem \
	  --client-key=kube-proxy-key.pem \
	  --embed-certs=true \
	  --kubeconfig=kube-proxy.kubeconfig

	kubectl config set-context default \
	  --cluster=kubernetes \
	  --user=kube-proxy \
	  --kubeconfig=kube-proxy.kubeconfig

	kubectl config use-context default --kubeconfig=kube-proxy.kubeconfig

	#分发 kubeconfig 文件：
	echo "分发 kubeconfig 文件"
	for node_name in ${WOKER_NAMES[@]}
	  do
		echo ">>> ${node_name}"
		scp -P $SSH_PORT kube-proxy.kubeconfig root@${node_name}:$K8S_CERT_PARENT_DIR/
	  done
}
  
###3.创建 kube-proxy 配置文件
##20191212 mode: "ipvs" -> mode: "" #不适用ipvs模式
function create_kube_proxy_config(){  
	cd $K8S_WORK_DIR
	cat > kube-proxy-config.yaml.template <<EOF
kind: KubeProxyConfiguration
apiVersion: kubeproxy.config.k8s.io/v1alpha1
clientConnection:
  burst: 200
  kubeconfig: "$K8S_CERT_PARENT_DIR/kube-proxy.kubeconfig"
  qps: 100
bindAddress: ##NODE_IP##
healthzBindAddress: ##NODE_IP##:10256
metricsBindAddress: ##NODE_IP##:10249
enableProfiling: true
clusterCIDR: ${CLUSTER_CIDR}
hostnameOverride: ##NODE_NAME##
mode: ""
portRange: ""
kubeProxyIPTablesConfiguration:
  masqueradeAll: false
kubeProxyIPVSConfiguration:
  scheduler: rr
  excludeCIDRs: []
EOF

	echo "为各节点创建和分发 kube-proxy 配置文件"
	for (( i=0; i < ${#WOKER_IPS[@]}; i++ ))
	  do 
		echo ">>> ${WOKER_NAMES[i]}"
		sed -e "s/##NODE_NAME##/${WOKER_NAMES[i]}/" -e "s/##NODE_IP##/${WOKER_IPS[i]}/" kube-proxy-config.yaml.template > kube-proxy-config-${WOKER_NAMES[i]}.yaml.template
		scp -P $SSH_PORT kube-proxy-config-${WOKER_NAMES[i]}.yaml.template root@${WOKER_NAMES[i]}:$K8S_CERT_PARENT_DIR/kube-proxy-config.yaml
	  done
}

###4.创建和分发 kube-proxy systemd unit 文件
function issue_kube_proxy_service(){
	echo "创建和分发 kube-proxy systemd unit 文件"
	cd $K8S_WORK_DIR
	source $K8S_BIN_DIR/environment.sh
	cat > kube-proxy.service <<EOF
[Unit]
Description=Kubernetes Kube-Proxy Server
Documentation=https://github.com/GoogleCloudPlatform/kubernetes
After=network.target

[Service]
WorkingDirectory=${K8S_DIR}/kube-proxy
ExecStart=$K8S_BIN_DIR/kube-proxy \\
  --config=$K8S_CERT_PARENT_DIR/kube-proxy-config.yaml \\
  --logtostderr=true \\
  --v=2
Restart=on-failure
RestartSec=5
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOF

	#分发 kube-proxy systemd unit 文件：
	for node_name in ${WOKER_NAMES[@]}
	  do 
		echo ">>> ${node_name}"
		scp -P $SSH_PORT kube-proxy.service root@${node_name}:/etc/systemd/system/
	  done
}

###5.启动 kube-proxy 服务
function start_kube_proxy_service(){
cd $K8S_WORK_DIR
source $K8S_BIN_DIR/environment.sh
for node_ip in ${WOKER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "mkdir -p ${K8S_DIR}/kube-proxy"
    ssh -p $SSH_PORT root@${node_ip} "modprobe ip_vs_rr"
    ssh -p $SSH_PORT root@${node_ip} "systemctl daemon-reload && systemctl enable kube-proxy && systemctl restart kube-proxy"
  done
}

###6.检查启动结果
function check_service(){
source $K8S_BIN_DIR/environment.sh
for node_ip in ${WOKER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "systemctl status kube-proxy"
  done
}


###7.查看监听端口
function see_port(){
source $K8S_BIN_DIR/environment.sh
for node_ip in ${WOKER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "/usr/sbin/ipvsadm -ln"
  done
}

###8.查看 ipvs 路由规则
function see_ipvs(){
source $K8S_BIN_DIR/environment.sh
for node_ip in ${WOKER_IPS[@]}
  do
    echo ">>> ${node_ip}"
    ssh -p $SSH_PORT root@${node_ip} "/usr/sbin/ipvsadm -ln"
  done
}

function main(){
	create_cert
	issue_kubeconfig
	create_kube_proxy_config
	issue_kube_proxy_service
	start_kube_proxy_service
	check_service
	see_port
	see_ipvs
	echo "=====success====="
}

main "$@"

