# libvirt
# KVM/QEMU虚拟化环境和开发环境搭建、
## 构建KVM环境
### 打开CPU虚拟化支持
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121971715-b94d7e00-2f06-4b04-8475-9f36356b9b2e.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u3093ec75&margin=%5Bobject%20Object%5D&name=image.png&originHeight=420&originWidth=726&originalType=binary&ratio=1&rotation=0&showTitle=false&size=30153&status=done&style=none&taskId=uccc87163-5273-4bdc-b275-e1075acfe97&title=)
### 查看系统版本
```bash
cat /etc/issue
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121995751-dcb376eb-2bc0-4036-8c99-7dab128e2f95.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u9292cda3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=53&originWidth=367&originalType=binary&ratio=1&rotation=0&showTitle=false&size=48901&status=done&style=none&taskId=u0c5272c8-fd4c-46dd-844b-d208768d1d5&title=)
### 查看内核版本
```bash
uname -r
```
### 查看CPU虚拟化支持
```bash
grep -E '(vmx|svm)' /proc/cpuinfo
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122030188-62af5978-2084-49f3-baf9-1c85ba931d49.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u3cb17e6d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=554&originWidth=1035&originalType=binary&ratio=1&rotation=0&showTitle=false&size=1302981&status=done&style=none&taskId=ue86ae078-4fde-461d-b4eb-171f2adc094&title=)
### 查看KVM模块
```bash
lsmod|grep kvm
ls -l /dev/kvm
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122051475-b4d2adb4-34c3-418f-a6f4-c7a72ff4986d.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u0125a957&margin=%5Bobject%20Object%5D&name=image.png&originHeight=42&originWidth=492&originalType=binary&ratio=1&rotation=0&showTitle=false&size=47928&status=done&style=none&taskId=u3477c151-0c25-4765-b2e7-6624b996098&title=)
## **安装QEMU**
### 下载和解压，选定合适目录使用命令
```bash
cd /usr/local
# wget下载
wget https://download.qemu.org/qemu-6.2.0.tar.xz
# 解压
tar -Jxvf qemu-6.2.0.tar.xz
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121628641-51a2edd0-62d4-4951-9adb-94f387b863ba.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ud04d2c0b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=163&originWidth=867&originalType=binary&ratio=1&rotation=0&showTitle=false&size=353586&status=done&style=none&taskId=uaa3df2b0-1f68-4f3c-ba88-2603762b719&title=)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121631864-f2f75f63-d95a-4c19-a6c1-d6d5f2127304.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ufd211b3e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=173&originWidth=522&originalType=binary&ratio=1&rotation=0&showTitle=false&size=230866&status=done&style=none&taskId=ua3ab7cd5-9dd9-4363-a1b3-437bf600f4a&title=)
### 直接运行代码仓库中configure 文件进行配置
```bash
cd qemu-6.2.0
./configure
```
配置失败的尝试安装以下几个包
```bash
apt-get install zlib1g zlib1g-dev 
apt install libglib2.0-dev
apt-get install libpixman-1-dev
apt install ninja-build
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121682449-f4dc39aa-5aa2-4375-a99b-2ded0857afab.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u7e8dcebc&margin=%5Bobject%20Object%5D&name=image.png&originHeight=201&originWidth=416&originalType=binary&ratio=1&rotation=0&showTitle=false&size=180945&status=done&style=none&taskId=u964043a2-6df0-4777-904b-7bc3eb8437f&title=)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121684433-1d709d2b-69b1-458a-ae7e-16375b395c55.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u3290f18e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=272&originWidth=415&originalType=binary&ratio=1&rotation=0&showTitle=false&size=274631&status=done&style=none&taskId=u34ca5269-e081-47de-bea2-95e09e840a3&title=)
### qemu的编译与安装
```bash
# 多线程编译
make -j 50
# 安装
make install | tee make-install.log
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121805342-44930b3d-1f08-48ee-90dc-30704c6bddaf.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u7e77b4c2&margin=%5Bobject%20Object%5D&name=image.png&originHeight=107&originWidth=138&originalType=binary&ratio=1&rotation=0&showTitle=false&size=4164&status=done&style=none&taskId=u27e435e8-2094-49da-9efe-f5aba7ab2ea&title=)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121810540-9fc7ba57-59d1-4364-bd12-b355af7a7396.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u1daf3181&margin=%5Bobject%20Object%5D&name=image.png&originHeight=402&originWidth=701&originalType=binary&ratio=1&rotation=0&showTitle=false&size=654665&status=done&style=none&taskId=u86a7b3a6-4599-4746-9f45-bb66deb6df9&title=)
### 查看Qemu 
```bash
安装成功后，查看qemu提供的工具(按两次Tab键给出以qemu-开头的命令)
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121850298-e169b610-659b-431b-ad3f-6cca14e0dc8a.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ub2c4a369&margin=%5Bobject%20Object%5D&name=image.png&originHeight=312&originWidth=1091&originalType=binary&ratio=1&rotation=0&showTitle=false&size=795751&status=done&style=none&taskId=ub7e97316-fec6-493c-8fb3-d96de6e375e&title=)
```bash
which qemu-img
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655121867239-47b0b7e2-a38d-4090-9248-271896665f65.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ue77c901f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=40&originWidth=602&originalType=binary&ratio=1&rotation=0&showTitle=false&size=61857&status=done&style=none&taskId=uc1c96cc5-2d6d-48fb-bf51-3708c5f58a9&title=)
## 安装虚拟化管理工具
### libvirt的安装
```bash
# 适用于18
apt-get install libvirt-bin
apt-get install libvirt-dev

# 适用于20
apt-get install qemu-kvm libvirt-daemon-system libvirt-clients bridge-utils
apt-get install libvirt-dev
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122261675-c784b5e0-063c-40c8-95f2-31459d2800cd.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u7b2d189a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=369&originWidth=905&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110258&status=done&style=none&taskId=u8d59bb22-b235-4d6d-a60e-e228533f026&title=)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122264014-a68c0385-c349-4932-8992-3eaddf481a15.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u28135671&margin=%5Bobject%20Object%5D&name=image.png&originHeight=398&originWidth=904&originalType=binary&ratio=1&rotation=0&showTitle=false&size=132870&status=done&style=none&taskId=ub1743acf-33ca-4f97-8d38-d17c7452ceb&title=)
```bash
# 查看libvirtd的版本号
libvirtd --version
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122315982-134d5cba-a7b8-44ae-8a44-a145c7dc46c4.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u9110af8c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=37&originWidth=521&originalType=binary&ratio=1&rotation=0&showTitle=false&size=7295&status=done&style=none&taskId=ua488ff5f-1417-4ce0-9d47-59e1fd2d4d9&title=)
### virt-manager的安装
```bash
# virt-manager的安装
apt-get install virt-manager
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122338919-322c0746-2e87-47c0-bc6a-0d4f8e9998f4.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ue71d01e7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=379&originWidth=720&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110186&status=done&style=none&taskId=u1c7427cd-af25-435d-b9cc-acf6e188646&title=)
```bash
# 查看版本号
virt-manager --version
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122363072-ea1eea0b-54fa-4670-afc5-796d6f5db22b.png#clientId=u167399d8-ffdd-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u0a007bbd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=34&originWidth=574&originalType=binary&ratio=1&rotation=0&showTitle=false&size=5903&status=done&style=none&taskId=u4d77e747-abe2-4949-9892-41f3b1fbf0f&title=)
## 配置IDEA集成开发环境
### 安装IDEA
#### 下载
```c
# 下载链接
https://www.jetbrains.com/idea/download/other.html
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1653446685109-d78ea25a-75ab-488b-b914-8ca38952b0a5.png#clientId=ucfd8004a-358c-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=617&id=uc41ac8e3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=617&originWidth=990&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110508&status=done&style=none&taskId=u238f838d-5e41-43bc-8cdb-dc8e05fff57&title=&width=990)
#### 移动到安装目录并解压
```c
tar -zxvf ideaIU-2022.1.1.tar.gz
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1653447087973-36d7c3ef-1596-4597-a17f-fea8952e333b.png#clientId=ucfd8004a-358c-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=160&id=ucbae2ade&margin=%5Bobject%20Object%5D&name=image.png&originHeight=160&originWidth=697&originalType=binary&ratio=1&rotation=0&showTitle=false&size=40044&status=done&style=none&taskId=uc31c9288-3437-4793-993d-888d73fd2a2&title=&width=697)
#### 赋权限并且运行
```c
# 赋权限
chmod 755 -R idea-IU-221.5591.52/

# 运行 
cd /home/wyl/idea/idea-IU-221.5591.52/bin
./idea.sh
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1653447273581-f66cc7f8-b810-4131-90b1-1e4c31994cd1.png#clientId=ucfd8004a-358c-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=21&id=u6c46d00c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=21&originWidth=691&originalType=binary&ratio=1&rotation=0&showTitle=false&size=6318&status=done&style=none&taskId=uf975a76c-f99b-49ba-9af9-11e5c81569c&title=&width=691)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1653447282683-0d93ac97-a122-49be-bc18-4e9603ef1487.png#clientId=ucfd8004a-358c-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=145&id=ubb6ea4dd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=145&originWidth=737&originalType=binary&ratio=1&rotation=0&showTitle=false&size=35972&status=done&style=none&taskId=u0aae6b48-449b-4f4b-8589-ddea9218b3a&title=&width=737)
### 配置JDK
#### 下载
使用IDEA自带的JDK下载工具，选择合适的版本
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122774897-d04604b2-0bd8-4b78-b669-f84d1010df4e.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=227&id=u8a82f9ef&margin=%5Bobject%20Object%5D&name=image.png&originHeight=227&originWidth=533&originalType=binary&ratio=1&rotation=0&showTitle=false&size=18018&status=done&style=none&taskId=u48a3efca-011b-4f34-a12c-e162f3e990d&title=&width=533)
#### 配置
选择刚刚下载好的JDK路径即可
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122812957-9cbeb88b-ee88-4005-8180-59d51b569500.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=743&id=uef0296eb&margin=%5Bobject%20Object%5D&name=image.png&originHeight=743&originWidth=1038&originalType=binary&ratio=1&rotation=0&showTitle=false&size=131226&status=done&style=none&taskId=u79a408ae-6327-40b2-a238-b63e29f3c8c&title=&width=1038)
### 配置Maven
#### 选择IDEA自带的maven配置即可
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655122876093-f2d3eacf-9742-463c-9943-5efc250671d4.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=741&id=ub9e91862&margin=%5Bobject%20Object%5D&name=image.png&originHeight=741&originWidth=1038&originalType=binary&ratio=1&rotation=0&showTitle=false&size=99456&status=done&style=none&taskId=ubbeea223-f918-414b-9b38-02a9128d3c7&title=&width=1038)
### 配置Git
#### 安装Git
```bash
sudo apt-get install git
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655123245647-8675ac18-5e92-40ba-9bb1-c0a783448482.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=77&id=ud5c8bfc4&margin=%5Bobject%20Object%5D&name=image.png&originHeight=77&originWidth=693&originalType=binary&ratio=1&rotation=0&showTitle=false&size=25049&status=done&style=none&taskId=u0a340c4f-903e-42be-a989-b5a37b9e486&title=&width=693)
#### 配置git
```bash
git config --global user.name  "pepsi-wyl"
git config --global user.email "2322535585@qq.com"
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655123229927-a7d8f39a-4643-44bd-bb2a-7c840b05b1ef.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=39&id=ucda81ff5&margin=%5Bobject%20Object%5D&name=image.png&originHeight=39&originWidth=804&originalType=binary&ratio=1&rotation=0&showTitle=false&size=12800&status=done&style=none&taskId=u6c55d968-93ce-4c4a-8d45-03ca985c7cf&title=&width=804)
#### IDAE配置Git
选择合适的路径点击test 成功则会显示Git的版本号
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655123169142-e65d7b36-281b-4e17-ad65-c14fe9090034.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=739&id=u049e40c8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=739&originWidth=1042&originalType=binary&ratio=1&rotation=0&showTitle=false&size=107241&status=done&style=none&taskId=u803ab9d7-e992-4a81-9e44-6aa0e125f0c&title=&width=1042)
# **需求分析**
## 功能要求
```bash
登陆界面和web端管理
宿主机详细信息
Libvirt连接信息
虚拟机信息、创建、启动、挂起、还原、保存、恢复、关闭、强制关闭、重启、删除
快照信息、创建、删除、使用
镜像信息、添加、删除、下载
存储池信息、创建、删除
网络断开和连接
```
## 程序功能模块
```bash
1.LibvirtUtils      获取连接和连接信息
2.SigarUtils        获取宿主机信息
3.LibvirtService    管理虚拟机的一系列操作
4.LibvirtController 处理前端页面请求调用LibvirtService完成对虚拟机的业务
5.UserController    处理前端页面请求完成登陆逻辑
6.HTML页面           展示数据和方便操作
```
# 系统设计
```bash
云平台管理系统是对虚拟机进行管理和操作，
该系统采用Libvirt Java API 进行分析和设计
，使用编程语言java作为开发语言，使用IDEA作为开发平台，
通过SpringBoot+Thymeleaf框架实现功能，能够很好的实现系统的开发及测试。
```
## 虚拟机基础管理
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655128181824-a904425c-2b77-4bcb-b306-2208c39437c8.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=809&id=ue63cdbc7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=809&originWidth=722&originalType=binary&ratio=1&rotation=0&showTitle=false&size=51633&status=done&style=none&taskId=u736d4639-faaa-4323-85f3-4530e8a08ad&title=&width=722)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655128394534-797e7346-602f-42cd-a5c1-1a01f2837a5a.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=499&id=u9d7f7143&margin=%5Bobject%20Object%5D&name=image.png&originHeight=499&originWidth=850&originalType=binary&ratio=1&rotation=0&showTitle=false&size=24776&status=done&style=none&taskId=u0861d6d8-6626-4cfc-a7fc-8d3a65e7a47&title=&width=850)
## 虚拟机镜像管理
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655128630964-aa0623b5-d641-4e06-9220-e691cc25fe56.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=472&id=u9fb9c8ba&margin=%5Bobject%20Object%5D&name=image.png&originHeight=472&originWidth=716&originalType=binary&ratio=1&rotation=0&showTitle=false&size=18645&status=done&style=none&taskId=u6a155cd9-68cc-4958-857b-7e6d114e569&title=&width=716)
## 虚拟机存储池管理
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655128812444-ffaf4ec6-4b62-44a1-8227-85f25311ea25.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=331&id=u139006f1&margin=%5Bobject%20Object%5D&name=image.png&originHeight=331&originWidth=818&originalType=binary&ratio=1&rotation=0&showTitle=false&size=13971&status=done&style=none&taskId=u40530651-a4be-482d-a5c7-786bbac3100&title=&width=818)
## 虚拟机网络管理
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655128888151-dba3c578-4b1e-4aa8-b163-0cc9f443169b.png#clientId=uccb0f305-3ba3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=302&id=ub09d593e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=302&originWidth=742&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11575&status=done&style=none&taskId=u48a9e54d-1b1e-49e6-a296-fb07d204e9c&title=&width=742)
# 系统实现
## 功能实现
### libvirt连接及其连接信息
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173140991-2674e3a1-d5ee-43d9-ae9b-43080e87e575.png#clientId=u27bfc1e7-8204-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=417&id=uc1a97a98&margin=%5Bobject%20Object%5D&name=image.png&originHeight=417&originWidth=844&originalType=binary&ratio=1&rotation=0&showTitle=false&size=72621&status=done&style=none&taskId=uef6a08dc-1b70-4c33-9ed4-226277aeca8&title=&width=844)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173191474-de0e5e26-f341-4f12-9883-98f0d88c5057.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=302&id=u551bb4e8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=302&originWidth=706&originalType=binary&ratio=1&rotation=0&showTitle=false&size=36316&status=done&style=none&taskId=u26ffb77e-2c9d-4849-a9f1-e3624554e4f&title=&width=706)
### 主机信息
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173244672-2926f670-8406-49f6-8d38-5181643cb0e3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=735&id=u401b61b7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=735&originWidth=691&originalType=binary&ratio=1&rotation=0&showTitle=false&size=86316&status=done&style=none&taskId=ubac388ea-6af9-46e6-a185-481000d6ce0&title=&width=691)
### 通过name或者id获取Domain
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173308317-8b88785d-92b1-44ef-a0c2-28df32a2e158.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=368&id=u3209fa65&margin=%5Bobject%20Object%5D&name=image.png&originHeight=368&originWidth=711&originalType=binary&ratio=1&rotation=0&showTitle=false&size=41115&status=done&style=none&taskId=uf93ed0b4-cc8a-4738-975e-f51fe53e93c&title=&width=711)
### 通过name或者id获取虚拟机信息
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174094891-aca809b4-7120-433b-a2d8-0d9a3b259a19.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=326&id=ue638e4f1&margin=%5Bobject%20Object%5D&name=image.png&originHeight=326&originWidth=651&originalType=binary&ratio=1&rotation=0&showTitle=false&size=35015&status=done&style=none&taskId=u8bfa2cdb-b1d2-4445-8d00-af61dfbc76f&title=&width=651)
### 获取虚拟机列表
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173338835-8334835b-2885-4c3a-9f66-9ab9ae220484.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=372&id=u149100a7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=372&originWidth=761&originalType=binary&ratio=1&rotation=0&showTitle=false&size=46683&status=done&style=none&taskId=uc376fb4b-e7ea-4825-b49c-22c81ba96f4&title=&width=761)
### 启动虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173396908-22b55b45-f22d-437c-8c31-0b7bfce78cf1.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=305&id=u0c7b6a3f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=305&originWidth=845&originalType=binary&ratio=1&rotation=0&showTitle=false&size=42379&status=done&style=none&taskId=ud8ed43de-24be-4d1c-99e3-cdc8888f4b4&title=&width=845)
### 关闭虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173386356-04529824-a9a8-46f7-a70b-fd55e4473826.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=358&id=u5cfda9aa&margin=%5Bobject%20Object%5D&name=image.png&originHeight=358&originWidth=843&originalType=binary&ratio=1&rotation=0&showTitle=false&size=53071&status=done&style=none&taskId=u8718c4f0-be94-4362-9906-cdbf3266e94&title=&width=843)
### 强制关闭虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173413644-dc48e87a-a26b-4f63-8ad4-0ca0cd6f6b91.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=342&id=u841cfbb8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=342&originWidth=855&originalType=binary&ratio=1&rotation=0&showTitle=false&size=55264&status=done&style=none&taskId=u1ba208b2-894d-40cc-b76e-d3dd325646e&title=&width=855)
### 重启虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173437881-3a1e4674-5d5b-4ce2-b50a-b1fe32ace6d4.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=355&id=uffe15659&margin=%5Bobject%20Object%5D&name=image.png&originHeight=355&originWidth=796&originalType=binary&ratio=1&rotation=0&showTitle=false&size=52015&status=done&style=none&taskId=u95bd98c1-4477-43fe-b77f-22fd04cce7c&title=&width=796)
### 挂起虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173462426-2ef1dc2f-3965-43e8-affb-57ebc606a864.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=368&id=u1b96ae0a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=368&originWidth=843&originalType=binary&ratio=1&rotation=0&showTitle=false&size=52880&status=done&style=none&taskId=ue923c4c9-5a15-4eff-a9f3-3054d8f4d91&title=&width=843)
### 还原虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173478164-76ce9547-ad28-450e-82ed-61cb560fc2af.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=362&id=u37ee2443&margin=%5Bobject%20Object%5D&name=image.png&originHeight=362&originWidth=813&originalType=binary&ratio=1&rotation=0&showTitle=false&size=52793&status=done&style=none&taskId=u5a08d6a5-4e73-4ed8-a943-97bad7b65cd&title=&width=813)
### 保存虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173498124-ada63e5e-8279-4833-a905-3d1b002d0b4c.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=455&id=u4adf48c1&margin=%5Bobject%20Object%5D&name=image.png&originHeight=455&originWidth=818&originalType=binary&ratio=1&rotation=0&showTitle=false&size=79011&status=done&style=none&taskId=ub978c856-bbd2-4565-9417-01293f8d633&title=&width=818)
### 恢复虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173514276-66ad59ab-810b-40f9-a634-b98992ffb226.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=476&id=u70069d7b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=476&originWidth=824&originalType=binary&ratio=1&rotation=0&showTitle=false&size=74650&status=done&style=none&taskId=u762b5030-1424-45de-add6-2c9d02a10d3&title=&width=824)
### 创建虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173557639-b6848eca-2b30-4f77-a4b9-9f4c5420b4c7.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=709&id=ub63b0a24&margin=%5Bobject%20Object%5D&name=image.png&originHeight=709&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=99980&status=done&style=none&taskId=ub8fe62a4-9a4c-4f24-aa78-bf2e03d7512&title=&width=939)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173578387-d5ab29ee-25ce-4c21-ad8d-13660a5bef6d.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=386&id=u5ff91d63&margin=%5Bobject%20Object%5D&name=image.png&originHeight=386&originWidth=762&originalType=binary&ratio=1&rotation=0&showTitle=false&size=49761&status=done&style=none&taskId=ubdaaa033-b414-442a-89d8-f8b56376aec&title=&width=762)
### 删除虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173587446-9fe5659b-0e4f-45f4-86c6-5db50f65dc23.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=292&id=u96150d5b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=292&originWidth=769&originalType=binary&ratio=1&rotation=0&showTitle=false&size=36456&status=done&style=none&taskId=u3567089b-ed3b-4693-95b1-cf93ad8c361&title=&width=769)
### 获取镜像列表
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173613588-343dae3c-c2ff-4f0a-a00f-8c79c70858fe.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=348&id=u04010390&margin=%5Bobject%20Object%5D&name=image.png&originHeight=348&originWidth=753&originalType=binary&ratio=1&rotation=0&showTitle=false&size=33792&status=done&style=none&taskId=u5baca986-16ae-4c8d-a72c-2778dc189b3&title=&width=753)
### 添加镜像文件
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173627288-2074fab3-630d-4530-a956-9613ca9d64ef.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=279&id=u93fa1327&margin=%5Bobject%20Object%5D&name=image.png&originHeight=279&originWidth=706&originalType=binary&ratio=1&rotation=0&showTitle=false&size=31828&status=done&style=none&taskId=u3bf4bd51-ecae-4f07-bb6c-1584a6eff9d&title=&width=706)
### 下载镜像文件
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173646471-7357ea03-70cc-42e4-8d86-cde110c22d06.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=423&id=u848584f2&margin=%5Bobject%20Object%5D&name=image.png&originHeight=423&originWidth=905&originalType=binary&ratio=1&rotation=0&showTitle=false&size=53040&status=done&style=none&taskId=u2ce8eaf2-f7af-45cd-8ba7-8406e354a7b&title=&width=905)
### 删除镜像文件
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173664864-8da76a81-3f07-454d-8519-cff8149694e3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=271&id=ue2e5dd2e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=271&originWidth=685&originalType=binary&ratio=1&rotation=0&showTitle=false&size=28649&status=done&style=none&taskId=ucdd5b583-210a-4563-8532-cecb638ed0f&title=&width=685)
### 获取网络状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173713723-a1fed8ca-390b-4ffb-92f2-b4a1aa413153.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=218&id=u6db752bf&margin=%5Bobject%20Object%5D&name=image.png&originHeight=218&originWidth=761&originalType=binary&ratio=1&rotation=0&showTitle=false&size=23990&status=done&style=none&taskId=u0ef54d1d-d770-48fd-a6ca-24c2ba9fa67&title=&width=761)
### 关闭网络
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173732781-336055e6-3566-44a8-8a26-5ede68e57c22.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=290&id=uaafcb8d6&margin=%5Bobject%20Object%5D&name=image.png&originHeight=290&originWidth=649&originalType=binary&ratio=1&rotation=0&showTitle=false&size=42152&status=done&style=none&taskId=u6841df22-7912-4c65-8df1-4c1b9f85965&title=&width=649)
### 开启网络
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173742479-a50a5718-a01d-4704-927d-51775dad8bdf.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=281&id=u92fbfdbb&margin=%5Bobject%20Object%5D&name=image.png&originHeight=281&originWidth=658&originalType=binary&ratio=1&rotation=0&showTitle=false&size=41309&status=done&style=none&taskId=uc7db987e-c434-4a95-a0d2-856c9d0d3c7&title=&width=658)
### 获取快照列表
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173789496-74dfc5aa-e6ae-4eb1-b292-fb8d3799e134.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=478&id=u2a0f311d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=478&originWidth=841&originalType=binary&ratio=1&rotation=0&showTitle=false&size=58693&status=done&style=none&taskId=ub19d2e46-3338-4fe0-a0b8-8a989da2ef5&title=&width=841)
### 快照管理
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173845323-c399f785-18b6-49a2-9e93-3a46b4ffa208.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=365&id=u5b7f1932&margin=%5Bobject%20Object%5D&name=image.png&originHeight=365&originWidth=559&originalType=binary&ratio=1&rotation=0&showTitle=false&size=38188&status=done&style=none&taskId=u9d4dda1a-921f-4d07-adfd-3de7ad7a8b4&title=&width=559)
### 创建快照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173861483-2c4d517c-02b2-4833-9f98-19d60c1c59fb.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=192&id=ub38b830a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=192&originWidth=632&originalType=binary&ratio=1&rotation=0&showTitle=false&size=25352&status=done&style=none&taskId=u1e2b80a4-0fcf-4255-b2b3-1b587c337ff&title=&width=632)
### 删除快照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173875594-097beb8d-5e52-4d68-9d61-e7a897c35f77.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=198&id=u261da403&margin=%5Bobject%20Object%5D&name=image.png&originHeight=198&originWidth=611&originalType=binary&ratio=1&rotation=0&showTitle=false&size=25356&status=done&style=none&taskId=uff580086-85ce-4a2d-a22d-86a9eaf1f23&title=&width=611)
### 启动快照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655173889071-f141ca74-164f-4955-9eb5-865d9d794fa9.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=187&id=ue5f351fb&margin=%5Bobject%20Object%5D&name=image.png&originHeight=187&originWidth=649&originalType=binary&ratio=1&rotation=0&showTitle=false&size=25067&status=done&style=none&taskId=uaebabf91-99a7-458f-9105-d10b915885c&title=&width=649)
### 获取存储池列表
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174000895-d06e4ca6-b80f-499f-bc81-c29270e16eb2.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=552&id=u1a5491bc&margin=%5Bobject%20Object%5D&name=image.png&originHeight=552&originWidth=905&originalType=binary&ratio=1&rotation=0&showTitle=false&size=84248&status=done&style=none&taskId=u8805ecbd-12cd-4d6c-96c0-a1183acd3d2&title=&width=905)
### 创建存储池
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174026649-537832c8-4da9-4d18-a688-441d900903b2.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=404&id=uf29ed92f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=404&originWidth=759&originalType=binary&ratio=1&rotation=0&showTitle=false&size=46045&status=done&style=none&taskId=u2fb106f0-dd8e-427d-8d17-9c9bc9a5d2c&title=&width=759)
### 删除存储池
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174040392-ce109c47-fd76-491c-8a99-fbcde0365ab6.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=201&id=u176be909&margin=%5Bobject%20Object%5D&name=image.png&originHeight=201&originWidth=651&originalType=binary&ratio=1&rotation=0&showTitle=false&size=27761&status=done&style=none&taskId=uf02bf8e3-aa89-4808-975c-26601fa2760&title=&width=651)
## 功能展示
### 登陆界面
账号密码都是admin 输入即可完成登陆
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174210360-776b3ef2-1208-4cf4-8b2d-64381ae21ed5.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u7117aeda&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=72492&status=done&style=none&taskId=u028a848d-ca63-49fb-94ed-cfd7b57f91c&title=&width=1235)
### 首页界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174250924-6f7de432-b25e-406c-9be9-28f19161e2b1.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u7e20a0f7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=143592&status=done&style=none&taskId=ubee3732a-19e9-484a-b5fd-99059c7e82a&title=&width=1235)
### 注销登陆
点击注销登陆即可回到登陆界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174519296-47a86b4d-899d-45b4-9bb4-e9ce8d64e989.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=J5mvZ&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=137901&status=done&style=none&taskId=ub7ce4ec7-bdf1-440a-8353-89129d42a58&title=&width=1235)
### 宿主机信息
点击宿主机详细信息即可查看信息
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174318932-85c00c6f-448c-4805-ab5e-080ae0b2b072.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=uf324dec8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=147057&status=done&style=none&taskId=u9f9b4c02-567b-414e-8bc3-77544b7d58c&title=&width=1235)
### Libvirt连接信息
点击Libvirt连接信息即可查看信息
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174407083-4aeecd19-d07a-4607-a661-230f623a579a.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=uc2485c7f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=121273&status=done&style=none&taskId=uc9553f01-cc81-4f41-bd53-ad607954315&title=&width=1235)
### 虚拟机管理界面
点击云平台管理即可进入管理界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174461435-2999c302-81f9-40f2-8df2-2b209c6ef6f7.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=uba550168&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=104038&status=done&style=none&taskId=u85e18661-c07a-43b0-afb9-5929b610a53&title=&width=1235)
点击首页可以回到首页
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174627225-bd903075-3848-4cbe-80ca-a23d657bc5c7.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u60da09b1&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106178&status=done&style=none&taskId=uace963a5-8217-4ffd-b213-f2cf68f56b0&title=&width=1235)
### 注销登陆
点击注销登陆即可回到登陆界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174636228-0fadbd7f-7b8d-4a97-9c96-3dfc46faa43f.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u962f5fd9&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106163&status=done&style=none&taskId=u242ac7ce-dfad-4897-8d67-23ea4530ef9&title=&width=1235)
### 网络打开与关闭
点击网络状态即可实现网络的打开与关闭
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174699244-1f6490f0-53b5-4436-8146-26c0492c63ff.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=63&id=uf942d568&margin=%5Bobject%20Object%5D&name=image.png&originHeight=63&originWidth=1167&originalType=binary&ratio=1&rotation=0&showTitle=false&size=15346&status=done&style=none&taskId=uec03e0a8-ca2f-46ff-bbcf-efc5dc7ec25&title=&width=1167)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175910046-b284997b-e5a9-4ff2-b4b6-f7cd207e4af3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u911964a9&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=138761&status=done&style=none&taskId=ub9b9ab2d-2523-4671-9005-9e218163a19&title=&width=939)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174713262-ff89d7e7-2dcd-4eaf-865a-6229d90c8979.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=50&id=u4e6cd86c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=50&originWidth=1179&originalType=binary&ratio=1&rotation=0&showTitle=false&size=15300&status=done&style=none&taskId=ubdbf893c-c5a1-409e-9948-0a8654a3478&title=&width=1179)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175895757-feb8deee-b259-47af-a623-2528932c80c6.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ua1176b18&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=142426&status=done&style=none&taskId=ua63d8d5a-d25b-49a4-bd4b-f66be5be953&title=&width=939)
### 虚拟机添加
点击添加虚拟机按钮即可跳转到添加界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176486519-abb071ba-6722-4b1c-9d2e-d23596efda75.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=62&id=uf72eb5fd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=62&originWidth=891&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14268&status=done&style=none&taskId=ucf743c6c-c0cc-44ed-9033-0ed1ab15d3e&title=&width=891)
输入创建虚拟机的名称和选择虚拟机的镜像即可创建个默认大小的虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174794558-ae9a6d65-51fd-466e-9410-4cf7e6598e5f.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u23160584&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=70153&status=done&style=none&taskId=uab33d0fc-9d5e-4c58-ada1-f551188c4ad&title=&width=1235)
虚拟机创建成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174865239-1a24c014-cfdd-4d30-aed9-a99eeb5e763d.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u70980f38&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=117127&status=done&style=none&taskId=u4264eb43-812b-4fc9-bb10-2df7f52b06a&title=&width=1235)
### 虚拟机启动
点击虚拟机的启动按钮即可启动
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174928417-ed2eec5d-e633-41b1-a7e9-29b2fff8171f.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=873&id=u271216cf&margin=%5Bobject%20Object%5D&name=image.png&originHeight=873&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=117459&status=done&style=none&taskId=u2d1cd81e-24c4-4a7e-977b-f902db25f42&title=&width=1235)
虚拟机变为运行状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655174980001-a038c686-6ea1-4c15-a900-a0808f98a5e5.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=u301977e3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=114151&status=done&style=none&taskId=uf5307800-ee7e-4422-8c97-e92042fe504&title=&width=1235)
虚拟机管理器查看也是运行状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175035258-e72a49c4-b79f-4543-be4d-6c9df9c17543.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=ucb0a5725&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=97768&status=done&style=none&taskId=u71579c98-a914-451e-80ae-4eec5667e58&title=&width=1235)
虚拟机启动成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175063991-ff950dbf-6b83-4628-a88e-b3d57350dd2a.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=u55c7d141&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=76880&status=done&style=none&taskId=uc85d126f-3d11-4ab1-bf72-f35478a59f2&title=&width=1235)
### 虚拟机挂起
点击虚拟机的挂起按钮即可挂起
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175131727-d08f857f-367f-4fc6-9b13-6a1777d233fa.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=u8c63c577&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=115720&status=done&style=none&taskId=u15f09654-dbf2-42c1-9762-e04c193b02e&title=&width=1235)
虚拟机变为挂起状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175145488-737dd376-cd21-4483-92f7-2718f76195ae.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=u34d77bcb&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=116904&status=done&style=none&taskId=u724951fd-afd8-4e5f-8484-1348484af62&title=&width=1235)
虚拟机管理器查看也是挂起状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175157686-471b5669-c149-485f-a2a1-07099b1fd553.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=uaabc3554&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=97782&status=done&style=none&taskId=ud3390833-41bf-4938-85c9-2b4c7cb4823&title=&width=1235)
### 虚拟机还原
点击虚拟机的还原按钮即可还原
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175237310-818dc542-30da-4a32-bd44-119592ea6c2a.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=u67d944e9&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=125310&status=done&style=none&taskId=u6b323131-fbe5-40b3-ad99-03729384af7&title=&width=1235)
虚拟机变为运行状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175254404-b6697774-ba76-4fc7-a123-3399f4c671d4.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=uc606668d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=116459&status=done&style=none&taskId=ub6a9b3ec-9742-49b9-a801-a1df8f3f3f6&title=&width=1235)
虚拟机管理器查看也是运行状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175265566-89e7b06e-8dd3-4829-9e0c-b077be7466fa.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=868&id=ua21e8fd3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=868&originWidth=1235&originalType=binary&ratio=1&rotation=0&showTitle=false&size=97378&status=done&style=none&taskId=u82f4bd65-462b-4fd3-ba08-79758b6240f&title=&width=1235)
### 虚拟机保存
点击虚拟机的保存按钮即可保存
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175314752-1dd020d2-0ac6-41ff-97c6-9b1605b986b5.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u532f2cff&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106691&status=done&style=none&taskId=u27ac9de4-bd40-43dd-8cb5-10d5b21cc0e&title=&width=939)
选择合适的路径进行保存
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175355716-12c3b981-c8c7-4672-a822-bd56acd115fe.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ub772636d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=91068&status=done&style=none&taskId=uca60e585-1912-460f-9b17-a7172dd11db&title=&width=939)
保存成功虚拟机状态为关闭并且生成磁盘文件
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175399111-43899f6d-0dea-40af-b250-8c49525068f9.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u9be90c55&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110192&status=done&style=none&taskId=u09b8d4bf-3a8b-4d82-a7f7-9498bc2445a&title=&width=939)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175418005-0dbad86b-9424-4fd5-9656-41f5ef55ffd1.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u1f0d6345&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=84017&status=done&style=none&taskId=u65086ec8-65e7-4459-843a-606fbd6b228&title=&width=939)
### 虚拟机恢复
点击虚拟机的恢复按钮即可恢复
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175450992-11629bbf-13bc-46ed-95e8-6a5e073926f3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u46574715&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=109543&status=done&style=none&taskId=ud3b44540-082c-4932-850c-1e01c1e8591&title=&width=939)
选择磁盘文件
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175487579-6234d389-48d4-41d9-8bdd-b4619357e170.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ubfa0d9d7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=88920&status=done&style=none&taskId=u01e47b3f-df3c-450c-89e5-1f6b90ff9fb&title=&width=939)
恢复成功虚拟机状态变为运行态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175525492-f51d2955-6c48-429a-a640-f4d660d00f36.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u63a43d70&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=103188&status=done&style=none&taskId=ub49f7a10-075a-40a5-a6a6-bced96b443e&title=&width=939)
### 虚拟机关闭与强制关闭
点击虚拟机的关闭按钮或者强制关闭按钮即可关闭虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175591789-403dbaee-bc7d-4118-9e93-78659a5d5a30.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u6c8c02f0&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=103097&status=done&style=none&taskId=ud0a49128-c488-4480-9321-42cfbb85ea7&title=&width=939)
关闭成功变为关闭状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175756328-64b38520-6fc8-4c18-ac67-21458c0ed3e8.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=uf6efc26b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106829&status=done&style=none&taskId=u1ffd3d8c-4b8b-4b38-aec3-7bbf6a26e03&title=&width=939)
虚拟机管理器查看也是关闭状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175806979-b6636b80-fa4e-4066-adce-b874b7a7f08c.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ucda648ea&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=78374&status=done&style=none&taskId=u2ce45dd3-d298-4ba3-95d4-9230ad436f7&title=&width=939)

### 虚拟机重启
点击虚拟机的重启按钮即可重启虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175632244-ed318808-2fc9-4ee6-b379-ab1f5a88700f.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u01835865&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=102677&status=done&style=none&taskId=ufd299e93-3f2f-4060-b338-4d9955f8c4c&title=&width=939)
虚拟机正在重启
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175654107-9e42e0bd-80fc-469c-8685-378fc815adf3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u89d8118c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=95430&status=done&style=none&taskId=u378fbbc0-a8ab-4c21-b30b-f9d54fb76f8&title=&width=939)
虚拟机启动成功并且状态仍然为运行状态
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175688402-3b0e06c6-198f-4372-b143-195af5b0db77.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u7f55d1a1&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=74499&status=done&style=none&taskId=u37cecabd-6741-4a03-b3d1-ee8d435afc1&title=&width=939)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175708518-819a8e39-c5d1-4588-b8a8-befae275e02a.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u70adb2c6&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=107161&status=done&style=none&taskId=uad314e92-608b-4778-8f15-94cae947f2a&title=&width=939)
### 虚拟机删除
点击删除按钮即可删除虚拟机
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175950329-47093b8d-4fe3-41e6-b3f6-b48097b07513.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u15865c8d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=102706&status=done&style=none&taskId=uec5a6a9f-e135-41b3-966b-cc707aef919&title=&width=939)
虚拟机删除后将不存在
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175968010-32644e7d-5a6b-4a8f-9d85-a1b4bbc0e835.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ubfca5d53&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=95261&status=done&style=none&taskId=u6d6617df-1174-49d5-a6e2-455d65ed43c&title=&width=939)
虚拟机管理器查看也是是不存在
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655175998111-b094319a-77cf-4d50-910a-ff7950a5eaa1.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ub855e727&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=63298&status=done&style=none&taskId=u59f6dcc1-2800-42fa-aa65-0a791c7ee9e&title=&width=939)
### 管理快照
点击快照管理即可进入管理界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176099934-e51b113f-053a-4ce7-a01b-fedfe86e0e58.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ua5f08bc6&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=97003&status=done&style=none&taskId=uc0b660c5-1a04-47a3-b2b2-a7d5c279e9b&title=&width=939)
快照界面可以查看快照拍摄的时间状态等信息 也可以对快照进行一系列操作
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176415645-879d7703-2210-402f-bd7b-3d89f034da2b.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u814260f7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=79554&status=done&style=none&taskId=uade9c451-a02f-473c-a6e8-6e725acee78&title=&width=939)
### 拍摄快照
填入快照的名称点击拍摄按钮即可拍照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176129535-9734b6f6-5800-4123-8aed-3ce580d479d8.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ubc762832&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=73986&status=done&style=none&taskId=ude26fa01-7c11-4788-9ae1-8caeabb328b&title=&width=939)
拍摄成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176166603-a8969d62-3013-41fd-a99a-124d705d9ee8.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u729b0d03&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=81411&status=done&style=none&taskId=u0ae5bb93-b352-47a6-a78e-3c16ba728dd&title=&width=939)
### 快照使用
点击使用按钮即可恢复到此时的快照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176226870-637fd1bd-3b0f-4565-90ea-80c48ef551c0.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u164e4570&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=81632&status=done&style=none&taskId=uc8abbefa-36f9-461e-a7ee-b67a96b6d69&title=&width=939)
### 快照删除
点击删除按钮即可删除该快照
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176272672-fa47cb4c-f9aa-4f1d-9a1d-b81e6f2492cb.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=uc8c764ab&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=82069&status=done&style=none&taskId=u4a3e8f8c-4369-4928-9167-ae669fc8e6b&title=&width=939)
删除成功快照将不存在
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176291469-004e900a-3a87-412e-b568-ac51f5489c5a.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u87cdc4f5&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=77676&status=done&style=none&taskId=ua5cb5194-f8ff-4c04-a399-28b669253bb&title=&width=939)
### 镜像管理
点击镜像管理即可进行镜像管理，该页面展示了镜像信息和定义了一系列操作
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176586754-1a2f24dc-881c-4488-88d7-7ca204b7b3c0.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u56cb5b38&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=77193&status=done&style=none&taskId=uf63059fd-0880-4610-b365-6c19002e409&title=&width=939)
### 镜像下载
点击镜像下载按钮即可下载镜像
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176676518-11804a43-46ef-46a9-bd77-c25ac0b2a996.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ub5f7928d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=76160&status=done&style=none&taskId=u53bf67e1-e58e-4e69-94be-0fa01d4c50e&title=&width=939)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176694101-dd8e9966-89b5-413b-8e7c-92c682bca0af.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u294ecefd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=73191&status=done&style=none&taskId=u50a13c85-af24-4042-a261-86ba0218138&title=&width=939)
### 镜像删除
点击镜像删除按钮即可删除镜像
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176891574-7331db78-1350-40ba-bb8f-329154fe6443.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u27a70a82&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=82538&status=done&style=none&taskId=u56ed9e3c-d6d3-440a-baac-c2e90557ab7&title=&width=939)
删除之后将不在列表中
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176916121-2bf81242-c506-4b23-b3aa-dcc89c3d6696.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u9d25fe73&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=77612&status=done&style=none&taskId=u50d7a2cf-947d-4fe7-acc9-df7cfbf5f5c&title=&width=939)
### 镜像添加
点击镜像添加跳转到添加界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176769287-e2df9e66-92a6-4ffc-b5ce-8ea91183572e.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=75&id=u16940fa8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=75&originWidth=894&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14903&status=done&style=none&taskId=u853f0848-86ed-420e-ac96-dd542ce4df3&title=&width=894)
输入添加镜像的名称和选择镜像文件点击提交按钮即可
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176799960-301ef6d2-499a-4ad8-90bf-2e4cf9b591c3.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u27b58c51&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=69297&status=done&style=none&taskId=u1d088a3b-359e-415d-a3c0-dc681b34426&title=&width=939)
添加成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176867050-469d5ac2-9d56-4808-942a-fd5e7010ff0e.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=uce74c983&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=82413&status=done&style=none&taskId=u1020d20f-8e86-4c4d-aad3-be975d81026&title=&width=939)
### 添加存储池
点击添加存储池按钮跳转到添加界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655176997860-20febf0f-7318-439a-b7fd-bc1aca5ebec4.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=60&id=uf5bb45ef&margin=%5Bobject%20Object%5D&name=image.png&originHeight=60&originWidth=890&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14142&status=done&style=none&taskId=udeb9267d-3045-4c6f-84bc-ab12cad18f7&title=&width=890)
输入存储池名称和存储池的路径点击提交按钮即可
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655177045272-c7f758ff-4f8a-4cbb-9b95-5950040a3582.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ua006b17b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=75374&status=done&style=none&taskId=u3097cd0a-b29f-4773-8f88-09ecda87043&title=&width=939)
存储添加成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655177064181-53629a38-7dc8-426f-b0b8-63e486fdfeb9.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u7f452d7f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=89886&status=done&style=none&taskId=u092b7d54-4df6-4d29-aa7b-dfcf3117f2c&title=&width=939)
### 删除存储池
点击存储池管理按钮跳转到存储池管理界面
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655177118298-02bc07f3-197d-4585-9fb7-2ac76650d685.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=71&id=u75d81148&margin=%5Bobject%20Object%5D&name=image.png&originHeight=71&originWidth=882&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14124&status=done&style=none&taskId=ue0c037ed-2274-41d2-b14a-f27a00ebee3&title=&width=882)
点击删除按钮即可删除存储池
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655177138412-9a903113-d63b-476c-a50f-314af0b92732.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=ub5f4b77d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=89257&status=done&style=none&taskId=uea608864-83c8-4b1f-9401-a9d4635e451&title=&width=939)
存储删除成功
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1655177151352-39b7ba5d-eee5-42f1-b3a1-2fed67b43cb0.png#clientId=u3028d98d-6a24-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=746&id=u5403c81b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=746&originWidth=939&originalType=binary&ratio=1&rotation=0&showTitle=false&size=84817&status=done&style=none&taskId=u9e867ce0-bb20-41c9-a19b-36d001c8feb&title=&width=939)
# 附录：源程序清单
## utils
### LibvirtUtils
```java
package com.example.libvirt.Utils;

