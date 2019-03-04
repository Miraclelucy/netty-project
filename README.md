
# 第1讲、Netty 是异步的事件驱动的网络应用
SEDA->staged event driven architecture
将一个请求的处理分成多个阶段，每个阶段的处理用不同数量的线程来处理。第一个阶段处理完要执行第二个阶段时，是采用事件驱动的模式来进行沟通的。


# 第3讲、基本概念介绍
1. RPC库：protobuf 、thrift
2. http协议：短链接  无状态的基于请求响应的协议
   websocket协议：长连接 ; 全双工的通信 即时通讯 ; 可以允许只传送数据本身 没有head ; 心跳检测


# 第4讲、intellij安装gradle环境：
1. 下载官网的gradle包，本次使用的是gradle-4.10.2.如果版本过高会出现和intellij不兼容的问题。
2. 设置本地win10中的环境变量 用gradle -v查看成功设置后的gradle的版本
3. 在intellij的setting中搜索gradle,配置gradle-home


# 第5-6讲 netty执行流程分析
<font color="red"> ch01-TestServer 实现hello world </font>


# 第7讲 基于netty的简单socket示例
ch02-MyServer和MyClient

netty的应用：
1. 支持dubbo，spark等业界开源框架的底层通信
2. 支持长连接的开发，在线聊天等实时通讯的场景


# 第8讲 基于netty的实现多客户端连接
ch03-MyChatServer和MyChatClient


# 第9讲 心跳检测
ch04-MyServer
什么时候需要心跳检测？
1. 集群中主节点和从节点，节点挂掉或者节点因为网络阻塞消息没法到达，那么节点与节点之间如何感知对方还是活着的？比如B给A发送心跳包
2. 实现长连接时。如手机的客户端和服务端建立好了长连接，如果客户端和服务端已经建立了长连接，但是如果手机开了飞行模式，如果这是服务端向客户端发送消息，客户端是收不到的。这种情况下就需要心跳检测了，客户端每隔5秒向服务器端发送一个心跳包，服务器收到后返回一个心跳包，如果在规定时间内客户端收到了这个包，就会认为客户端和服务器的长连接还是正常的，否则客户端就强制断开连接，并调用相应的close方法。


# 第10-11讲 基于websocket的服务器 实现长连接
ch05-MyWebSocketServer
- 轮询的形式很浪费资源，不断地去轮询，如果每次服务器没有数据发给客户端，就会浪费很多网络和计算资源。
- WebSocket实现了真正意义上的长连接，
- WebSocket客户端编写


# 第12-14讲 Protobuf--搭建环境
ch06-ProtobufTest  Protobuf完整实例--在同一个jvm中通信
> remote method invocation远程方法调用：只在java中使用
client:stub
server:skeleton
序列化与反序列化：编码和解码
rpc:remote procedure call 远程过程调用：跨语言
1. 定义一个接口说明文件（idl）：描述了对象、对象成员、接口方法等一系列的信息
2. 通过rpc的编译器将说明文件编译成具体语言文件
3. 在客户端和服务器端分别引入rpc编译器所生成的文件，即可像调用本地方法一样调用远程方法


# 第15讲 Protobuf与netty集成
ch07-ProtobufClient
ch07-ProtobufServer


# 第16讲 Protobuf与netty集成 并实现在socket上传输多种类型的protobuf数据
ch08-ProtobufClient
ch08-ProtobufServer


# 第17讲 Protobuf最佳实践
一般有4个工程:
- ServerProject(引用Protobuf-Java)
- Protobuf-Java
- data.proto
- ClientProject(引用Protobuf-Java)

> 使用git开发时一定会大量使用分支:
> branch：
   > develop(开发)
   > test(测试-给测试人员和产品经理测试用)
   > product(生产)
 
最好的解决Protobuf工程间引用的方法是 git subtree


# 第18讲 thrift应用和开发
1. thrift是CS架构的，客户端和服务端可以分别用不同的编程语言开发。那么，一定存在一种中间语言IDL（interface description language）来关联客户端和服务端。
2. thrift不支持无符号的类型。因为很多语言不持持无符号类型，如Java。
3. thrift的数据类型：byte,i16,i32,i64,duble,string
thrift的容器类型：struct,enum,service,exception,list,set,map,
4. 类型定义：thrift支持typedef定义
- typedef i32 int;
- typedef i64 long;
5. 命令空间与文件包含：
- namespace 语言名 包名
- include 文件全名
6. 可选与必选：提供了2个关键词required,optional
