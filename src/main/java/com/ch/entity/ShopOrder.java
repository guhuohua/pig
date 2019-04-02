package com.ch.entity;

import java.util.Date;

public class ShopOrder {
    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private Double freeShipping;

    private Integer orderCloseNormal;

    private Integer orderCloseActive;

    private Integer confirmDay;

    private String templateCancel;

    private String templateDelivery;

    private String templateEvaluation;

    private String templateComplete;

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

    public Double getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Double freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Integer getOrderCloseNormal() {
        return orderCloseNormal;
    }

    public void setOrderCloseNormal(Integer orderCloseNormal) {
        this.orderCloseNormal = orderCloseNormal;
    }

    public Integer getOrderCloseActive() {
        return orderCloseActive;
    }

    public void setOrderCloseActive(Integer orderCloseActive) {
        this.orderCloseActive = orderCloseActive;
    }

    public Integer getConfirmDay() {
        return confirmDay;
    }

    public void setConfirmDay(Integer confirmDay) {
        this.confirmDay = confirmDay;
    }

    public String getTemplateCancel() {
        return templateCancel;
    }

    public void setTemplateCancel(String templateCancel) {
        this.templateCancel = templateCancel == null ? null : templateCancel.trim();
    }

    public String getTemplateDelivery() {
        return templateDelivery;
    }

    public void setTemplateDelivery(String templateDelivery) {
        this.templateDelivery = templateDelivery == null ? null : templateDelivery.trim();
    }

    public String getTemplateEvaluation() {
        return templateEvaluation;
    }

    public void setTemplateEvaluation(String templateEvaluation) {
        this.templateEvaluation = templateEvaluation == null ? null : templateEvaluation.trim();
    }

    public String getTemplateComplete() {
        return templateComplete;
    }

    public void setTemplateComplete(String templateComplete) {
        this.templateComplete = templateComplete == null ? null : templateComplete.trim();
    }
}