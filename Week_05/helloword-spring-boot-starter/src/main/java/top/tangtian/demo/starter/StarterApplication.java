package top.tangtian.demo.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangtian
 * @version 1.0
 * @className Config
 * @description
 * @date 2020/11/17 8:03 PM
 **/
@Configuration
@ComponentScan //当前包和子包扫描@Component注解 加载为bean
@EnableConfigurationProperties(top.tangtian.demo.starter.Student.class)//告诉Spring Boot 能支持@ConfigurationProperties。
@ConditionalOnProperty(
        prefix = "top.tangtian",
        name = "isopen",
        havingValue = "true"
)
public class StarterApplication {
    private static final Logger LOG = LoggerFactory.getLogger(StarterApplication.class);
    @Autowired
    private DemoService demoService;

    public DemoService demoService(){
        return new DemoService();
    }
}
/**
 * 引入此项目
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

