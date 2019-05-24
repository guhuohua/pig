package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.OrderDto;

public interface ViewOrderService {
    ResponseResult addOrder(OrderDto[] orderDtoList, String openId, Integer shopId);

    ResponseResult showOrder(String orderId, String openId,Integer shopId);

    ResponseResult updateOrder(OrderDto orderDto);

    ResponseResult manageOrder(Integer status, String openId, Integer shopId,String condition);

    ResponseResult findAll(String openId, Integer shopId,String condition);

    ResponseResult deleOrderById(String orderId);

    ResponseResult updateStatus(String orderId);

    ResponseResult orderCount(Integer status, String openId, Integer shopId);

    ResponseResult orderAllCount(String openId, Integer shopId);

    ResponseResult orderAddAddress(String orderId,Integer addressId);


}
