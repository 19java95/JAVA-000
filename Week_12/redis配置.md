在docker 环境下：

## 配置主从

### 找到配置文件

1.下载源码响应版本源码文件 。解压之后 文件中存在 redis.conf 文件 并复制出来。

### 部署方案

| 机器IP        | 角色   | 端口 |
| ------------- | ------ | ---- |
| 120.79.218.62 | Master | 6379 |
| 120.79.218.62 | Slave1 | 5672 |
| 120.79.218.62 | Slave2 | 9042 |

### redis配置文件修改

#### 参考配置

```

bind  192.168.198.131 　　//两台主机分别改为自己的IP
logfile  "/data/redis/logs/redis.log"
daemonize yes 　　//启用守护模式
protected-mode no 　　//protected-mode 是3.2 之后加入的新特性，为了禁止公网访问redis cache，加强redis安全的。根据自己需要配置，它启用的条件，有两个，没有bind IP 以及没有设置访问密码。

requirepass  "admin.123"  　　//设置redis登录密码
masterauth  "admin.123" 　　//主从认证密码，否则主从不能同步

//以下只有 Slave 添加就行。
slaveof 192.168.198.131 6379 #slaveof表示作为从库的配置
slave-read-only yes　　 //slave 默认就是只读的，这里不用管。
```



#### 主库配置文件

```
# 修改下面三个配置参数
#bind 127.0.0.1        #如果bind选项为空的话，则允许所有来自于可用网络接口的连接
protected-mode no      #保护模式，若为yes，只允许本地客户端连接
appendonly yes         #开启后，Redis会把每次写入的数据在接收后都写入 appendonly.aof 文件，每次启动时Redis都会先把这个文件的数据读入内存里
```

#### 从库配置文件

```
port 5672
#port 9042 还有这个端口

#bind 127.0.0.1        #如果bind选项为空的话，则允许所有来自于可用网络接口的连接
pidfile /var/run/redis_5672.pid
slaveof 120.79.218.62 6379  #slaveof表示作为从库的配置
slave-read-only yes #从库只能读操作
protected-mode no      #保护模式，若为yes，只允许本地客户端连接
appendonly yes         #开启后，Redis会把每次写入的数据在接收后都写入 appendonly.aof 文件，每次启动时Redis都会先把这个文件的数据读入内存里
```

#### 启动redis

```bash
docker run -p 6379:6379 -v $PWD/data:/data -v /home/tangtian/tangtian/redis/6.0.6-conf/redis-master.conf:/etc/redis/redis.conf -d redis:6.0.6 redis-server  /etc/redis/redis.conf --appendonly yes
```

```bash
docker run -p 5672:5672 -v $PWD/slaveData:/data -v /home/tangtian/tangtian/redis/6.0.6-conf/redis-slave.conf:/etc/redis/redis.conf -d redis:6.0.6 redis-server  /etc/redis/redis.conf --appendonly yes
```

```bash
docker run -p 9042:9042 -v $PWD/slaveData2:/data -v /home/tangtian/tangtian/redis/6.0.6-conf/redis-slave2.conf:/etc/redis/redis.conf -d redis:6.0.6 redis-server  /etc/redis/redis.conf --appendonly yes
```



注意宿主机配置文件的路径

### 通过命令设置从库（5672端口为从库 可选)

进入5672容器：容器id：cc8f220434ea

```
docker exec -it cc8f220434ea /bin/bash
```

进入cli

```
redis-cli -p 5672
```

查询主从信息

```
info Replication
```

role:master

connected_slaves:0



修改为从库

```
slaveof 120.79.218.62 6379 
```

再次查看主从信息

```
info Replication
```

role:slave

master_host:120.79.218.62

master_port:6379

### 验证主从

进入master（6379）

```
docker exec -it 47ed7ce5b38c /bin/bash
```

```
redis-cli
```

```
info Replication
```

role:master

connected_slaves:2

slave0:ip=120.79.218.62,port=5672,state=online,offset=154,lag=1

slave1:ip=120.79.218.62,port=9042,state=online,offset=154,lag=1



## 配置sentinel

使用docker启动3个redis服务器，一个master（主）, 两个slave （从），进行主从配置。3台redis服务器分别启动一个哨兵sentinel。

```undefined
redis-cli 连接redis服务器的命令
redis默认使用6379端口
redis-sentinel 启动redis哨兵 启动的第一个默认26379，此后26380,26381, ...
```

### 配置方案

| 名字            | 端口  |      |
| --------------- | ----- | ---- |
| redis_sentinel1 | 26379 |      |
| redis_sentinel2 | 26380 |      |
| redis_sentinel3 | 26381 |      |

### 配置文件模版

