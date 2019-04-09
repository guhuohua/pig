package com.ch.entity;

public class GoodsArea {
    private Integer id;

    private Integer goodsId;

    private Integer shopId;

    private String goodsClassification;

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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getGoodsClassification() {
        return goodsClassification;
    }

    public void setGoodsClassification(String goodsClassification) {
        this.goodsClassification = goodsClassification == null ? null : goodsClassification.trim();
    }
}