import com.example.libvirt.pojo.LibvirtConnect;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.libvirt.Connect;

@Log
public class LibvirtUtils {
    
    private static Connect connect;
    
    // Connection
    @SneakyThrows
    public static Connect getConnection() {
        if (null == connect) {
            connect = new Connect("qemu:///system", false);
            log.info("Libvirt local connection successful" + "\n"
                     + "     连接URI: " + connect.getURI() + "\n"
                     + "     宿主机主机名: " + connect.getHostName() + "\n"
                     + "     宿主机剩余内存: " + connect.getFreeMemory() + "\n"
                     + "     宿主机最大cpu数量: " + connect.getMaxVcpus(null) + "\n"
                     + "     libvirt库版本号: " + connect.getLibVirVersion() + "\n"
                     + "     hypervisor名称: " + connect.getType()
                    );
        }
        return connect;
    }
    
    // ConnectionInfo
    @SneakyThrows
    public static LibvirtConnect getConnectionIo() {
        Connect connect = getConnection();
        return LibvirtConnect.builder()
            .url(connect.getURI())
            .hostName(connect.getHostName())
            .libVirVersion(connect.getLibVirVersion())
            .hypervisorVersion(connect.getType())
            .build();
    }    
}
```
### SigarUtils
```java
package com.example.libvirt.Utils;

