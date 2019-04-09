/**
 * Author: 常富文
 * Date:   2019/4/8 11:17
 * Description: 商品列表的查询条件
 */


package com.ch.dto;

import java.util.Date;
import java.util.List;

public class GoodsDto {
    private Integer id;

    private Integer shopId;

    private Integer status;
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

    private Integer start;

    private Integer rows;
    private List<String> goodsSalesArea;

    public List<String> getGoodsSalesArea() {
        return goodsSalesArea;
    }

    public void setGoodsSalesArea(List<String> goodsSalesArea) {
        this.goodsSalesArea = goodsSalesArea;
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
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        this.sn = sn;
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
        this.name = name;
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
        this.keyWords = keyWords;
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
        this.units = units;
    }



    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
