package top.tangtian.demo.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tangtian
 * @version 1.0
 * @className School
 * @description
 * @date 2020/11/17 7:45 PM
 **/
@Component
public class School implements ISchool {
    private static final Logger LOG = LoggerFactory.getLogger(School.class);
    // Resource
    @Autowired //primary
    Klass class1;

    @Autowired
    Student student100;

    @Override
    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }
}
