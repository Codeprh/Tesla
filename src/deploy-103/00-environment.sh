#!/usr/bin/bash
##author julian##
##date 2019-10-23##
####注意host_name不要使用大写的####

# ssh端口号
export SSH_PORT=22;

# hosts 多个用\n隔开
export HOSTS='10.200.102.161=k8s-node-161\n10.200.102.162=k8s-node-162\n10.200.102.163=k8s-node-163\n10.200.102.164=k8s-node-164\n10.200.102.165=k8s-node-165'

# CA证书有效时间  10年
export CA_CERT_DATE="87600h"

# 统一证书有效时间 1年
export CERT_DATE="87600h"

# 所有集群节点的ip数组  master节点放前面
export NODE_IPS=(10.200.102.161 10.200.102.162 10.200.102.163 10.200.102.164 10.200.102.165)

# master节点的ip数组
export MASTER_IPS=(10.200.102.161 10.200.102.162 10.200.102.163)

# etcd节点的ip数组
export ETCD_CLUSTER_IPS=(10.200.102.161 10.200.102.162 10.200.102.163)

# woker节点的ip数组  master节点放前面
export WOKER_IPS=(10.200.102.161 10.200.102.162 10.200.102.163 10.200.102.164 10.200.102.165)

# woker节点的hostname数组
export WOKER_NAMES=(10.200.102.161 10.200.102.162 10.200.102.163 10.200.102.164 10.200.102.165)

export WOKER_HOST_NAMES=(k8s-node-161 k8s-node-162 k8s-node-163 k8s-node-164 k8s-node-165)

# 集群各 IP 对应的hostname数组 按照先主后从的顺序写
export NODE_NAMES=(10.200.102.161 10.200.102.162 10.200.102.163 10.200.102.164 10.200.102.165)

export ETCD_NAMES=(etcd01 etcd02 etcd03)

# etcd 集群服务地址ip
export ETCD_IPS='"10.200.102.161",
    "10.200.102.162",
	"10.200.102.163"'

# etcd 集群服务地址列表 多个地址用,隔开
export ETCD_ENDPOINTS="https://10.200.102.161:2379,https://10.200.102.162:2379,https://10.200.102.163:2379"

# etcd 集群间通信的 IP 和端口 多个用,隔开
export ETCD_NODES="etcd01=https://10.200.102.161:2380,etcd02=https://10.200.102.162:2380,etcd03=https://10.200.102.163:2380"

# etcd 证书 目录
export ETCD_CERT_DIR="/etc/etcd/cert"

# kube-apiserver 的反向代理(kube-nginx)地址端口
export KUBE_APISERVER="https://127.0.0.1:8443"

# 节点间互联网络接口名称
export IFACE="ens160"

# etcd 数据目录
export ETCD_DATA_DIR="/data/k8s/etcd/data"

# etcd WAL 目录，建议是 SSD 磁盘分区，或者和 ETCD_DATA_DIR 不同的磁盘分区
export ETCD_WAL_DIR="/data/k8s/etcd/wal"

# k8s 各组件数据 目录
export K8S_DIR="/data/k8s/k8s"

# k8s 工作 目录
export K8S_WORK_DIR="/opt/k8s/work"

# k8s bin 目录
export K8S_BIN_DIR="/usr/local/bin/"

# k8s 数据父目录
export K8S_CERT_PARENT_DIR="/etc/kubernetes"

# k8s 证书 目录
export K8S_CERT_DIR="$K8S_CERT_PARENT_DIR/cert"

# docker 数据目录
export DOCKER_DIR="/data/k8s/docker"

# kube-nginx软件目录
export KUBE_NGINX_DIR="/usr/local/kube-nginx"

# flanneld 证书 目录
export FLANNEL_CERT_DIR="/etc/flanneld/cert"

# apiserver集群服务地址ip
export APISERVER_IPS=$ETCD_IPS

# controller-manager集群服务地址ip
export CONTROLLER_MANAGER_IPS=$ETCD_IPS

# scheduler集群服务地址ip
export SCHEDULER_MANAGER_IPS=$ETCD_IPS

#### 以下参数一般不需要修改 ####

# 生成 EncryptionConfig 所需的加密 key
export ENCRYPTION_KEY=$(head -c 32 /dev/urandom | base64)

# TLS Bootstrapping 使用的 Token，可以使用命令 head -c 16 /dev/urandom | od -An -t x | tr -d ' ' 生成
BOOTSTRAP_TOKEN="e077a933247441b34438b55389fd84d7c5"

# 最好使用 当前未用的网段 来定义服务网段和 Pod 网段

# 服务网段，部署前路由不可达，部署后集群内路由可达(kube-proxy 保证)
SERVICE_CIDR="10.254.0.0/16"

# Pod 网段，建议 /16 段地址，部署前路由不可达，部署后集群内路由可达(flanneld 保证)
CLUSTER_CIDR="10.233.0.0/16"

# 服务端口范围 (NodePort Range)
export NODE_PORT_RANGE="30000-32767"

# flanneld 网络配置前缀
export FLANNEL_ETCD_PREFIX="/k8s/network"

# kubernetes 服务 IP (一般是 SERVICE_CIDR 中第一个IP)
export CLUSTER_KUBERNETES_SVC_IP="10.254.0.1"

# 集群 DNS 服务 IP (从 SERVICE_CIDR 中预分配)
export CLUSTER_DNS_SVC_IP="10.254.0.2"

# 集群 DNS 域名（末尾不带点号）
export CLUSTER_DNS_DOMAIN="cluster.local"

# 将二进制目录 /opt/k8s/bin 加到 PATH 中
export PATH=$K8S_BIN_DIR:$PATH

