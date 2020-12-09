学习笔记

## 第八周作业

##### 设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。并在新结构在演示常见的增删改查操作

https://github.com/tangtian8/JAVA-000/tree/main/Week_08/0801

水平分库分表配置

```xml
bindingTables:
- order_test
defaultDataSourceName: test0
defaultDatabaseStrategy:
  inline:
    algorithmExpression: test$->{user_id % 2}
    shardingColumn: user_id
tables:
  order_test:
    actualDataNodes: test$->{0..1}.order_test$->{0..15}
    logicTable: order_test
    tableStrategy:
      inline:
        algorithmExpression: order_test$->{(int) ((Math.random() * 16))}
        shardingColumn: id
```

##### 基于ShardingSphere的Atomikos XA实现一个简单 的分布式事务应

https://github.com/tangtian8/JAVA-000/tree/main/Week_08/0802

