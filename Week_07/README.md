## 插入100万订单模拟数据，测试不同方式的插入效率

Version:5.7.32 (MySQL Community Server (GPL))

#### 插入1000000 的效率

| Total Time（s） | Buffer Usage | Write per Second | 备注                                                 |
| --------------- | ------------ | ---------------- | ---------------------------------------------------- |
| 4310            | 14.1%        | 232              | 通过存储过程直接插入到存储引擎为InnoDB的表中         |
| 30              |              |                  | 同样的存储过程插入到MEEORY表。最后在插入到InnoDB的表 |
|                 |              |                  |                                                      |

## mysql主从配置

1. 拉取镜像`docker pull mysql:5.7`

2. 启动容器

   ```sh
   docker run -p 3306:3306 --name mysql3306 -v /tangtian/docker/mysql3306/conf:/etc/mysql/conf.d -v /tangtian/docker/mysql3306/logs:/logs -v /tangtian/docker/mysql3306/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
   
   
   docker run -p 3307:3306 --name mysql3307 -v /tangtian/docker/mysql3307/conf:/etc/mysql/conf.d -v /tangtian/docker/mysql3307/logs:/logs -v /tangtian/docker/mysql3307/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
   ```

3. 修改配置文件

   `master数据库`

   \# cd /tangtian/docker/mysql3306/conf

   \# vim myconf

   ```
   [mysqld]
   server-id=1
   log-bin=mysql-bin
   binlog-do-db=test
   binlog_cache_size=1M
   binlog_format=mixed
   slow_query_log=ON
   long_query_time=1
   ```

   `slave数据库`

   \# cd /tangtian/docker/mysql3307/conf

   \# vim myconf

   ```
   [mysqld]
   server-id=2
   log-bin=mysql-bin
   replicate-do-db=testreplicate-ignore-db=mysql
   replicate-do-table
   ```

4. 查看`master`

   mysql命令: `SHOW master STATUS;`

   'mysql-bin.000003','1484203','test','

5. 配置slave节点

   mysql命令

   ```
   stop slave;//先关闭slave
   CHANGE MASTER TO
   	MASTER_HOST='localhost',
       MASTER_USER='root',
       MASTER_PASSWORD='123456',
       MASTER_LOG_FILE='mysql-bin.000003',
       MASTER_LOG_POS=1484203;//在slave监听master数据库bin日志
   start slave;//开启slave
   ```

6. 完成配置

   show slave status \G;    mysql命令行命令

   ```
   Slave_IO_Running: Yes
   Slave_SQL_Running: Yes
   ```

   表示配置成功

## 读写分离-动态切换数据源版本1.0

https://github.com/tangtian8/JAVA-000/tree/main/Week_07/1.0

- [x] 基于spring/springboot,配置多个数据源(例如2个，master和slave)

- [x] 根据具体的Service方法是否会操作数据,注入不同的数据源，1.0版本

- [ ] 基于操作AbstractRoutingDataSource和自定义注解readOnly等,简化自动切换数据源

- [ ] 支持配置多个从库

- [ ] 支持多个从库的负载均衡

## 读写分离-数据库框架版本2.0

https://github.com/tangtian8/JAVA-000/tree/main/Week_07/2.0

