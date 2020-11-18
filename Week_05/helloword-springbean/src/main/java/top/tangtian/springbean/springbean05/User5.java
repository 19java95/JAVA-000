package top.tangtian.springbean.springbean05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className User5
 * @description
 * @date 2020/11/16 8:37 AM
 **/
@Component("user5")
public class User5 {
    private static final Logger LOG = LoggerFactory.getLogger(User5.class);
    private int id = 5;
    private String name = "张三5";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
