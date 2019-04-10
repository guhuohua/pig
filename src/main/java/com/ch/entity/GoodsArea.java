package com.ch.entity;

public class GoodsArea {
    private Integer id;

    private Integer goodsId;

    private Integer shopId;

    private String goodsClassification;

    private Integer status;

    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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