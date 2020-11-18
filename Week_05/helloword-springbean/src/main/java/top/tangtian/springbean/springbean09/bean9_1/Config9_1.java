package top.tangtian.springbean.springbean09.bean9_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangtian
 * @version 1.0
 * @className Config
 * @description
 * @date 2020/11/17 7:06 AM
 **/
@Configuration
public class Config9_1 {
    private static final Logger LOG = LoggerFactory.getLogger(Config9_1.class);
    @Bean
    public User9_1 user9_1(){
        return new User9_1("9_1","zhangsan9_1");
    }
}