import com.example.libvirt.pojo.Host;
import lombok.SneakyThrows;
import org.hyperic.sigar.*;

import java.util.HashMap;

public class SigarUtils {

    private static Sigar sigar;

    private static Sigar getSigarInstance() {
        if (null == sigar) sigar = new Sigar();
        return sigar;
    }

    // getHostInfo
    @SneakyThrows
    public static Host getHostInfo() {

        OperatingSystem OS = OperatingSystem.getInstance();
        Mem mem = getSigarInstance().getMem();
        Swap swap = sigar.getSwap();
        CpuInfo[] cpuInfoList = getSigarInstance().getCpuInfoList();

        HashMap<String, Object> fileInfoMap = new HashMap<>();
        for (FileSystem fs : getSigarInstance().getFileSystemList()) {
            //盘符类型:ext4   硬盘类型:本地硬盘 2
            if (fs.getType() == 2 && fs.getSysTypeName().equals("ext4")) {
                FileSystemUsage usage = getSigarInstance().getFileSystemUsage(fs.getDirName());
                fileInfoMap.put("devName", fs.getDevName());              // 盘符名称
                fileInfoMap.put("dirName", fs.getDirName());              // 盘符路径
                fileInfoMap.put("fileTotal", usage.getTotal());           // 总大小       KB
                fileInfoMap.put("fileUsed", usage.getUsed());             // 已经使用量   KB
                fileInfoMap.put("fileUsePercent", (usage.getUsePercent() * 100D)); // 资源的利用率 %
            }
        }

        HashMap<String, String> netInfoMap = new HashMap<>();
        for (String name : getSigarInstance().getNetInterfaceList()) {
            if (name.equals("ens33")) {
                NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(name);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0 || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr()))
                    continue;
                netInfoMap.put("description", cfg.getDescription());    // 网卡描述
                netInfoMap.put("netType", cfg.getType());               // 网卡类型
                netInfoMap.put("IPAddress", cfg.getAddress());          // IP地址
                netInfoMap.put("MACAddress", cfg.getHwaddr());          // MAC地址
                netInfoMap.put("netmask", cfg.getNetmask());            // 子网掩码
            }
        }

        return Host.builder()

                // os
                .vendor(OS.getVendor())                 // Ubuntu
                .vendorVersion(OS.getVendorVersion())   // 20.04
                .vendorCodeName(OS.getVendorCodeName()) // focal
                .version(OS.getVersion())               // 5.13.0-44-generic

                // memory 单位:k
                .memory(mem.getTotal() / 1024L)
                .memoryUsed(mem.getUsed() / 1024L)
                .memoryFree(mem.getFree() / 1024L)
                .swap(swap.getTotal() / 1024L)
                .swapUsed(swap.getUsed() / 1024L)
                .swapFree(swap.getFree() / 1024L)

                // cpu
                .cpuNum(cpuInfoList.length)
                .cpuModel(cpuInfoList[1].getModel())
                .cpuMhz(cpuInfoList[1].getMhz())

                // file
                .devName((String) fileInfoMap.get("devName"))                    // 盘符名称
                .dirName((String) fileInfoMap.get("dirName"))                    // 盘符路径
                .fileTotal((Long) fileInfoMap.get("fileTotal"))                  // 总大小       KB
                .fileUsed((Long) fileInfoMap.get("fileUsed"))                    // 已经使用量   KB
                .fileUsePercent((Double) fileInfoMap.get("fileUsePercent"))      // 资源的利用率 %

                // net
                .netDescription(netInfoMap.get("description"))                   // 网卡描述
                .netType(netInfoMap.get("netType"))                              // 网卡类型
                .netIP(netInfoMap.get("IPAddress"))                              // IP地址
                .netMAC(netInfoMap.get("MACAddress"))                            // MAC地址
                .netMask(netInfoMap.get("netmask"))                              // 子网掩码

                .build();
    }
}
```
## pojo
### Host
```java
package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Host {
    // os
    private String vendor;           // Ubuntu
    private String vendorVersion;    // 20.04
    private String vendorCodeName;   // focal
    private String version;          // 5.13.0-44-generic

    // memory 单位:k
    private long memory;
    private long memoryUsed;
    private long memoryFree;
    private long swap;
    private long swapUsed;
    private long swapFree;

    // cpu
    private int cpuNum;
    private String cpuModel;
    private int cpuMhz;

    // file
    private String devName;
    private String dirName;
    private long fileTotal;
    private long fileUsed;
    private double fileUsePercent;

    // net
    private String netDescription;
    private String netType;
    private String netIP;
    private String netMAC;
    private String netMask;
}
```
### Virtual
```java
package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Virtual {
    private int id;
    private String name;
    private String state;
}
```
### ImgFile
```java
package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class ImgFile {
    private String name;
    private String size;
}
```
### Snapshot
```java
package com.example.libvirt.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Snapshot {
    private String name;
    private String creationTime;
    private String state;
}
```
### Storagepool
```java
package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class Storagepool {
    private String name;    // 名称
    private String type;    // 类型

    private int capacity;   // GB 容量
    private int available;  // GB 可用容量
    private int allocation; // GB 已用容量
    private String usage;   // 使用率(%)

    private String state;   // 状态
    private String xml;     // 描述xml
 }
