package top.tangtian.springbean.springbean09.bean9_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className User9_1
 * @description
 * @date 2020/11/17 7:05 AM
 **/
public class User9_1 {
    private static final Logger LOG = LoggerFactory.getLogger(User9_1.class);
    private String id;
    private String name;

    public User9_1(String id, String name) {
        this.id = id;
        this.name = name;
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
