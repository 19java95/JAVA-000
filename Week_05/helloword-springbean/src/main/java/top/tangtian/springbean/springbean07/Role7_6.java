package top.tangtian.springbean.springbean07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className Role7_1
 * @description
 * @date 2020/11/18 3:57 PM
 **/
public class Role7_6 {
    private static final Logger LOG = LoggerFactory.getLogger(Role7_6.class);
    private User7_2 user7_2;
    public Role7_6(User7_2 user7_2){
        this.user7_2 = user7_2;
    }
    public Role7_6(){

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
}
