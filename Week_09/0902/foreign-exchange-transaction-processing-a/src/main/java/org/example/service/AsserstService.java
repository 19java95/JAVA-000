package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author tangtian
 * @version 1.0
 * @className AsserstService
 * @description
 * @date 2020/12/18 8:14 AM
 **/
public interface AsserstService {
    boolean ChangeDoller(BigDecimal rmbCount);
}
