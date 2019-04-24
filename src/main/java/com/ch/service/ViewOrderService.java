package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.OrderDto;

import javax.print.DocFlavor;
import java.util.List;

public interface ViewOrderService {
    ResponseResult addOrder(OrderDto[] orderDtoList ,String openId,Integer shopId);
    ResponseResult showOrder(String orderId, String openId);
    ResponseResult updateOrder(OrderDto orderDto);
    ResponseResult deleOrder(String orderId);
    ResponseResult manageOrder(Integer status,String openId,Integer shopId );
    ResponseResult findAll(String openId,Integer shopId);
}
