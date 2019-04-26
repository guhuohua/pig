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


    /**
     * 订单详情
     * @param oderId
     * @param userId
     * @return
     */
    ResponseResult detail(String oderId, Integer userId);

    /**
     * 超时未支付，取消订单
     * @param oderId
     * @return
     */
    ResponseResult cancelOrder(String oderId);
}
