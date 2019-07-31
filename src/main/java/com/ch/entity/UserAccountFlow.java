package com.ch.entity;

import java.util.Date;

public class UserAccountFlow {
    private Integer id;

    private Integer userId;

    private Integer shopId;

    private Integer status;

    private Long price;

    private Date createDate;

    private String type;

    private String flowReason;

    private Long formartTime;

    public Long getFormartTime() {
        return formartTime;
    }

    public void setFormartTime(Long formartTime) {
        this.formartTime = formartTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFlowReason() {
        return flowReason;
    }

    public void setFlowReason(String flowReason) {
        this.flowReason = flowReason == null ? null : flowReason.trim();
    }
}