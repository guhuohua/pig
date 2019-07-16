package com.ch.entity;

import java.util.Date;

public class SpikeGoods {
    private Integer id;

    private Integer goodsId;

    private Integer skuId;

    private Integer shopId;

    private Date beginDate;

    private Date endDate;

    private Long spikePrice;

    private Integer spikeNum;

    private Integer maxNum;

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(Long spikePrice) {
        this.spikePrice = spikePrice;
    }

    public Integer getSpikeNum() {
        return spikeNum;
    }

    public void setSpikeNum(Integer spikeNum) {
        this.spikeNum = spikeNum;
    }
}