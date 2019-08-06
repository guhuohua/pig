/**
 * Author: 常富文
 * Date:   2019/4/18 17:50
 * Description: 购物车
 */


package com.ch.dto;

import com.ch.entity.GoodsSku;
import com.ch.entity.SpikeGoods;

public class CarDto {
    GoodsSku goodsSku;
    Integer num;
    String name;
    Integer flag;
    SpikeGoods spikeGoods;

    public SpikeGoods getSpikeGoods() {
        return spikeGoods;
    }

    public void setSpikeGoods(SpikeGoods spikeGoods) {
        this.spikeGoods = spikeGoods;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

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
