package top.tangtian.geek_week0802.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tangtian.geek_week0802.entity.OrderTest;
import top.tangtian.geek_week0802.mapper.MasterOrderMapper;
import top.tangtian.geek_week0802.service.OrderService;

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

    @Override
    public int insert(OrderTest orderTest) {
        return orderMapper.insert(orderTest);
    }

    @Override
    public List<OrderTest> getAll() {
        return orderMapper.selectList(new Wrapper<OrderTest>() {
            @Override
            public OrderTest getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });
    }

    @Override
    public int delete(int id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public OrderTest update(OrderTest orderTest) {
        orderMapper.update(orderTest, new Wrapper<OrderTest>() {
            @Override
            public OrderTest getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });
        return orderMapper.selectById(orderTest.getId());
    }
}
