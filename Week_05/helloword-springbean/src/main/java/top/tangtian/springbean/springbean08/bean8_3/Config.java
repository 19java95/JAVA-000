package top.tangtian.springbean.springbean08.bean8_3;

import io.tangtian.springbean.springbean08.bean8_1.Config8_1;
import io.tangtian.springbean.springbean08.bean8_1.User8_1;
import io.tangtian.springbean.springbean08.bean8_2.Config8_2;
import io.tangtian.springbean.springbean08.bean8_2.User8_2;
import io.tangtian.springbean.springbean08.bean8_4.User8_4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author tangtian
 * @version 1.0
 * @className Config
 * @description
 * @date 2020/11/16 7:47 PM
 **/
@Configuration
@Import({Config8_1.class, Config8_2.class})
@ImportResource("classpath:springbean08.xml")
public class Config {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);
    @Bean
    public Role3 role3(User8_1 user8_1, User8_2 user8_2, User8_4 user8_4){
        Role3 role3 = new Role3();
        role3.setUser8_1(user8_1);
        role3.setUser8_2(user8_2);
        role3.setUser8_4(user8_4);
        return role3;
    }
}
