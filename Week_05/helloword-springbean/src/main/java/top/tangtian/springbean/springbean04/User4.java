package top.tangtian.springbean.springbean04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className User
 * @description
 * @date 2020/11/16 7:08 AM
 **/
@Component
public class User4 {
    private static final Logger LOG = LoggerFactory.getLogger(User4.class);
    private String name = "张三";
    private int id = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