```
port 26379
logfile "26379.log"
dir /data
sentinel deny-scripts-reconfig yes
sentinel monitor mymaster redis_master 6379 2
sentinel down-after-milliseconds mymaster 30000
sentinel parallel-syncs mymaster 1
```

### 启动服务

sentinel1节点启动命令

```bash
docker run -p 26379:26379 --name redis_sentinel1 \
  -v /home/tangtian/tangtian/redis/sentinel/conf/sentinel1.conf:/usr/local/etc/redis/redis-sentine1.conf \
  -v /home/tangtian/tangtian/redis/sentinel/sen_data_1:/data \
  -d redis:6.0.6 redis-sentinel /usr/local/etc/redis/redis-sentine1.conf
```

sentinel2节点启动命令

```bash
docker run -p 26380:26379 --name redis_sentinel2 \
  -v /home/tangtian/tangtian/redis/sentinel/conf/sentinel2.conf:/usr/local/etc/redis/redis-sentine1.conf \
  -v /home/tangtian/tangtian/redis/sentinel/sen_data_2:/data \
  -d redis:6.0.6 redis-sentinel /usr/local/etc/redis/redis-sentine1.conf
```

sentinel3节点启动命令

```bash
docker run -p 26381:26379 --name redis_sentinel3 \
  -v /home/tangtian/tangtian/redis/sentinel/conf/sentinel3.conf:/usr/local/etc/redis/redis-sentine1.conf \
  -v /home/tangtian/tangtian/redis/sentinel/sen_data_3:/data \
  -d redis:6.0.6 redis-sentinel /usr/local/etc/redis/redis-sentine1.conf
```

### 验证哨兵

#### 停掉master节点 

```
docker stop 47ed7ce5b38c
```

#### 9042 端口日志

1:S 05 Jan 2021 00:47:35.498 * REPLICAOF 120.79.218.62:9042 enabled (user request from 'id=21 addr=120.79.218.62:55806 fd=14 name=sentinel-0a98ed77-cmd age=270 idle=0 flags=x db=0 sub=0 psub=0 multi=4 qbuf=339 qbuf-free=32429 obl=45 oll=0 omem=0 events=r cmd=exec user=default')

1:S 05 Jan 2021 00:47:35.499 # CONFIG REWRITE failed: Permission denied

1:S 05 Jan 2021 00:47:35.506 * Connecting to MASTER 120.79.218.62:9042

1:S 05 Jan 2021 00:47:35.506 * MASTER <-> REPLICA sync started

#### sentinel1日志

1:X 05 Jan 2021 00:47:34.225 # +sdown master mymaster 120.79.218.62 6379

1:X 05 Jan 2021 00:47:34.287 # +new-epoch 1

1:X 05 Jan 2021 00:47:34.289 # +vote-for-leader 0a98ed7798e8c573ca21a1849ed570e34c1b418a 1

1:X 05 Jan 2021 00:47:35.320 # +odown master mymaster 120.79.218.62 6379 #quorum 3/2

1:X 05 Jan 2021 00:47:35.320 # Next failover delay: I will not start a failover before Tue Jan 5 00:53:34 2021

1:X 05 Jan 2021 00:47:35.497 # +config-update-from sentinel 0a98ed7798e8c573ca21a1849ed570e34c1b418a 172.17.0.8 26380 @ mymaster 120.79.218.62 6379

1:X 05 Jan 2021 00:47:35.497 # +switch-master mymaster 120.79.218.62 6379 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.497 * +slave slave 120.79.218.62:5672 120.79.218.62 5672 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.497 * +slave slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:48:05.502 # +sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:49:04.624 # -sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

#### sentinel2日志

1:X 05 Jan 2021 00:47:35.606 # +failover-end master mymaster 120.79.218.62 6379

1:X 05 Jan 2021 00:47:35.606 # +switch-master mymaster 120.79.218.62 6379 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.606 * +slave slave 120.79.218.62:5672 120.79.218.62 5672 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.606 * +slave slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:48:05.616 # +sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:49:03.802 # -sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:49:13.775 * +convert-to-slave slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

#### sentinel3日志

1:X 05 Jan 2021 00:47:34.236 # +sdown master mymaster 120.79.218.62 6379

1:X 05 Jan 2021 00:47:34.287 # +new-epoch 1

1:X 05 Jan 2021 00:47:34.289 # +vote-for-leader 0a98ed7798e8c573ca21a1849ed570e34c1b418a 1

1:X 05 Jan 2021 00:47:34.326 # +odown master mymaster 120.79.218.62 6379 #quorum 3/2

1:X 05 Jan 2021 00:47:34.326 # Next failover delay: I will not start a failover before Tue Jan 5 00:53:35 2021

1:X 05 Jan 2021 00:47:35.497 # +config-update-from sentinel 0a98ed7798e8c573ca21a1849ed570e34c1b418a 172.17.0.8 26380 @ mymaster 120.79.218.62 6379

