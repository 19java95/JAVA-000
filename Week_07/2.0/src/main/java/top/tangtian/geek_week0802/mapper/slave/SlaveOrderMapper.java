package top.tangtian.geek_week0802.mapper.slave;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.tangtian.geek_week0802.entity.master.OrderNo;

import java.util.List;

@Repository
public interface SlaveOrderMapper extends BaseMapper<OrderNo> {
    List<OrderNo> selectAll();

}
