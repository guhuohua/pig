/**
 * Author: 常富文
 * Date:   2019/4/16 17:20
 * Description: 订单
 */


package com.ch.dto;

import com.ch.entity.GoodsOrder;
import com.ch.entity.GoodsSku;
import com.ch.entity.UserAddress;

public class OrderDto {
    private GoodsSku goodsSku;

    private Integer num;

    private UserAddress userAddress;

    private GoodsOrder order;


    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
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

    public GoodsOrder getOrder() {
        return order;
    }

    public void setOrder(GoodsOrder order) {
        this.order = order;
    }
}
