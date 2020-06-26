#!/bin/sh
##author julian##
##date 2019-10-23##
####新的节点加入集群####
#获取当前执行脚本的目录
FILENAME=$0
DIRNAME=${FILENAME%/*}
source $DIRNAME/00-environment.sh

###1.系统基础优化
function optimize(){
	## 解决出现不存在RPM-GPG-KEY-EPEL-7
	cd /etc/pki/rpm-gpg
	wget https://archive.fedoraproject.org/pub/epel/RPM-GPG-KEY-EPEL-7
	curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
	yum install -y epel-release
	yum install -y vim conntrack ntpdate ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget unzip openssh-clients*
}

###4.关闭防火墙
function stop_firewalld(){
	##停止firewalld服务
	systemctl stop firewalld
	##设置开机禁用防火墙
	systemctl disable firewalld

	systemctl stop  iptables.service

	##清除所有规则
	iptables -F && iptables -F -t nat
	##清除用户自定义规则
	iptables -X && iptables -X -t nat
	##刷新Forward跟accept规则
	iptables -P FORWARD ACCEPT
}

###5.关闭swap
function swap_off(){
	##禁用 /proc/swaps 中的所有交换区
	swapoff -a
	##注释掉/etc/fstab中所有 swap 的行
	sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
}

###6.关闭SELinux
function close_selinux(){
	##临时关闭
	setenforce 0  
	##修改SELINUX=disabled 永久关闭
	sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
	sed -i 's@SELINUX=enforcing@SELINUX=disabled@g' /etc/sysconfig/selinux
}

###7.加载内核模块
function load_core(){
	modprobe ip_vs_rr
	modprobe br_netfilter
}

###8.优化内核参数
function core_optimize(){
	##建一个文件夹用来存放k8s相关的文件
	mkdir -p $K8S_WORK_DIR
	cat > $K8S_WORK_DIR/kubernetes.conf <<EOF
net.bridge.bridge-nf-call-iptables=1
net.bridge.bridge-nf-call-ip6tables=1
net.ipv4.ip_forward=1
net.ipv4.tcp_tw_recycle=0  ##必须关闭 tcp_tw_recycle，否则和 NAT 冲突，会导致服务不通；
vm.swappiness=0  ##禁止使用 swap 空间，只有当系统 OOM 时才允许使用它
vm.overcommit_memory=1  ##不检查物理内存是否够用
vm.panic_on_oom=0  ##开启 OOM
fs.inotify.max_user_instances=8192
fs.inotify.max_user_watches=1048576
fs.file-max=52706963
fs.nr_open=52706963
net.ipv6.conf.all.disable_ipv6=1  ##关闭 IPV6，防止触发 docker BUG；
net.netfilter.nf_conntrack_max=2310720
EOF
	
	##移动参数文件
	cp $K8S_WORK_DIR/kubernetes.conf  /etc/sysctl.d/kubernetes.conf
	##读取配置文件，使参数生效
	sysctl -p /etc/sysctl.d/kubernetes.conf
}

###9.设置系统时区
function set_date(){
	## 同步服务器时间
	systemctl enable ntpd
	systemctl restart ntpd
	##调整系统 TimeZone
	timedatectl set-timezone Asia/Shanghai
	##将当前的 UTC 时间写入硬件时钟
	timedatectl set-local-rtc 0
	##重启依赖于系统时间的服务
	systemctl restart rsyslog && systemctl restart crond
}

###10.关闭无关的服务
function colse_service(){
	systemctl stop postfix && systemctl disable postfix
}

###11.设置 rsyslogd 和 systemd journald 日志
function set_logs(){
	##持久化保存日志的目录
	mkdir -p /var/log/journal
	mkdir -p /etc/systemd/journald.conf.d
	cat > /etc/systemd/journald.conf.d/99-prophet.conf <<EOF
[Journal]
# 持久化保存到磁盘
Storage=persistent

# 压缩历史日志
Compress=yes

SyncIntervalSec=5m
RateLimitInterval=30s
RateLimitBurst=1000

# 最大占用空间 10G
SystemMaxUse=10G

# 单日志文件最大 200M
SystemMaxFileSize=200M

# 日志保存时间 2 周
MaxRetentionSec=2week

# 不将日志转发到 syslog
ForwardToSyslog=no
EOF
	##重启服务
	systemctl restart systemd-journald
}

###12.创建相关目录
function mdkir_work(){
	mkdir -p  $K8S_WORK_DIR $K8S_BIN_DIR $K8S_CERT_DIR $ETCD_CERT_DIR
}

###13.升级内核
function upgrade_core(){
	## 获取内核版本
	kernel_num=`uname -r`
	kernel_num=${kernel_num:0:1}
	## 如果内核版本为3.x版本,升级内核
	if [ "${kernel_num}" == 3 ]; then
		echo "升级内核版本,3.X版本升级为4.X"
	    ##安装完成后检查 /boot/grub2/grub.cfg 中对应内核 menuentry 中是否包含 initrd16 配置，如果没有，再安装一次！
	    #rpm --import https://www.elrepo.org/RPM-GPG-KEY-elrepo.org
	    rpm -Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
	    yum --enablerepo=elrepo-kernel install -y kernel-lt
	    ##设置开机从新内核启动
	    grub2-set-default 0
	fi
}

###14.关闭 NUMA
function close_numa(){
	##在 GRUB_CMDLINE_LINUX 一行添加 `numa=off` 参数，如下所示：
	sed -i 's/GRUB_CMDLINE_LINUX=\"/&numa=off /' /etc/default/grub
	##重新生成 grub2 配置文件：
	cp /boot/grub2/grub.cfg{,.bak}
	grub2-mkconfig -o /boot/grub2/grub.cfg
}

###15.修改hosts文件
function update_hosts(){
	sed -i '$a\'"$HOSTS"'' /etc/hosts
	##因为HOSTS中参数是用'='号隔开的，应该替换成空格
	sed -i 's/=/ /' /etc/hosts
}


###17.创建docker账号
function create_user_docker(){
	useradd -m docker
}

###18.更新 PATH 变量
function update_path(){
	echo "PATH=$PATH" >>/root/.bashrc
	source /root/.bashrc
}

###19.安装sshpass
function setUp_sshpass(){
	rpm -ivh http://mirror.centos.org/centos/7/extras/x86_64/Packages/sshpass-1.06-2.el7.x86_64.rpm
}


function main(){
	optimize
	stop_firewalld
	swap_off
	close_selinux
	load_core
	core_optimize
	set_date
	colse_service
	set_logs
	mdkir_work
	upgrade_core
	close_numa
	update_hosts
	create_user_docker
	update_path
	setUp_sshpass
	echo "系统初始化完成，请输入reboot重启服务器！"
}

main "$@"
