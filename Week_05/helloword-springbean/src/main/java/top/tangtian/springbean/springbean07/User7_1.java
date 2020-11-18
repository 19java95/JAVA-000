package top.tangtian.springbean.springbean07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className User7_1
 * @description
 * @date 2020/11/16 9:00 AM
 **/
public class User7_1 {
    private static final Logger LOG = LoggerFactory.getLogger(User7_1.class);
    private int id = 7;
    private String name = "张三71";
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
