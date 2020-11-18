package top.tangtian.springbean.springbean05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className Role5_1
 * @description
 * @date 2020/11/16 8:40 AM
 **/
@Component
public class Role5_1 {
    private static final Logger LOG = LoggerFactory.getLogger(Role5_1.class);
    @Autowired(required = false)
    private User5 user5;
}
