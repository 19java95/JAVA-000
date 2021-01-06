package top.tangtian.redis.service;

import io.lettuce.core.api.StatefulRedisConnection;
import top.tangtian.redis.RedisDistributedLock;

/**
 * @author tangtian
 * @version 1.0
 * @className InventoryService
 * @description
 * @date 2020/12/31 7:23 AM
 **/
public interface InventoryService {
    /**
     * 扣减库存
     * @return
     */
    boolean decrease(RedisDistributedLock redisDistributedLock, StatefulRedisConnection<String, String> connection);
}
