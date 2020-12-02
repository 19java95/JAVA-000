# 作业思路

## 必做作业

### 写代码实现Spring bean 的装配（周四）

Week_05/helloword-springbean目录下

1-5：自动装配
6：通过Java类装配
7：通过XML配置装配
8-9：混合装配

### 课程提供的Student/Klass/School实现自动配置和Starter(周六)

Week_05/helloword-spring-boot-starter目录下

1.老师在实例代码中提供xml装配，我用@ComponentScan 扫描当前包和子包扫描@Component注解 装配

2.Starter 

​	01.在pom中配置

```
    <groupId>top.tangtian</groupId>
    <artifactId>helloword-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
```

​	02.把java类转化给spring管理(实现自动配置)

​	03.创建文件 src/main/resources/META-INF/spring.factories

（spring.factories文件是帮助spring-boot项目包以外的bean（即在pom文件中添加依赖中的bean）注册到spring-boot项目的spring容器的结论）

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=top.tangtian.demo.starter.StarterApplication
```

​	04.mvn clean install

​	05.引入此项目方法

```
/**
 * 
 * 1.pom文件zhong
 *          <dependency>
 *             <groupId>top.tangtian</groupId>
 *             <artifactId>helloword-spring-boot-starter</artifactId>
 *             <version>1.0-SNAPSHOT</version>
 *         </dependency>
 *
 *  2.resources配置文件中增加
 *  top.tangtian.id=12
 *  top.tangtian.name=1231
 *
 *  3.倒入DemoService调用方法
 *
 */
```

### 研究一下JDBC接口和数据库连接池,掌握它们的设计和用法(周六)

Week_05/hello-jdbc目录下

01.pom 倒入依赖（mysql、Hikari）

```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.39</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>3.4.1</version>
</dependency>
```

02.使用JDBC原生接口

加载驱动->建立连接->执行操作->释放连接

加载驱动->建立连接->执行操作(开启事物->批量操作->关闭事物)->释放连接

加载驱动->建立连接（使用Hikari连接池）->执行操作(开启事物->批量操作->关闭事物)->释放连接

## 选做作业

### maven/spring的profile机制,都有什么用法

spring的profile机制，可以对不同的环境做不同的配置。 然后只需要为不同的环境打包不同的配置即可。
