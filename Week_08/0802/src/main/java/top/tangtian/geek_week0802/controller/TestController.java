package top.tangtian.geek_week0802.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangtian.geek_week0802.entity.OrderTest;
import top.tangtian.geek_week0802.service.OrderService;

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
    private OrderService orderService;

    @GetMapping("/transactionException")
    public Object listAll() {
        return orderService.shardingTransactionError();
    }

    @GetMapping("/transaction")
    public Object newOrder(@RequestBody OrderTest orderTest){
        return orderService.shardingTransactionCurrent();
    }
}