```
### LibvirtConnect
```java
package com.example.libvirt.pojo;

import lombok.*;

@Builder
@Getter
@ToString
public class LibvirtConnect {
    private String url;
    private String hostName;
    private long libVirVersion;
    private String hypervisorVersion;
}
```
## service
### LibvirtService
```java
package com.example.libvirt.service;

import com.example.libvirt.Utils.*;
import com.example.libvirt.pojo.*;
import org.apache.commons.io.FileUtils;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.libvirt.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Log
@Service(value = "libvirtService")
public class LibvirtService {

    /**
     * getHostInformation
     */
    public Host getHostInfo() {
        return SigarUtils.getHostInfo();
    }

    /**
     * getLibvirtConnectInformation
     */
    public LibvirtConnect getLibvirtConnectInformation() {
        return LibvirtUtils.getConnectionIo();
    }

    /**
     * getDomainById
     */
    @SneakyThrows
    public Domain getDomainById(int id) {
        return LibvirtUtils.getConnection().domainLookupByID(id);
    }

    /**
     * getDomainByName
     */
    @SneakyThrows
    public Domain getDomainByName(String name) {
        return LibvirtUtils.getConnection().domainLookupByName(name);
    }

    /**
     * getVirtualById
     */
    @SneakyThrows
    public Virtual getVirtualById(int id) {
        Domain domain = getDomainById(id);
        return Virtual.builder()
                .id(domain.getID())
                .name(domain.getName())
                .state(domain.getInfo().state.toString())
                .build();
    }

