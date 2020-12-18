package org.example.service.impl;

import org.example.service.AsserstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author tangtian
 * @version 1.0
 * @className AssertServiceImpl
 * @description
 * @date 2020/12/18 8:14 AM
 **/
public class AssertServiceImpl implements AsserstService {
    private static final Logger LOG = LoggerFactory.getLogger(AssertServiceImpl.class);

    @Override
    public boolean ChangeDoller(BigDecimal rmbCount) {
        return false;
    }
}
