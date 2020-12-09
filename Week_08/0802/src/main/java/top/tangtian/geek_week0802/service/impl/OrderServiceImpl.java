package top.tangtian.geek_week0802.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangtian.geek_week0802.entity.OrderTest;
import top.tangtian.geek_week0802.mapper.MasterOrderMapper;
import top.tangtian.geek_week0802.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className OrderServiceImpl
 * @description
 * @date 2020/12/8 12:03 AM
 **/
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private MasterOrderMapper orderMapper;

    /**
     * 根据规则 分别给两个数据库增加记录 其中 userid为 偶数在 test0数据库
     * userid 为基础在test1数据库
     * @return
     */
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean shardingTransactionError() {
        orderMapper.insert(buildEvenOrderByUserId());
        orderMapper.insert(buildOddOrderByUserId());
        throw new RuntimeException("分库插入异常");
    }

    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean shardingTransactionCurrent() {
        orderMapper.insert(buildEvenOrderByUserId());
        orderMapper.insert(buildOddOrderByUserId());
        return true;
    }

    private OrderTest buildOddOrderByUserId(){
        OrderTest orderTest = new OrderTest();
        orderTest.setId(20);
        orderTest.setUserId(15);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        orderTest.setCloseTime(LocalDateTime.now().format(dtf2));
        orderTest.setPayment(new BigDecimal(100));
        orderTest.setPostage(20);
        orderTest.setCreateTime(LocalDateTime.now().format(dtf2));
        return orderTest;
    }

    private OrderTest buildEvenOrderByUserId(){
        OrderTest orderTest = new OrderTest();
        orderTest.setId(22);
        orderTest.setUserId(16);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        orderTest.setCloseTime(LocalDateTime.now().format(dtf2));
        orderTest.setPayment(new BigDecimal(100));
        orderTest.setPostage(20);
        orderTest.setCreateTime(LocalDateTime.now().format(dtf2));
        return orderTest;
    }

}
