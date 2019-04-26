package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.OrderRefund;

public interface ViewOrderRefundService {
    ResponseResult addOrderRefund(OrderRefund orderRefund,String openId,Integer shopId);
}
