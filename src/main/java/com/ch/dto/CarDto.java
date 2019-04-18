/**
 * Author: 常富文
 * Date:   2019/4/18 17:50
 * Description: 购物车
 */


package com.ch.dto;

import com.ch.entity.GoodsSku;

public class CarDto {
    GoodsSku goodsSku;
    Integer num;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
