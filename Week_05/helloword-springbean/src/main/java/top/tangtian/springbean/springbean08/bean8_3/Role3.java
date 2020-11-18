package top.tangtian.springbean.springbean08.bean8_3;

import io.tangtian.springbean.springbean08.bean8_1.User8_1;
import io.tangtian.springbean.springbean08.bean8_2.User8_2;
import io.tangtian.springbean.springbean08.bean8_4.User8_4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className Role3
 * @description
 * @date 2020/11/16 7:47 PM
 **/
public class Role3 {
    private static final Logger LOG = LoggerFactory.getLogger(Role3.class);
    private User8_1 user8_1;
    private User8_2 user8_2;
    private User8_4 user8_4;

    public User8_1 getUser8_1() {
        return user8_1;
    }

    public void setUser8_1(User8_1 user8_1) {
        this.user8_1 = user8_1;
    }

    public User8_2 getUser8_2() {
        return user8_2;
    }

    public void setUser8_2(User8_2 user8_2) {
        this.user8_2 = user8_2;
    }

    public User8_4 getUser8_4() {
        return user8_4;
    }

    public void setUser8_4(User8_4 user8_4) {
        this.user8_4 = user8_4;
    }
}
