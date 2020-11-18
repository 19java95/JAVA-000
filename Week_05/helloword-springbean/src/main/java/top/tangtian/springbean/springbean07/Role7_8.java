package top.tangtian.springbean.springbean07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className Role7_1
 * @description
 * @date 2020/11/18 3:57 PM
 **/
public class Role7_8 {
    private static final Logger LOG = LoggerFactory.getLogger(Role7_8.class);
    private User7_2 user7_2;
    private String name;
    private List<String> titles;

    public Role7_8(User7_2 user7_2){
        this.user7_2 = user7_2;
    }
    public Role7_8(){

    }

    public User7_2 getUser7_2() {
        return user7_2;
    }

    public void setUser7_2(User7_2 user7_2) {
        this.user7_2 = user7_2;
    }

    public void say(){
        System.out.println("包含用户:" + user7_2.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
