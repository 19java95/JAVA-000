package top.tangtian.springbean.springbean01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangtian
 * @version 1.0
 * @className IocCnfig
 * @description
 * @Configuration注解表示这是一个配置类，@ComponentScan注解表示配置类会扫描该配置类所在的包以及所有子包，寻找所有带@Component注解的类。
 * @ComponentScan后面并没有配置属性，就表示扫描配置类所在的包以及子包，还可以手动配置从哪个包开始扫描
 * @date 2020/11/16 7:13 AM
 **/
@Configuration
@ComponentScan
public class Config1 {
    private static final Logger LOG = LoggerFactory.getLogger(Config1.class);
}
