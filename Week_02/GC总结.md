## 相关知识点

### Serial GC(串行GC)

Serial GC 对年轻代使用 [mark-copy(标记-复制) 算法](https://plumbr.eu/handbook/garbage-collection-algorithms/removing-unused-objects/copy), 对老年代使用 [mark-sweep-compact(标记-清除-整理)算法](https://plumbr.eu/handbook/garbage-collection-algorithms/removing-unused-objects/compact). 顾名思义, 两者都是**单线程**的垃圾收集器,不能进行并行处理。两者都会触发全线暂停(STW),停止所有的应用线程。

### Parallel GC(并行GC)

并行垃圾收集器这一类组合, 在年轻代使用 [标记-复制(mark-copy)算法](https://plumbr.eu/handbook/garbage-collection-algorithms/removing-unused-objects/copy), 在老年代使用 [标记-清除-整理(mark-sweep-compact)算法](https://plumbr.eu/handbook/garbage-collection-algorithms/removing-unused-objects/compact)。年轻代和老年代的垃圾回收都会触发STW事件,暂停所有的应用线程来执行垃圾收集。两者在执行 标记和 复制/整理阶段时都使用**多个线程**, 因此得名“**(Parallel)**”。通过并行执行, 使得GC时间大幅减少。

### CMS

全称 Concurrent Mark Sweep，老年代收集器，致力于获取最短回收停顿时间（即缩短垃圾回收的时间），使用`标记-清除`算法，多线程，优点是并发收集（用户线程可以和 GC 线程同时工作），停顿小。

初始标记(CMS-initial-mark) -> 并发标记(CMS-concurrent-mark) -> 重新标记(CMS-remark) -> 并发清除(CMS-concurrent-sweep) ->并发重设状态等待下次CMS的触发(CMS-concurrent-reset)。

来源：https://www.bookstack.cn/read/gc-handbook/spilt.2.04_GC_Algorithms_Implementations_CN.md

并发收集：

> 指用户线程与GC线程同时执行（不一定是并行，可能交替，但总体上是在同时执行的），不需要停顿用户线程（其实在 CMS 中用户线程还是需要停顿的，只是非常短，GC 线程在另一个 CPU 上执行）；

并行收集：

> 指多个 GC 线程并行工作，但此时用户线程是暂停的；

作者：健健君
链接：https://juejin.im/post/6844903984998645768
来源：掘金

### G1

G1 GC，全称Garbage-First Garbage Collector

## 各个GC的对比

| 收集器            |      |        |                    | 目标         | 适用场景                               | 可以与cms配合 |
| ----------------- | ---- | ------ | ------------------ | ------------ | -------------------------------------- | ------------- |
| Serial            | 串行 | 新生代 | 复制算法           | 响应速度优先 | 单CPU环境下的Client模式                | 是            |
| Serial Old        | 串行 | 老年代 | 标记-整理          | 响应速度优先 | 单CPU环境下的Client模式、CMS的后备方案 |               |
| parNew            | 并行 | 新生代 | 复制算法           | 响应速度优先 | 多CPU环境时在Server模式下与CMS配合     | 是            |
| Parallel Scavenge | 并行 | 新生代 | 复制算法           | 吞吐量优先   | 在后台运算而不需要太多交互的任务       |               |
| Parrllel Old      | 并行 | 老年代 | 标记-整理          | 吞吐量优先   | 在后台运算而不需要太多交互的任务       |               |
| CMS               | 并发 | 老年代 | 标记-清除          | 响应速度优先 | 集中在互联网网站或者B/S系统上的应用    |               |
| G1                | 并发 | both   | 标记-整理+复制算法 | 响应速度优先 | 面向服务端应用,将来会替换CMS÷          |               |

作者：秦金卫
链接：https://juejin.im/post/6844903984998645768
来源：极客时间



### 不同GC在不同内存参数下回收情况

 `java -XX:+UseSerialGC -Xms1024m -Xmx1024m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

电脑配置

8 GB 2133 MHz LPDDR3

1.4 GHz Quad-Core Intel Core i5

使用`-XX:+UseConcMarkSweepGC`进行`ParNew + CMS + Serial Old`进行内存回收，优先使用`ParNew + CMS`

|       | 串行（-XX:+UseSerialGC）                | 并行（-XX:+UseParallelGC）              | CMS（-XX:+UseConcMarkSweepGC）                  | G1(-XX:+UseG1GC)                          |
| ----- | --------------------------------------- | --------------------------------------- | ----------------------------------------------- | ----------------------------------------- |
| 512M  | YoungGC:15. FullGC:3 生成对象次数:9526  | YoungGC:26. FullGC:12 生成对象次数:8745 | YoungGC:19. YoungGCYoungGC:8 生成对象次数:10094 | YoungGC:70. mixedGC:34 生成对象次数:11387 |
| 1024M | YoungGC:10. FullGC:0 生成对象次数:11149 | YoungGC:21. FullGC:2 生成对象次数:12489 | YoungGC:11. CMSGC:2 生成对象次数:11776          | YoungGC:16. mixedGC:5 生成对象次数:11188  |
| 2048M | YoungGC:5. FullGC:0 生成对象次数:10562  | YoungGC:6. FullGC:0 生成对象次数:11935  | YoungGC:5.CMSGC:0生成对象次数:10284             | YoungGC:8. mixedGC:0 生成对象次数:8033    |
| 4086M | YoungGC:2. FullGC:0 生成对象次数:8331   | YoungGC:2. FullGC:0 生成对象次数:8446   | YoungGC:5.CMSGC:0生成对象次数:10565             | YoungGC:12. mixedGC:0 生成对象次数:12556  |
|       |                                         |                                         |                                                 |                                           |

发生在 young(主要是Survivor)区的gc称为 minor gc				发生在 old(Tenured)区的gc称为 major gc

#### 总结分析

1.内存越小，GC的次数越多

2.每次gc的时候，young区GC的次数高于在其他区GC的次数





### 不同GC在不同内存参数下压测情况

电脑配置

8 GB 2133 MHz LPDDR3

1.4 GHz Quad-Core Intel Core i5

`java -jar -Xmx512M -Xms512M -XX:+UseSerialGC gateway-server-0.0.1-SNAPSHOT.jar`

`wrk -t4 -c200 -d60s http://localhost:8088/api/hello`



200个连接 60s

|       | 串行（-XX:+UseSerialGC） | 并行（-XX:+UseParallelGC） | CMS（-XX:+UseConcMarkSweepGC） | G1(-XX:+UseG1GC) |
| ----- | ------------------------ | -------------------------- | ------------------------------ | ---------------- |
| 512M  | QPS: 38051.04            | QPS:38833.08               | QPS:39318.52                   | QPS:45115.60     |
| 1024M | QPS: 43886.37            | QPS:38046.35               | QPS:43408.35                   | QPS:43991.15     |
| 2048M | QPS:37261.00             | QPS:39359.32               | QPS:48705.59                   | QPS:44591.82     |
| 4086M | QPS:38297.33             | QPS:40191.24               | QPS:45532.58                   | QPS:44656.08     |
|       |                          |                            |                                |                  |

#### 总结分析

相同环境压测的情况下

1.CMSGC、G1GC的吞吐量更高，由于并发，STW时间更短，垃圾回收的暂停时间短了，把时间留给了处理程序，所有吞吐量高了。

