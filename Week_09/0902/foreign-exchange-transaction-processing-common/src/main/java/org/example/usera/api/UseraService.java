package org.example.usera.api;

import org.dromara.hmily.annotation.Hmily;
import org.example.usera.entity.Doller;
import org.example.usera.entity.Rmb;

/**
 * @author tangtian
 * @version 1.0
 * @className UseraService
 * @description
 * @date 2020/12/18 7:28 AM
 **/
public interface UseraService {
    /**
     * 新增钱
     * @param rmb
     * @return
     */
    @Hmily
    boolean insertAssest(Rmb rmb, Doller doller);

    /**
     * 扣件钱
     * @param doller
     * @return
     */
    @Hmily
    Integer decreaseAssest(Rmb rmb, Doller doller);

}