1:X 05 Jan 2021 00:47:35.497 # +switch-master mymaster 120.79.218.62 6379 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.497 * +slave slave 120.79.218.62:5672 120.79.218.62 5672 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:47:35.497 * +slave slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:48:05.523 # +sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042

1:X 05 Jan 2021 00:49:04.648 # -sdown slave 120.79.218.62:6379 120.79.218.62 6379 @ mymaster 120.79.218.62 9042



#### 9042 端口配置

role:master

connected_slaves:2

slave0:ip=120.79.218.62,port=5672,state=online,offset=296553,lag=1

slave1:ip=120.79.218.62,port=6379,state=online,offset=296553,lag=0

master_replid:b70e214df93abb71b55142f814c9ad29e6a381db

master_replid2:c6da51a7ac8065e7583c857e0a5ed29f41d122bd





#### 结果

9042 节点变为主节点

重启 6379 该节点变为从节点

### Redis Cluster 

#### 搭建方案

| ip            | port | ramark         |
| ------------- | ---- | -------------- |
| 120.79.218.62 | 7001 | 主节点master-1 |
| 120.79.218.62 | 7002 | 主节点master-2 |
| 120.79.218.62 | 7003 | 主节点master-3 |
| 120.79.218.62 | 7004 | 从节点slave-1  |
| 120.79.218.62 | 7005 | 从节点slave-2  |
| 120.79.218.62 | 7006 | 从节点slave-3  |

#### 配置文件REDIS CLUSTER

主节点

```
port 7001
appendonly yes 
daemonize no #后台运行
protected-mode no # 允许外部IP访问
pidfile /var/run/redis_7001.pid

cluster-enabled yes 
cluster-config-file nodes.conf #集群配置文件 。自动创建
cluster-node-timeout 5000  #集群超时时间
# cluster-replica-validity-factor 10
# cluster-migration-barrier 1
# cluster-require-full-coverage yes
# cluster-replica-no-failover no
# cluster-allow-reads-when-down no
```

从节点

```
port 7004 
cluster-enabled no 
appendonly yes 
daemonize no 
protected-mode no 
pidfile /var/run/redis_7004.pid 
slaveof 192.168.1.103 7001


```

#### 创建容器

docker-compose文件

```yaml
version: '3'
services:
  redis-master-1:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    ports:
      - 7001:7001
      - 17001:17001
    volumes:
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster1.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data1:/data

  redis-master-2:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    volumes:
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster2.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data2:/data
    ports:
      - 7002:7002
      - 17002:17002

  redis-master-3:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    volumes:
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster3.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data3:/data
    ports:
      - 7003:7003
      - 17003:17003

  redis-slave-1:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    ports:
      - 7004:7004
      - 17004:17004
    volumes: 
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster4.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data4:/data
  redis-slave-2:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    ports:
      - 7005:7005
      - 17005:17005
    volumes:
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster5.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data5:/data
  
  redis-slave-3:
    image: redis:6.0.6
    command: ["redis-server", "/home/redis/cluster/redis.conf"]
    ports:
      - 7006:7006
      - 17006:17006
    volumes:
      - /home/tangtian/tangtian/redis/6.0.6-conf/redis-cluster6.conf:/home/redis/cluster/redis.conf
      - /home/tangtian/tangtian/redis/cluster/data6:/data
```

#### 创建集群*

　进入主节点一个容器节点

```
#执行redis-cli
redis-cli --cluster create 120.79.218.62:7001 120.79.218.62:7002 120.79.218.62:7003 --cluster-replicas 0
```

--cluster-replicas 0 表示集群主节点需要多少个从节点，即3台服务器构成集群，每台服务器设置0台从服务器

##### 结果

```
>>> Performing hash slots allocation on 3 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
M: 1ed218f7d2a8dc8edbb0552c894d384b75af6bbf 120.79.218.62:7001
   slots:[0-5460] (5461 slots) master
M: 9704ed75014023dc0b59006caf6d4304fef1bfa6 120.79.218.62:7002
   slots:[5461-10922] (5462 slots) master
M: b972cd354ff6ac752dabf9e3548b0c62b8befbd8 120.79.218.62:7003
   slots:[10923-16383] (5461 slots) master
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
.....
>>> Performing Cluster Check (using node 120.79.218.62:7001)
M: 1ed218f7d2a8dc8edbb0552c894d384b75af6bbf 120.79.218.62:7001
   slots:[0-5460] (5461 slots) master
M: 9704ed75014023dc0b59006caf6d4304fef1bfa6 120.79.218.62:7002
   slots:[5461-10922] (5462 slots) master
M: b972cd354ff6ac752dabf9e3548b0c62b8befbd8 120.79.218.62:7003
   slots:[10923-16383] (5461 slots) master
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```

