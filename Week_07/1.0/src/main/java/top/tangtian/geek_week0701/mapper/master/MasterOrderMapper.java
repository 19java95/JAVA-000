package top.tangtian.geek_week0701.mapper.master;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangtian.geek_week0701.entity.master.OrderNo;

import java.util.List;


/**
 * @author tangtian
 * @version 1.0
 * @className MasterOrderMapper
 * @description MasterOrderMapper 继承 BaseMapper
 * @date 2020/12/1 7:13 AM
 **/
@Repository
public interface MasterOrderMapper extends BaseMapper<OrderNo> {
    /**
     * 自定义查询
     * 条件构造器
     */
    List<OrderNo> selectAll(@Param(Constants.WRAPPER) Wrapper<OrderNo> wrappers);
}
