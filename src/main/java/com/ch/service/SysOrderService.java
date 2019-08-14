package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysOrderParam;
import com.ch.entity.Express;

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

    /**
     * 7天自动收货
     * @param oderId
     * @return
     */
    ResponseResult deliver(String oderId);

    /**
     * 快递公司列表
     * @return
     */
    ResponseResult expressList();

    /**
     * 物流跟踪
     * @param orderId
     * @return
     */
    ResponseResult expressTracking(String orderId);
}
