package top.tangtian.geek_week0702.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangtian.geek_week0702.entity.master.OrderNo;
import top.tangtian.geek_week0702.mapper.master.MasterOrderMapper;
import top.tangtian.geek_week0702.mapper.slave.SlaveOrderMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangtian
 * @version 1.0
 * @className TestController
 * @description
 * @date 2020/12/1 7:29 AM
 **/
@RestController
@RequestMapping("/order")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private MasterOrderMapper masterOrderMapper;
    @Autowired
    private SlaveOrderMapper slaveOrderMapper;

    /**
     * 查询
     */
    @GetMapping("/find")
    public Object find(int id) {
        OrderNo testUser = slaveOrderMapper.selectOne(new QueryWrapper<OrderNo>().eq("id" , id));
        if (testUser != null) {
            return (testUser);
        } else {
            return ("没有找到该对象");
        }
    }

    /**
     * 查询全部
     */
    @GetMapping("/listall")
    public Object listAll() {
        //自定义接口查询
        QueryWrapper<OrderNo> queryWrapper = new QueryWrapper<>();
        List<OrderNo> resultData = masterOrderMapper.selectAll(queryWrapper.eq("id",1));
        //mp内置接口
        List<OrderNo> resultDataSlave = slaveOrderMapper.selectAll();
        int initSize = 30;
        Map<String, Object> result = new HashMap<>(initSize);
        result.put("master" , resultData);
        result.put("slave" , resultDataSlave);

        return (result);
    }
}
