package top.tangtian.springbean.springbean07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author tangtian
 * @version 1.0
 * @className User7_1
 * @description
 * @date 2020/11/16 9:00 AM
 **/
public class User7_9 {
    private static final Logger LOG = LoggerFactory.getLogger(User7_9.class);
    private int id;
    private String name;
    private Set<String> titles;

    public User7_9(int id, String name, Set<String> titles) {
        this.id = id;
        this.name = name;
        this.titles = titles;
    }

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

    public Set<String> getTitles() {
        return titles;
    }

    public void setTitles(Set<String> titles) {
        this.titles = titles;
    }
}
