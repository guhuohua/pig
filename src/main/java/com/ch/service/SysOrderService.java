package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysOrderParam;

public interface SysOrderService {

    /**
     * 订单列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult list(SysOrderParam param, Integer userId);

    /**
     * 发货
     * @param param
     * @param userId
     * @return
     */
    ResponseResult deliverGoods(DeliverGoodsParam param, Integer userId);

}
