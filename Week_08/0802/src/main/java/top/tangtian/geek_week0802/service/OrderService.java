package top.tangtian.geek_week0802.service;

import top.tangtian.geek_week0802.entity.OrderTest;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className OrderService
 * @description
 * @date 2020/12/8 12:01 AM
 **/
public interface OrderService {

    Boolean shardingTransactionCurrent();

    Boolean shardingTransactionError();

}
