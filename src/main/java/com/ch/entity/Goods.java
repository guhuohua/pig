package com.ch.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private String title;

    private String desc;

    private Integer recommend;

    private Integer sale;

    private Integer sort;

    private Integer type;

    private Integer inventory;

    private Long originalPrice;

    private Long presentPrice;

    private String sn;

    private Integer memberDiscount;

    private Integer limitBuy;

    private Integer salesVolume;

    private String name;

    private Integer catrgoryId;

    private String keyWords;

    private Long freight;

    private String units;

    private String goodsImgUrl;

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(Long presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(Integer memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public Integer getLimitBuy() {
        return limitBuy;
    }

    public void setLimitBuy(Integer limitBuy) {
        this.limitBuy = limitBuy;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCatrgoryId() {
        return catrgoryId;
    }

    public void setCatrgoryId(Integer catrgoryId) {
        this.catrgoryId = catrgoryId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public Long getFreight() {
        return freight;
    }

    public void setFreight(Long freight) {
        this.freight = freight;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }
}