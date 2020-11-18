package top.tangtian.springbean.springbean09.bean3;

import io.tangtian.springbean.springbean09.bean2.User9_2;
import io.tangtian.springbean.springbean09.bean9_1.User9_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className Role9_3
 * @description
 * @date 2020/11/17 7:08 AM
 **/
public class Role9_3 {
    private static final Logger LOG = LoggerFactory.getLogger(Role9_3.class);

    private User9_1 user9_1;
    private User9_2 user9_2;

    public Role9_3(User9_1 user9_1, User9_2 user9_2) {
        this.user9_1 = user9_1;
        this.user9_2 = user9_2;
    }

    public User9_1 getUser9_1() {
        return user9_1;
    }

    public void setUser9_1(User9_1 user9_1) {
        this.user9_1 = user9_1;
    }

    public User9_2 getUser9_2() {
        return user9_2;
    }

    public void setUser9_2(User9_2 user9_2) {
        this.user9_2 = user9_2;
    }
}
