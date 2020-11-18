package top.tangtian.springbean.springbean06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangtian
 * @version 1.0
 * @className Config6
 * @description
 * @date 2020/11/16 8:46 AM
 **/
@Configuration
public class Config6 {
    private static final Logger LOG = LoggerFactory.getLogger(Config6.class);

    @Bean
    public User6_1 user6_1() {
        return new User6_1();
    }

    @Bean(name = "user6_2")
    public User6_2 user6_2() {
        return new User6_2();
    }

    @Bean
    Role6_1 role6_1() {
        return new Role6_1(user6_1());
    }

    @Bean
    Role6_2 role6_2(User6_1 user6_1) {
        return new Role6_2(user6_1);
    }

    @Bean
    Role6_3 role6_3(User6_1 user6_1) {
        Role6_3 role6_3 = new Role6_3();
        role6_3.setUser6_1(user6_1);
        return role6_3;
    }
}
