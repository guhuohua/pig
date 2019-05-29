package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.OrderRefund;

public interface ViewOrderRefundService {
    ResponseResult addOrderRefund(OrderRefund orderRefund, String openId, Integer shopId);

    ResponseResult showRefundList(Integer status, String openId, Integer shopId);
    ResponseResult  refundCount(String openId, Integer shopId);
}
