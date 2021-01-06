package top.tangtian.redis.service.impl;


import io.lettuce.core.api.StatefulRedisConnection;
import top.tangtian.redis.service.InventoryService;
import top.tangtian.redis.RedisDistributedLock;

import java.util.UUID;

/**
 * @author tangtian
 * @version 1.0
 * @className InventoryServiceImpl
 * @description
 * @date 2020/12/31 7:24 AM
 **/
public class InventoryServiceImpl implements InventoryService {
    private Long n = 1000L;
    @Override
    public boolean decrease(RedisDistributedLock redisDistributedLock, StatefulRedisConnection<String, String> connection) {
        String value = UUID.randomUUID().toString();
        if(redisDistributedLock.lockWithTimeout(connection,value,1000L)){
            n--;
            System.out.println(Thread.currentThread().getName() + "--减库存到:::" + n);
            redisDistributedLock.releaseLockWithLua(connection,value);
            return true;
        }
        System.out.println(Thread.currentThread().getName() + "---没有执行减少库存操作");
       return false;
    }
}
