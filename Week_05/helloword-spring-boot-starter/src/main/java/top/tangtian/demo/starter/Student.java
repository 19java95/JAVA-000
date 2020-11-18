package top.tangtian.demo.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className Student
 * @description
 * @date 2020/11/17 7:45 PM
 **/
@ConfigurationProperties(prefix = "top.tangtian")
public class Student {
    private static final Logger LOG = LoggerFactory.getLogger(Student.class);
    private String id;
    private String name;

    public void init(){
        System.out.println("hello...........");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
