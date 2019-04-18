/**
 * Author: 常富文
 * Date:   2019/4/16 17:20
 * Description: 订单
 */


package com.ch.dto;

import com.ch.entity.Order;
import com.ch.entity.UserAddress;

public class OrderDto {
    private Integer skuId;

    private Integer num;

    private UserAddress userAddress;

    private Order order;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
