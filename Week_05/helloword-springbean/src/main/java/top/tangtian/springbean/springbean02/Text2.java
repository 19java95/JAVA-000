package top.tangtian.springbean.springbean02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tangtian
 * @version 1.0
 * @className Text1
 * @description
 * @date 2020/11/16 7:16 AM
 **/
public class Text2 {
    private static final Logger LOG = LoggerFactory.getLogger(Text2.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext annotationConfigApplicationContext = new
                ClassPathXmlApplicationContext("springbean02.xml");
        User2 user1 = annotationConfigApplicationContext.getBean(User2.class);
        LOG.info("获取到bean:{}",user1.getName());
    }
}
