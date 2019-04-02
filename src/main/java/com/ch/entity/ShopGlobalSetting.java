package com.ch.entity;

import java.util.Date;

public class ShopGlobalSetting {
    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private String indexRecommend;

    private String indexCoupon;

    private String shopcarRecommend;

    private String orderRecommend;

    private String searchKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getIndexRecommend() {
        return indexRecommend;
    }

    public void setIndexRecommend(String indexRecommend) {
        this.indexRecommend = indexRecommend == null ? null : indexRecommend.trim();
    }

    public String getIndexCoupon() {
        return indexCoupon;
    }

    public void setIndexCoupon(String indexCoupon) {
        this.indexCoupon = indexCoupon == null ? null : indexCoupon.trim();
    }

    public String getShopcarRecommend() {
        return shopcarRecommend;
    }

    public void setShopcarRecommend(String shopcarRecommend) {
        this.shopcarRecommend = shopcarRecommend == null ? null : shopcarRecommend.trim();
    }

    public String getOrderRecommend() {
        return orderRecommend;
    }

    public void setOrderRecommend(String orderRecommend) {
        this.orderRecommend = orderRecommend == null ? null : orderRecommend.trim();
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }
}