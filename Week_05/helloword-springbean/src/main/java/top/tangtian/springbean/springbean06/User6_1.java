package top.tangtian.springbean.springbean06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className User6_1
 * @description
 * @date 2020/11/16 8:47 AM
 **/
public class User6_1 {
    private static final Logger LOG = LoggerFactory.getLogger(User6_1.class);
    private int id = 6;
    private String name = "张三6";

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
