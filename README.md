# 自己对下列技术的关键点思考

## JVM

### 字节码

javac命令编译成.class 文件，可以在JVM中运行。编译好的字节码.class文件，是一堆16进制的字节。需要 javap （不加 .class后缀）反编译，里面就有很多操作指令。看着指令，去了解helloWorld的世界。

### JDK1.8的JVM内存结构

线程共享：Java堆区，方法区。

线程私有：程序计数器，Java虚拟机栈，本地方法栈

java堆区:
新生代/Minor/Young + Major/old/老年代【-Xms -Xmn】

新生代：
Eden（8）+From（1）+TO（1）【-Xmn】

### 常见GC

| 收集器            |      |        |                    | 目标         | 适用场景                               | 可以与cms配合 |
| ----------------- | ---- | ------ | ------------------ | ------------ | -------------------------------------- | ------------- |
| Serial            | 串行 | 新生代 | 复制算法           | 响应速度优先 | 单CPU环境下的Client模式                | 是            |
| Serial Old        | 串行 | 老年代 | 标记-整理          | 响应速度优先 | 单CPU环境下的Client模式、CMS的后备方案 |               |
| parNew            | 并行 | 新生代 | 复制算法           | 响应速度优先 | 多CPU环境时在Server模式下与CMS配合     | 是            |
| Parallel Scavenge | 并行 | 新生代 | 复制算法           | 吞吐量优先   | 在后台运算而不需要太多交互的任务       |               |
| Parrllel Old      | 并行 | 老年代 | 标记-整理          | 吞吐量优先   | 在后台运算而不需要太多交互的任务       |               |
| CMS               | 并发 | 老年代 | 标记-清除          | 响应速度优先 | 集中在互联网网站或者B/S系统上的应用    |               |
| G1                | 并发 | both   | 标记-整理+复制算法 | 响应速度优先 | 面向服务端应用,将来会替换CMS÷          |               |

## NIO

### IO模型与相关概念

阻塞I/O(BIO)、非阻塞I/O(NIO)、多路复用、信号驱动IO(SIGIO)、异步IO(AIO)

### Netty

## 并发编程

### 多线程基础

#### 线程的创建

继承Thread类创建线程、实现Runnable接口创建线程、使用Callable和Future创建线程、使用线程池例如用Executor框架

#### 线程的状态

<img src="https://img-blog.csdnimg.cn/20181120173640764.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3BhbmdlMTk5MQ==,size_16,color_FFFFFF,t_70" alt="线程状态图" style="zoom:50%;" />

### 线程安全

并发相关的性质、synchronized的实现、volatile、final、锁、常用线程安全数据结构

### 线程池原理与应用

![图1 ThreadPoolExecutor UML类图](https://p1.meituan.net/travelcube/912883e51327e0c7a9d753d11896326511272.png)

1. 频繁申请/销毁资源和调度资源，将带来额外的消耗，可能会非常巨大。
2. 对资源无限申请缺少抑制手段，易引发系统资源耗尽的风险。
3. 系统无法合理管理内部的资源分布，会降低系统的稳定性。

### Java并发包(JUC)

锁机制类 Locks : Lock, Condition, ReadWriteLock

原子操作类 Atomic : AtomicInteger

线程池相关类 Executer : Future, Callable, Executor

信号量三组工具类 Tools : CountDownLatch, CyclicBarrier, Semaphore

并发集合类 Collections : CopyOnWriteArrayList, ConcurrentMap

### 并发工具类

AQS、Semaphore - 信号量、CyclicBarrier、CountDownLatch、Future/FutureTask/CompletableFuture

## Spring 和 ORM 等框架

### Spring AOP详解

AOP-面向切面编程、IoC-控制反转

### Spring Bean生命周期

<img src="https://pic1.zhimg.com/80/754a34e03cfaa40008de8e2b9c1b815c_720w.jpg?source=1940ef5c" alt="img" style="zoom: 67%;" />

### Spring Boot核心原理

SpringBoot运行原理、基础配置、外部配置、日志配置、Profile配置、核心注解

### Spring Boot Starter

创建自己的Spring Boot Starter

### JDBC与数据库连接池

装载数据库驱动程序；建立数据库连接；创建数据库操作对象；访问数据库，执行sql语句；处理返回结果集；断开数据库连接

### ORM-Hibernate/MyBatis



## MySQL 数据库和 SQL

## 分库分表

## RPC 和微服务

## 分布式缓存

## 分布式消息队列



![总结](https://cdn.nlark.com/yuque/0/2021/png/2542319/1612274096175-e653a36c-e893-41a9-ae33-94cfca5f2899.png)

