package top.tangtian.springbean.springbean01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author tangtian
 * @version 1.0
 * @className Text1
 * @description
 * @date 2020/11/16 7:16 AM
 **/
public class Text1 {
    private static final Logger LOG = LoggerFactory.getLogger(Text1.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new
                AnnotationConfigApplicationContext(Config1.class);
        User1 user1 = annotationConfigApplicationContext.getBean(User1.class);
        LOG.info("获取到bean:{}",user1.getName());
    }
}
