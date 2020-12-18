package org.example.usera.mapper;

import org.apache.ibatis.annotations.Update;
import org.example.usera.entity.AssetLog;

import java.math.BigDecimal;

/**
 * @author tangtian
 * @version 1.0
 * @className UseraMapper
 * @description
 * @date 2020/12/18 7:38 AM
 **/
public interface UseraMapper {
    @Update("UPDATE `doller` SET `asset` = `asset` + #{count} WHERE (`id` = #{id}); ")
    int updateDoller(BigDecimal count, Integer id);

    @Update("UPDATE `rmb` SET `asset` = `asset` + #{count} WHERE (`id` = #{id}); ")
    int updateRmb(BigDecimal count, Integer id);

    @Update("INSERT INTO `asset_log` ( `doller_asset`, `rmb_asset`, `status`, `create_time`,`update_time`)" +
            " VALUES (#{dollerAsset}, #{rmbAsset}, #{status}, #{createTime} , #{update_time}); ")
    int insertAssertLog(AssetLog assetLog);

    @Update("UPDATE `asset_log` SET `status` = #{status} WHERE (`id` = #{id}); ")
    int updateAssertLogStatus(Integer status, Integer id);
}
