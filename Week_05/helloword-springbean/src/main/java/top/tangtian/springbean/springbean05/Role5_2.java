package top.tangtian.springbean.springbean05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className Role5_2
 * @description
 * @date 2020/11/16 8:42 AM
 **/
@Component
public class Role5_2 {
    private static final Logger LOG = LoggerFactory.getLogger(Role5_2.class);
    private User5 user5;
    @Autowired
    public Role5_2(User5 user5){
        this.user5 = user5;
    }
}
