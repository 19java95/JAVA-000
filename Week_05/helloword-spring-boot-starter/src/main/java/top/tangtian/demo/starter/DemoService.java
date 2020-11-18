package top.tangtian.demo.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author tangtian
 * @version 1.0
 * @className Demo
 * @description
 * @date 2020/11/17 7:58 PM
 **/
@Component
public class DemoService {
    private static final Logger LOG = LoggerFactory.getLogger(DemoService.class);
    @Autowired
    Student student;
    @Autowired
    Klass klass;
    @Autowired
    School school;
    public void demoService(){
        LOG.info("在配置文件中配置的学生Id为:{},名字为:{}",student.getId(),student.getName());
        klass.setStudents(Arrays.asList(student));
        klass.getStudents().stream().forEach(e-> {
            LOG.info("klass名字为:{}",e.getName());
        });
        LOG.info("这个班的学生人数:{}",school.class1.getStudents().size());
        System.out.println();
    }

}