    /**
     * getVirtualByName
     */
    @SneakyThrows
    public Virtual getVirtualByName(String name) {
        Domain domain = getDomainByName(name);
        return Virtual.builder()
                .id(domain.getID())
                .name(domain.getName())
                .state(domain.getInfo().state.toString())
                .build();
    }

    /**
     * 虚拟机列表
     */
    @SneakyThrows
    public List<Virtual> getVirtualList() {
        ArrayList<Virtual> virtualList = new ArrayList<>();
        // live
        int[] ids = LibvirtUtils.getConnection().listDomains();
        for (int id : ids) virtualList.add(getVirtualById(id));
        // down
        String[] names = LibvirtUtils.getConnection().listDefinedDomains();
        for (String name : names) virtualList.add(getVirtualByName(name));
        return virtualList;
    }

    /**
     * 暂停/挂起 虚拟机
     */
    @SneakyThrows
    private void suspendedDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.suspend();
            log.info(domain.getName() + "虚拟机已挂起！");
        } else log.info("虚拟机未打开");
    }

    public void suspendedDomainById(int id) {
        suspendedDomain(getDomainById(id));
    }

    public void suspendedDomainName(String name) {
        suspendedDomain(getDomainByName(name));
    }


    /**
     * 还原 暂停/挂起 虚拟机
     */
    @SneakyThrows
    private void resumeDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.resume();
            log.info(domain.getName() + "虚拟机已唤醒！");
        } else log.info("虚拟机未打开");
    }

    public void resumeDomainById(int id) {
        resumeDomain(getDomainById(id));
    }

    public void resumeDomainByName(String name) {
        resumeDomain(getDomainByName(name));
    }

    /**
     * 保存 虚拟机 --->img文件
     */
    @SneakyThrows
    private void saveDomain(Domain domain) {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
        jf.showDialog(null, null);
        String f = jf.getSelectedFile().getAbsolutePath() + "/save.img";
        if (domain.isActive() == 1) {
            domain.save(f);
            log.info(domain.getName() + "虚拟机状态已保存！" + "save: " + f);
        } else log.info("虚拟机未打开");
    }

    public void saveDomainById(int id) {
        saveDomain(getDomainById(id));
    }

    public void saveDomainByName(String name) {
        saveDomain(getDomainByName(name));
    }

    /**
     * 恢复 虚拟机 --->img文件
     */
    @SneakyThrows
    private void restoreDomain(Domain domain) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        if (chooser.getSelectedFile() != null) {
            String path = chooser.getSelectedFile().getPath();
            if (domain.isActive() == 0) {
                domain.getConnect().restore(path);
                log.info(domain.getName() + "虚拟机状态已恢复！！" + "path: " + path);
            } else log.info("虚拟机未关闭");
        }
    }

    public void restoreDomainById(int id) {
        restoreDomain(getDomainById(id));
    }

    public void restoreDomainByName(String name) {
        restoreDomain(getDomainByName(name));
    }

    /**
     * 启动 虚拟机
     */
    @SneakyThrows
    private void initiateDomain(Domain domain) {
        if (domain.isActive() == 0) {
            domain.create();
            log.info(domain.getName() + "虚拟机已启动！");
        } else log.info("虚拟机已经打开过！");
    }

    public void initiateDomainByName(String name) {
        initiateDomain(getDomainByName(name));
    }

    /**
     * 关闭 虚拟机
     */
    @SneakyThrows
    private void shutdownDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.shutdown();
            log.info(domain.getName() + "虚拟机已正常关机！");
        } else log.info("虚拟机未打开");
    }

    public void shutdownDomainById(int id) {
        shutdownDomain(getDomainById(id));
    }

    public void shutdownDomainByName(String name) {
        shutdownDomain(getDomainByName(name));
    }

    /**
     * 强制关闭 虚拟机
     */
    @SneakyThrows
    private void shutdownMustDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.destroy();
            log.info(domain.getName() + "虚拟机已强制关机！");
        } else log.info("虚拟机未打开");
    }

    public void shutdownMustDomainById(int id) {
        shutdownMustDomain(getDomainById(id));
    }

    public void shutdownMustDomainByName(String name) {
        shutdownMustDomain(getDomainByName(name));
    }

    /**
     * 重启 虚拟机
     */
    @SneakyThrows
    private void rebootDomain(Domain domain) {
        if (domain.isActive() == 1) {
            domain.reboot(0);
            log.info(domain.getName() + "虚拟机状态已重启！");
        } else log.info("虚拟机未打开");
    }

    public void rebootDomainById(int id) {
        rebootDomain(getDomainById(id));
    }

    public void rebootDomainByName(String name) {
        rebootDomain(getDomainByName(name));
    }

    /**
     * 添加 虚拟机 xml------>name   1024MB
     */
    @SneakyThrows
    public void addDomainByName(String name) {
        String xml = "<domain type='kvm'>\n" +
                "  <name>" + name + "</name>\n" +
                "  <uuid>" + UUID.randomUUID() + "</uuid>\n" +
                "  <metadata>\n" +
                "    <libosinfo:libosinfo xmlns:libosinfo=\"http://libosinfo.org/xmlns/libvirt/domain/1.0\">\n" +
                "      <libosinfo:os id=\"http://ubuntu.com/ubuntu/14.04\"/>\n" +
                "    </libosinfo:libosinfo>\n" +
                "  </metadata>\n" +
                "  <memory unit='KiB'>1048576</memory>\n" +                 // 1024 MB
                "  <currentMemory unit='KiB'>1048576</currentMemory>\n" +   // 1024 MB
                "  <vcpu placement='static'>2</vcpu>\n" +
                "  <os>\n" +
                "    <type arch='x86_64' machine='pc-i440fx-focal'>hvm</type>\n" +
                "    <boot dev='hd'/>\n" +
                "  </os>\n" +
                "  <features>\n" +
                "    <acpi/>\n" +
                "    <apic/>\n" +
                "    <vmport state='off'/>\n" +
                "  </features>\n" +
                "  <cpu mode='host-model' check='partial'/>\n" +
                "  <clock offset='utc'>\n" +
                "    <timer name='rtc' tickpolicy='catchup'/>\n" +
                "    <timer name='pit' tickpolicy='delay'/>\n" +
                "    <timer name='hpet' present='no'/>\n" +
                "  </clock>\n" +
                "  <on_poweroff>destroy</on_poweroff>\n" +
                "  <on_reboot>restart</on_reboot>\n" +
                "  <on_crash>destroy</on_crash>\n" +
                "  <pm>\n" +
                "    <suspend-to-mem enabled='no'/>\n" +
                "    <suspend-to-disk enabled='no'/>\n" +
                "  </pm>\n" +
                "  <devices>\n" +
                "    <emulator>" + "/usr/bin/qemu-system-x86_64" + "</emulator>\n" +
                "    <disk type='file' device='disk'>\n" +
                "      <driver name='qemu' type='qcow2'/>\n" +
                "      <source file='/home/wyl/KVMImg/" + name + ".img'/>\n" +   // FileSource
                "      <target dev='vda' bus='virtio'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x07' function='0x0'/>\n" +
                "    </disk>\n" +
                "    <controller type='usb' index='0' model='ich9-ehci1'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x7'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci1'>\n" +
                "      <master startport='0'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x0' multifunction='on'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci2'>\n" +
                "      <master startport='2'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x1'/>\n" +
                "    </controller>\n" +
                "    <controller type='usb' index='0' model='ich9-uhci3'>\n" +
                "      <master startport='4'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x05' function='0x2'/>\n" +
                "    </controller>\n" +
                "    <controller type='pci' index='0' model='pci-root'/>\n" +
                "    <controller type='virtio-serial' index='0'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x06' function='0x0'/>\n" +
                "    </controller>\n" +
                "    <interface type='network'>\n" +
                "      <mac address='52:54:00:27:6d:ef'/>\n" +
                "      <source network='default'/>\n" +
                "      <model type='virtio'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x03' function='0x0'/>\n" +
                "    </interface>\n" +
                "    <serial type='pty'>\n" +
                "      <target type='isa-serial' port='0'>\n" +
                "        <model name='isa-serial'/>\n" +
                "      </target>\n" +
                "    </serial>\n" +
                "    <console type='pty'>\n" +
                "      <target type='serial' port='0'/>\n" +
                "    </console>\n" +
                "    <channel type='spicevmc'>\n" +
                "      <target type='virtio' name='com.redhat.spice.0'/>\n" +
                "      <address type='virtio-serial' controller='0' bus='0' port='1'/>\n" +
                "    </channel>\n" +
                "    <input type='tablet' bus='usb'>\n" +
                "      <address type='usb' bus='0' port='1'/>\n" +
                "    </input>\n" +
                "    <input type='mouse' bus='ps2'/>\n" +
                "    <input type='keyboard' bus='ps2'/>\n" +
                "    <graphics type='vnc' port='-1' autoport='yes' listen='0.0.0.0' keymap='en-us'>\n" +
                "      <listen type='address' address='0.0.0.0'/>\n" +
                "    </graphics>\n" +
                "    <sound model='ich6'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x04' function='0x0'/>\n" +
                "    </sound>\n" +
                "    <video>\n" +
                "      <model type='qxl' ram='65536' vram='65536' vgamem='16384' heads='1' primary='yes'/>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x0'/>\n" +
                "    </video>\n" +
                "    <redirdev bus='usb' type='spicevmc'>\n" +
                "      <address type='usb' bus='0' port='2'/>\n" +
                "    </redirdev>\n" +
                "    <redirdev bus='usb' type='spicevmc'>\n" +
                "      <address type='usb' bus='0' port='3'/>\n" +
                "    </redirdev>\n" +
                "    <memballoon model='virtio'>\n" +
                "      <address type='pci' domain='0x0000' bus='0x00' slot='0x08' function='0x0'/>\n" +
                "    </memballoon>\n" +
                "  </devices>\n" +
                "</domain>";
        LibvirtUtils.getConnection().domainDefineXML(xml);    // define ------> creat
        log.info(name + "虚拟机已创建！");
    }

    /**
     * 删除 虚拟机 xml
     */
    @SneakyThrows
    private void deleteDomain(Domain domain) {
        if (domain.isActive() == 1) domain.destroy();  // 强制关机
        domain.undefine();
        log.info(domain.getName() + "虚拟机已删除！");
    }

    public void deleteDomainById(int id) {
        deleteDomain(getDomainById(id));
    }

    public void deleteDomainByName(String name) {
        deleteDomain(getDomainByName(name));
    }

    /**
     * get ImgList
     */
    public List<ImgFile> getImgList() {
        List<ImgFile> list = new ArrayList<>();
        File[] files = new File("/home/wyl/KVMImg/").listFiles();
        if (files != null) {
            for (File file : files) {
                list.add(ImgFile.builder()
                        .name(file.getName())
                        .size(FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file)))
                        .build());
            }
        }
        return list;
    }

    /**
     * 添加 img
     */
    @SneakyThrows
    public Boolean addImgFile(String name, MultipartFile file) {
        if (!file.isEmpty()) {
            file.transferTo(new File("/home/wyl/KVMImg/" + name + ".img"));
            log.info("文件" + name + ".img已经保存！");
            return true;
        }
        log.info("文件" + name + ".img保存失败！");
        return false;
    }

    /**
     * 下载 img
     */
    @SneakyThrows
    public String downImgFile(String name, HttpServletResponse response) {
        File file = new File("/home/wyl/KVMImg/" + name);
        if (!file.exists()) return "下载文件不存在";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));  // 设置编码格式
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        int i = 0;
        byte[] buff = new byte[1024];
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
        return "下载成功";
    }

    /**
     * 删除 img
     */
    public Boolean deleteImgFile(String name) {
        if (new File("/home/wyl/KVMImg/" + name).delete()) {
            log.info("文件" + name + "已经删除！");
            return true;
        }
        log.info("文件" + name + "文件不存在");
        return false;
    }

    /**
     * 关闭网络
     */
    @SneakyThrows
    public void closeNetWork() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        Network network = domain.getConnect().networkLookupByName("default");
        if (network.isActive() == 1) {
            network.destroy();
            log.info("网络" + network.getName() + "已经被关闭！");
        } else log.info("网络" + network.getName() + "已经处于关闭状态！");
    }

    /**
     * 启动网络
     */
    @SneakyThrows
    public void openNetWork() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        Network network = domain.getConnect().networkLookupByName("default");
        if (network.isActive() == 0) {
            network.create();
            log.info("网络" + network.getName() + "已经被打开！");
        } else log.info("网络" + network.getName() + "已经处于打开状态！");
    }

    /**
     * 网络 State
     */
    @SneakyThrows
    public String getNetState() {
        Domain domain = getDomainByName(getVirtualList().get(0).getName());
        if (domain.getConnect().networkLookupByName("default").isActive() == 1) return "on";
        else return "off";
    }

    /**
     * getSnapshotList
     */
    @SneakyThrows
    public List<Snapshot> getSnapshotListByName(String name) {
        // virsh snapshot-list      虚拟机名
        String cmd = "virsh snapshot" + "-list " + name;
        Process process = Runtime.getRuntime().exec(cmd);
        LineNumberReader line = new LineNumberReader(new InputStreamReader(process.getInputStream()));
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String str;
        int linCount = 0;
        int snapshotNum = getDomainByName(name).snapshotNum(); // 2
        while ((str = line.readLine()) != null && snapshotNum > 0) {
            linCount++;
            if (linCount <= 2) continue;  // -2 line
            else {
                snapshotNum--;
                String[] lineStr = str.split("   ");
                snapshots.add(Snapshot.builder().name(lineStr[0]).creationTime(lineStr[1]).state(lineStr[2]).build());
            }
        }
        return snapshots;
    }

    /**
     * snapshot管理
     */
    @SneakyThrows
    private void snapshotManger(String op, String name, String snapshotName) {
        // virsh snapshot-create-as 虚拟机名称 快照名称
        // virsh snapshot-delete    虚拟机名称 快照名称
        // virsh snapshot-revert    虚拟机名称 快照名称
        String cmd = "";
        switch (op) {
            case "creat":
                cmd = "virsh snapshot" + "-create-as " + name + " " + snapshotName;
                break;
            case "delete":
                cmd = "virsh snapshot" + "-delete " + name + " " + snapshotName;
                break;
            case "revert":
                cmd = "virsh snapshot" + "-revert " + name + " " + snapshotName;
                break;
        }
        Runtime.getRuntime().exec(cmd);
    }

    /**
     * 创建快照
     */
    public void createSnapshot(String name, String snapshotName) {
        snapshotManger("creat", name, snapshotName);
        log.info("虚拟机" + name + "成功创建快照" + snapshotName);
    }

    /**
     * 删除快照
     */
    public void deleteSnapshot(String name, String snapshotName) {
        snapshotManger("delete", name, snapshotName);
        log.info("虚拟机" + name + "成功删除快照" + snapshotName);
    }

    /**
     * 启动快照
     */
    public void revertSnapshot(String name, String snapshotName) {
        snapshotManger("revert", name, snapshotName);
        log.info("虚拟机" + name + "成功切换快照" + snapshotName);
    }

    /**
     * getStoragePoolList
     */
    @SneakyThrows
    public List<Storagepool> getStoragePoolList() {
        String[] pools = LibvirtUtils.getConnection().listStoragePools();
        String[] definedPools = LibvirtUtils.getConnection().listDefinedStoragePools();
        log.info("pools" + Arrays.toString(pools) + "definedPools" + Arrays.toString(definedPools));
        List<Storagepool> storagePoolList = new ArrayList<>();
        for (String pool : pools) storagePoolList.add(getStoragePool(pool));
        for (String pool : definedPools) storagePoolList.add(getStoragePool(pool));
        return storagePoolList;
    }

    /**
     * getStoragePool ByName
     */
    @SneakyThrows
    public Storagepool getStoragePool(String name) {
        StoragePool storagePool = LibvirtUtils.getConnection().storagePoolLookupByName(name);
        StoragePoolInfo info = storagePool.getInfo();
        return Storagepool.builder()
                .name(name)     // 名称
                .type("qcow2")  // 类型
                .capacity((int) (info.capacity / 1024.00 / 1024.00 / 1024.00))     // GB 容量
                .available((int) (info.available / 1024.00 / 1024.00 / 1024.00))   // GB 可用容量
                .allocation((int) (info.allocation / 1024.00 / 1024.00 / 1024.00)) // GB 已用容量
                .usage(((int) ((info.allocation / 1024.00 / 1024.00 / 1024.00) / (info.capacity / 1024.00 / 1024.00 / 1024.00) * 100)) + "%") // 使用率(%)
                .state(info.state.toString())                // 状态
                .xml(storagePool.getXMLDesc(0))        // 描述xml
                .build();
    }

    /**
     * 删除StoragePool ByName
     */
    @SneakyThrows
    public void deleteStoragePool(String name) {
        StoragePool storagePool = LibvirtUtils.getConnection().storagePoolLookupByName(name);
        for (String pool : LibvirtUtils.getConnection().listStoragePools())
            if (pool.equals(name)) storagePool.destroy();
        for (String pool : LibvirtUtils.getConnection().listDefinedStoragePools())
            if (pool.equals(name)) storagePool.undefine();
    }

    /**
     * 创建Storagepool  >>>>>> url必须存在
     */
    @SneakyThrows
    public boolean createStoragepool(String name, String url) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<pool type=\"dir\">\n" +
                "    <name>" + name + "</name> \n" +       // 名称必须唯一
                "    <source>\n" +
                "    </source>\n" +
                "    <target>\n" +
                "        <path>" + url + "</path> \n" +                     // StoragePool 在宿主机的路径
                "        <permissions> \n" +                                // 权限
                "            <mode>0711</mode>\n" +
                "            <owner>0</owner>\n" +
                "            <group>0</group>\n" +
                "        </permissions>\n" +
                "    </target>\n" +
                "</pool>";
        return LibvirtUtils.getConnection().storagePoolCreateXML(xml, 0) == null ? false : true;
    }
}
```
## controller
### LibvirtController
```java
package com.example.libvirt.controller;

