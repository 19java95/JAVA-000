## 作业:

#### 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

https://github.com/tangtian8/JAVA-000/tree/main/Week_13/JMSActiveMQ

#### 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github

https://github.com/tangtian8/JAVA-000/tree/main/Week_13/kafkaDemo/src

#### Kafka 集群搭建

系统环境:CentOS Linux release 7.9.2009 (Core)

##### zookpeer搭建

###### 下载zookeeper3.6.1

```
wget https://archive.apache.org/dist/zookeeper/zookeeper-3.6.1/apache-zookeeper-3.6.1-bin.tar.gz
```

###### 解压压缩包

```
 tar -zvxf apache-zookeeper-3.6.1-bin.tar.gz 
```

###### 修改配置文件

```
cd apache-zookeeper-3.6.1-bin
cp  ./conf/zoo_sample.cfg ./conf/zoo.cfg
```

###### 启动zookper

```
./bin/zkServer.sh start
```

###### 查看端口启动

``` 
netstat -ntlp
```

tcp    0   0 0.0.0.0:2181      0.0.0.0:*        LISTEN   31164/java    

###### 注意事项

注意操作系统防火墙需要开启2181端口

##### kafka搭建

###### 下载解压

```
wget https://mirror.bit.edu.cn/apache/kafka/2.6.0/kafka_2.12-2.6.0.tgz
tar -zvxf kafka_2.12-2.6.0.tgz
```

###### 复制三分解压文件

```
[root@izwz91d1e0fhqsue2owv4az tangtian]# cp -r kafka_2.12-2.6.0 kafka_2.12-2.6.0_1
[root@izwz91d1e0fhqsue2owv4az tangtian]# cp -r kafka_2.12-2.6.0 kafka_2.12-2.6.0_2
[root@izwz91d1e0fhqsue2owv4az tangtian]# cp -r kafka_2.12-2.6.0 kafka_2.12-2.6.0_3
```

###### 分别修改配置文件

kafka_2.12-2.6.0_1

```
[root@izwz91d1e0fhqsue2owv4az tangtian]# cd kafka_2.12-2.6.0_1
[root@izwz91d1e0fhqsue2owv4az kafka_2.12-2.6.0_1]# vim ./config/server.properties
```

```
broker.id=0
listeners=PLAINTEXT://0.0.0.0:9092
advertised.listeners=PLAINTEXT://120.79.218.62:9092
zookeeper.connect=120.79.218.62:2181
log.dirs=/home/tangtian/tangtian/kafka/node0
```

kafka_2.12-2.6.0_2

```
[root@izwz91d1e0fhqsue2owv4az tangtian]# cd kafka_2.12-2.6.0_2
[root@izwz91d1e0fhqsue2owv4az kafka_2.12-2.6.0_1]# vim ./config/server.properties
```

```
broker.id=1
listeners=PLAINTEXT://0.0.0.0:9093
advertised.listeners=PLAINTEXT://120.79.218.62:9093
zookeeper.connect=120.79.218.62:2181
log.dirs=/home/tangtian/tangtian/kafka/node1
```

kafka_2.12-2.6.0_3

```
[root@izwz91d1e0fhqsue2owv4az tangtian]# cd kafka_2.12-2.6.0_3
[root@izwz91d1e0fhqsue2owv4az kafka_2.12-2.6.0_1]# vim ./config/server.properties
```

```
broker.id=2
listeners=PLAINTEXT://0.0.0.0:9094
advertised.listeners=PLAINTEXT://120.79.218.62:9094
zookeeper.connect=120.79.218.62:2181
log.dirs=/home/tangtian/tangtian/kafka/node1
```

###### 分别启动kafka

kafka_2.12-2.6.0_1

```
cd kafka_2.12-2.6.0_1
nohup ./bin/kafka-server-start.sh ../config/server.properties &
```

kafka_2.12-2.6.0_2

```
cd kafka_2.12-2.6.0_2
nohup ./bin/kafka-server-start.sh ../config/server.properties &
```

kafka_2.12-2.6.0_3

```
cd kafka_2.12-2.6.0_3
nohup ./bin/kafka-server-start.sh ../config/server.properties &
```

###### 查看端口启动

``` 
netstat -ntlp
```

###### 注意事项

注意操作系统防火墙需要开启9092,9093,9094端口