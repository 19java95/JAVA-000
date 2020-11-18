package top.tangtian.demo.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className Klass
 * @description
 * @date 2020/11/17 7:47 PM
 **/
@Component
public class Klass {
    private static final Logger LOG = LoggerFactory.getLogger(Klass.class);
    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