import com.example.libvirt.pojo.*;
import com.example.libvirt.service.LibvirtService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LibvirtController {

    @Resource(name = "libvirtService")
    private LibvirtService libvirtService;

    @RequestMapping(value = {"/index"})
    public String index(Model model) {
        Host hostInfo = libvirtService.getHostInfo();
        model.addAttribute("hostinfo", hostInfo);
        LibvirtConnect connectInformation = libvirtService.getLibvirtConnectInformation();
        model.addAttribute("connectInformation", connectInformation);
        return "index";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        List<Virtual> virtualList = libvirtService.getVirtualList();
        model.addAttribute("virtualList", virtualList);
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "main";
    }

    @RequestMapping("/openOrCloseNetWork")
    public String openOrCloseNetWork(@RequestParam("netState") String netState) {
        if ("on".equals(netState)) libvirtService.closeNetWork();
        if ("off".equals(netState)) libvirtService.openNetWork();
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/initiate")
    public String initiateVirtual(@RequestParam("name") String name) {
        libvirtService.initiateDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/suspended")
    public String suspendedVirtual(@RequestParam("name") String name) {
        libvirtService.suspendedDomainName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/resume")
    public String resumeVirtual(@RequestParam("name") String name) {
        libvirtService.resumeDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/save")
    public String saveVirtual(@RequestParam("name") String name) {
        libvirtService.saveDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/restore")
    public String restoreVirtual(@RequestParam("name") String name) {
        libvirtService.restoreDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/shutdown")
    public String shutdownVirtual(@RequestParam("name") String name) {
        libvirtService.shutdownDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/shutdownMust")
    public String shutdownMustVirtual(@RequestParam("name") String name) {
        libvirtService.shutdownMustDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @SneakyThrows
    @RequestMapping("/reboot")
    public String rebootVirtual(@RequestParam("name") String name) {
        libvirtService.rebootDomainByName(name);
        Thread.sleep(1000);
        return "redirect:main";
    }

    @RequestMapping("/delete")
    public String deleteVirtual(@RequestParam("name") String name) {
        libvirtService.deleteDomainByName(name);
        libvirtService.deleteImgFile(name + ".img");
        return "redirect:main";
    }

    @RequestMapping("/toAddVirtual")
    public String toAddVirtual(Model model) {
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "addVirtual";
    }

    @RequestMapping(value = "/addVirtual", method = RequestMethod.POST)
    public String addVirtual(@RequestParam("virtualName") String name,
                             @RequestPart("file") MultipartFile file) {
        libvirtService.addDomainByName(name);
        libvirtService.addImgFile(name, file);
        return "redirect:main";
    }

    @RequestMapping("/img")
    public String imgList(Model model) {
        List<ImgFile> imgList = libvirtService.getImgList();
        model.addAttribute("imgList", imgList);
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "img";
    }

    @RequestMapping("/toAddImg")
    public String toAddImg(Model model) {
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "addImg";
    }

    @RequestMapping("/addImg")
    public String addImg(@RequestParam("imgName") String name,
                         @RequestPart("file") MultipartFile file) {
        libvirtService.addImgFile(name, file);
        return "redirect:img";
    }

    @RequestMapping("/deleteImg")
    public String deleteImg(@RequestParam("name") String name) {
        libvirtService.deleteImgFile(name);
        return "redirect:img";
    }

    @ResponseBody
    @RequestMapping("/downImg")
    public String downImg(@RequestParam("name") String name, HttpServletResponse response) {
        return libvirtService.downImgFile(name, response);
    }

    @RequestMapping("/getSnapshotList")
    public String getSnapshotList(@RequestParam("name") String name,
                                  Model model) {
        List<Snapshot> snapshotList = libvirtService.getSnapshotListByName(name);
        model.addAttribute("snapshotList", snapshotList);
        model.addAttribute("virtualName", name);
        return "snapshot";
    }

    @SneakyThrows
    @RequestMapping("/deleteSnapshot")
    public String deleteSnapshot(@RequestParam("virtualName") String virtualName,
                                 @RequestParam("snapshotName") String snapshotName) {
        libvirtService.deleteSnapshot(virtualName, snapshotName);
        Thread.sleep(1000);
        return "redirect:/getSnapshotList?name=" + virtualName;
    }

    @SneakyThrows
    @RequestMapping("/revertSnapshot")
    public String revertSnapshot(@RequestParam("virtualName") String virtualName,
                                 @RequestParam("snapshotName") String snapshotName) {
        libvirtService.revertSnapshot(virtualName, snapshotName);
        Thread.sleep(1000);
        return "redirect:/getSnapshotList?name=" + virtualName;
    }

    @SneakyThrows
    @RequestMapping("/createSnapshot")
    public String createSnapshot(@RequestParam("virtualName") String virtualName,
                                 @RequestParam("snapshotName") String snapshotName) {
        libvirtService.createSnapshot(virtualName, snapshotName);
        Thread.sleep(1000);
        return "redirect:/getSnapshotList?name=" + virtualName;
    }

    @RequestMapping("/storagePoolList")
    public String storagePoolList(Model model) {
        List<Storagepool> storagePoolList = libvirtService.getStoragePoolList();
        model.addAttribute("storagePoolList", storagePoolList);
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "storagepool";
    }

    @SneakyThrows
    @RequestMapping("/deleteStoragePool")
    public String deleteStoragePool(@RequestParam("name") String name) {
        libvirtService.deleteStoragePool(name);
        Thread.sleep(1000);
        return "redirect:/storagePoolList";
    }

    @RequestMapping("/toCreateStoragepool")
    public String toCreateStoragepool(Model model) {
        String netState = libvirtService.getNetState();
        model.addAttribute("netState", netState);
        return "addStoragepool";
    }

    @SneakyThrows
    @RequestMapping("/createStoragepool")
    public String createStoragepool(@RequestParam("storagepoolName") String name,
                                    @RequestParam("storagepoolPath") String url) {
        libvirtService.createStoragepool(name, url);
        Thread.sleep(1000);
        return "redirect:/storagePoolList";
    }

}
```
### UserController
```java
package com.example.libvirt.controller;

import com.example.libvirt.pojo.Host;
import com.example.libvirt.pojo.LibvirtConnect;
import com.example.libvirt.service.LibvirtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    /**
     * 跳转登录页面
     */
    @RequestMapping(value = {"/"})
    public String toLogin() {
        return "login";
    }

    /**
     * 登录
     */
    @RequestMapping(value = {"/login"})
    public String login(@RequestParam(value = "username", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password,
                        Model model,
                        HttpSession session) {
        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("loginUser", userName);//UserName存入Session
            return "redirect:index";
        }
        model.addAttribute("msg", "userName or password error!");
        return "login";
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            session.removeAttribute("loginUser");          //移除Session 转到登陆界面
        }
        return "redirect:/";
    }
}
```
## interceptor
### LoginInterceptor
```java
package com.example.libvirt.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 目标方法执行之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle拦截的请求路径是{}", request.getRequestURI());
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null) return true;     // 放行
        //拦截住。未登录。跳转到登录页
        request.setAttribute("msg", "请先登录");
        //response.sendRedirect("/");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

    /**
     * 目标方法执行完成以后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行{}", modelAndView);
    }

    /**
     * 页面渲染以后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行异常{}", ex);
    }

}
```
## config
### MyMvcConfig
```java
package com.example.libvirt.config;

import com.example.libvirt.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration(value = "myMvcConfig")
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/", "/login").
                excludePathPatterns("/css/*", "/js/*", "/img/*");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

}
```
## properties
### application.properties
```java
spring.http.multipart.max-file-size=10240MB
spring.http.multipart.max-request-size=10240MB
```
## templates
### login.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>云平台管理系统AdminLogin</title>
    <!-- Bootstrap core CSS -->
    <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link th:href="@{/css/signin.css}" rel="stylesheet"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>
</head>
<body class="text-center">
<form class="form-signin" th:action="@{/login}" method="post">
    <img class="mb-4" th:src="@{/img/log.jpg}" alt="" width="72" height="72"/>
    <h1 class="h3 mb-3 font-weight-normal">Admin Log In</h1>
    <input type="text" class="form-control" name="username" required="" autofocus=""/>
    <input type="password" class="form-control" name="password" required=""/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <p class="mt-5 mb-3 " style="color: red" th:text="${msg}"></p>
    <p class="mt-5 mb-3 text-muted">© 2021 design by wyl</p>
</form>
</body>
</html>
```
### index.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>云平台管理系统</title>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link th:href="@{css/styleindex.css}" rel="stylesheet"/>
    <!--    <link th:href="@{/css/signin.css}" rel="stylesheet"/>-->
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script th:href="@{js/scripts.js}"></script>
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</head>
<body id="page-top">

<!-- Navigation-->
<nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="#page-top">云平台管理系统</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded" th:href="@{/main}">云平台管理</a>
                </li>
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded" th:href="@{/loginOut}">注销登录</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!--Masthead-->
<header class="masthead bg-primary text-white text-center">
    <div class="container d-flex align-items-center flex-column">
        <!-- Masthead Heading-->
        <h1 class="masthead-heading text-uppercase mb-0">云平台管理系统</h1>
        <!-- Icon Divider-->
        <div class="divider-custom divider-light">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
            <div class="divider-custom-line"></div>
        </div>
        <!-- Masthead Subheading-->
        <p class="masthead-subheading font-weight-light mb-0">SpringBoot + Thymeleaf + HTML+BootStrap + Libvirt +
            Sigar</p>
    </div>
</header>
<!-- Portfolio Section-->
<section class="page-section portfolio" id="portfolio">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Portfolio Item 1-->
            <div class="col-md-6 col-lg-4 mb-5">
                <div class="portfolio-item mx-auto" data-bs-toggle="modal" data-bs-target="#portfolioModal1">
                    <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                        <div class="portfolio-item-caption-content text-center text-white"><i
                                class="fas fa-plus fa-3x"></i></div>
                    </div>
                    <!--                    <p class="mb-5">宿主机详细信息</p>-->
                    <img class="img-fluid" th:src="@{img/index1.png}" alt="..."/>
                </div>
            </div>
            <!-- Portfolio Item 2-->
            <div class="col-md-6 col-lg-4 mb-5">
                <div class="portfolio-item mx-auto" data-bs-toggle="modal" data-bs-target="#portfolioModal2">
                    <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                        <div class="portfolio-item-caption-content text-center text-white"><i
                                class="fas fa-plus fa-3x"></i></div>
                    </div>
                    <!--                    <p class="mb-5">Libvirt连接信息</p>-->
                    <img class="img-fluid" th:src="@{img/index.png}" alt="..."/>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Copyright Section-->
<div class="copyright py-4 text-center text-white">
    <div class="container"><small>© 2022 design by wyl</small></div>
</div>
<!-- Portfolio Modals-->
<!-- Portfolio Modal 1-->
<div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" aria-labelledby="portfolioModal1"
     aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header border-0">
                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center pb-5">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <h5>版本信息</h5>
                            <p class="mb-3" th:inline="text">型号：[[${hostinfo.vendor}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数字版本：[[${hostinfo.vendorVersion}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文字版本：[[${hostinfo.vendorCodeName}]]&nbsp;&nbsp;&nbsp;内核版本：[[${hostinfo.version}]]</p>
                            <h5>内存信息</h5>
                            <p class="mb-3" th:inline="text">内存：[[${hostinfo.memory}]]k&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用内存：[[${hostinfo.memoryUsed}]]k&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用内存：[[${hostinfo.memoryFree}]]k</p>
                            <p class="mb-3" th:inline="text">交换空间：[[${hostinfo.swap}]]k&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用交换空间：[[${hostinfo.swapUsed}]]k&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用交换空间：[[${hostinfo.swapFree}]]k</p>
                            <h5>CPU信息</h5>
                            <p class="mb-4" th:inline="text">宿主机CPU数量：[[${hostinfo.cpuNum}]]个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CPU型号：[[${hostinfo.cpuModel}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CPU频率：[[${hostinfo.cpuMhz}]]Mhz</p>
                            <h5>磁盘信息</h5>
                            <p class="mb-4" th:inline="text">设备名称：[[${hostinfo.devName}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备目录：[[${hostinfo.dirName}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;磁盘总量：[[${hostinfo.fileTotal}]]KB&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <br/>使用：[[${hostinfo.fileUsed}]]KB&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用率：[[${hostinfo.fileUsePercent}]]%
                            </p>
                            <h5>网络信息</h5>
                            <p class="mb-4" th:inline="text">网卡描述：[[${hostinfo.netDescription}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网卡类型：[[${hostinfo.netType}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP地址：[[${hostinfo.netIP}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <br/>
                                MAC地址：[[${hostinfo.netMAC}]]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mask掩码：[[${hostinfo.netMask}]]
                            </p>
                            <button class="btn btn-primary" data-bs-dismiss="modal">
                                <i class="fas fa-xmark fa-fw"></i>
                                关闭窗口
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Portfolio Modal 2-->
<div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" aria-labelledby="portfolioModal2"
     aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header border-0">
                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center pb-5">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <h5>Libvirt连接信息</h5>
                            <p class="mb-4" th:inline="text">主机名称：[[${connectInformation.hostName}]]</p>
                            <p class="mb-4" th:inline="text">连接URL：[[${connectInformation.url}]]</p>
                            <p class="mb-4" th:inline="text">libVir版本：[[${connectInformation.libVirVersion}]]</p>
                            <p class="mb-4" th:inline="text">
                                hypervisor版本：[[${connectInformation.hypervisorVersion}]]</p>
                            <button class="btn btn-primary" data-bs-dismiss="modal">
                                <i class="fas fa-xmark fa-fw">
                                </i>
                                关闭窗口
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

```
### main.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:v-model="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>虚拟机管理</title>
    <!--    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous"/>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li><a th:href="@{/img}">镜像管理</a></li>
                <li><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a></li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="virtual:${virtualList}">
                    <td th:text="${virtual.id}"></td>
                    <td th:text="${virtual.name}"></td>
                    <td th:text="${virtual.state}"></td>
                    <td>
                        <div>
                            <a th:href="@{/initiate(name=${virtual.name})}">启动</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/suspended(name=${virtual.name})}">挂起</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/resume(name=${virtual.name})}">还原</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/save(name=${virtual.name})}">保存</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/restore(name=${virtual.name})}">恢复</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/shutdown(name=${virtual.name})}">关闭</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/shutdownMust(name=${virtual.name})}">强制关闭</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/reboot(name=${virtual.name})}">重启</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/delete(name=${virtual.name})}">删除</a>
                            &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                            <a th:href="@{/getSnapshotList(name=${virtual.name})}">快照管理</a>
                        </div>
                    </td>
                </tr>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>
</html>
```
### snapshot.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:v-model="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>快照管理</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
            </ul>
            <form class="navbar-form navbar-right" th:action="@{/createSnapshot}" method="get">
                <div class="form-group">
                    <input type="text" class="from-control" id="virtualName" name="virtualName" th:value="${virtualName}"/>
                </div>
                <div class="form-group">
                    <input type="text" class="from-control" id="snapshotName" name="snapshotName" placeholder="请输入快照名称" required=""/>
                </div>
                <button type="submit" class="btn btn-default">拍摄</button>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="snapshot:${snapshotList}">
                    <td th:text="${snapshot.name}"></td>
                    <td th:text="${snapshot.creationTime}"></td>
                    <td th:text="${snapshot.state}"></td>
                    <td>
                        <div>
                            <a th:href="@{/deleteSnapshot(virtualName=${virtualName},snapshotName=${snapshot.name})}">删除</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/revertSnapshot(virtualName=${virtualName},snapshotName=${snapshot.name})}">使用</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
```
### img.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:v-model="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>虚拟机管理</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li class="active"><a th:href="@{/img}">镜像管理</a></li>
                <li><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a></li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>大小</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="img:${imgList}">
                    <td th:text="${img.name}"></td>
                    <td th:text="${img.size}"></td>
                    <td>
                        <div>
                            <a th:href="@{/deleteImg(name=${img.name})}">删除</a>
                            &nbsp;|&nbsp;
                            <a th:href="@{/downImg(name=${img.name})}">下载</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
```
### storagepool.html
```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:v-model="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>存储池管理</title>
    <!--    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous"/>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li><a th:href="@{/img}">镜像管理</a></li>
                <li><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li class="active"><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a>
                </li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>name</th>
                    <th>type</th>
                    <th>capacity</th>
                    <th>available</th>
                    <th>allocation</th>
                    <th>usage</th>
                    <th>state</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="storagePool:${storagePoolList}">
                    <td th:text="${storagePool.name}"></td>
                    <td th:text="${storagePool.type}"></td>

                    <td th:inline="text">[[${storagePool.capacity}]] GB</td>
                    <td th:inline="text">[[${storagePool.available}]] GB</td>
                    <td th:inline="text">[[${storagePool.allocation}]] GB</td>

                    <td th:text="${storagePool.usage}"></td>
                    <td th:text="${storagePool.state}"></td>
                    <td>
                        <div>
                            <a th:href="@{/deleteStoragePool(name=${storagePool.name})}">删除</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
```
### addVirtual.html
```java
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>添加虚拟机</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li class="active"><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li><a th:href="@{/img}">镜像管理</a></li>
                <li><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a></li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>

<div align="center" class="center">
    <form class="form-inline" role="form" th:action="@{/addVirtual}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="sr-only" for="virtualName">虚拟机名称</label>
            <input type="text" class="from-control" id="virtualName" name="virtualName" placeholder="请输入虚拟机名称"
                   required=""/>
        </div>
        <div class="form-group">
            <label class="sr-only" for="file">选择镜像文件</label>
            <input type="file" id="file" name="file" required=""/><br/>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>

</body>
</html>
```
### addImg.html
```java
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>添加镜像</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li><a th:href="@{/img}">镜像管理</a></li>
                <li class="active"><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a></li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>

<div align="center" class="center">
    <form class="form-inline" role="form" th:action="@{/addImg}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="sr-only" for="imgName">镜像名称</label>
            <input type="text" class="from-control" id="imgName" name="imgName" placeholder="请输入镜像名称"
                   required=""/>
        </div>
        <div class="form-group">
            <label class="sr-only" for="file">选择镜像文件</label>
            <input type="file" id="file" name="file" required=""/><br/>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>

</body>
</html>
```
### addStoragepool.html
```java
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>添加虚拟机</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" th:href="@{/index}">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/main}">虚拟机基础管理<span class="sr-only">(current)</span></a></li>
                <li><a th:href="@{/toAddVirtual}">添加虚拟机</a></li>
                <li><a th:href="@{/img}">镜像管理</a></li>
                <li><a th:href="@{/toAddImg}">添加镜像</a></li>
                <li><a th:href="@{/storagePoolList}">存储池管理</a></li>
                <li class="active"><a th:href="@{/toCreateStoragepool}">添加存储池</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a th:href="@{/openOrCloseNetWork(netState=${netState})}" th:inline="text">网络状态：[[${netState}]]</a>
                </li>
                <li><a th:href="@{/loginOut}">注销登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>

<div align="center" class="center">
    <form class="form-inline" role="form" th:action="@{/createStoragepool}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="sr-only" for="storagepoolName">存储池名称</label>
            <input type="text" class="from-control" id="storagepoolName" name="storagepoolName" placeholder="请输入存储池名称"
                   required=""/>
        </div>
        <div class="form-group">
            <label class="sr-only" for="storagepoolPath">存储池路径</label>
            <input type="text" class="from-control" id="storagepoolPath" name="storagepoolPath" placeholder="请输入存储池路径"
                   required=""/>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>

</body>
</html>
```
