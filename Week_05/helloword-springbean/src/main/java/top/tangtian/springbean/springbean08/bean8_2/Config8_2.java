package top.tangtian.springbean.springbean08.bean8_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangtian
 * @version 1.0
 * @className Config
 * @description
 * @date 2020/11/16 7:38 PM
 **/
@Configuration
public class Config8_2 {
    private static final Logger LOG = LoggerFactory.getLogger(Config8_2.class);
    @Bean
    public User8_2 user9_1(){
        return new User8_2("9_2","张三09");
    }
}
