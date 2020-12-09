package top.tangtian.geek_week0802.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.tangtian.geek_week0802.entity.OrderTest;

import java.util.List;

/**
 * @author tangtian
 * @version 1.0
 * @className MasterOrderMapper
 * @description MasterOrderMapper 继承 BaseMapper
 * @date 2020/12/1 7:13 AM
 **/
@Mapper
public interface MasterOrderMapper extends BaseMapper<OrderTest> {
    /**
     * 自定义查询
     * 条件构造器
     */
    List<OrderTest> selectAll(@Param(Constants.WRAPPER) Wrapper<OrderTest> wrappers);
}
