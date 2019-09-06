package com.ch.entity;

import java.util.Date;

public class RedPacket {
    private Integer id;

    private String name;

    private Long faceValue;

    private Long stock;

    private Date sendBeginDate;

    private Date sendEndDate;

    private Integer goodsRange;

    private Integer superposition;

    private Long minPrice;

    private Date useBeginDate;

    private Date useEndDate;

    private String redPacketType;

    private Long version;

    private Date createDate;

    private Date modifyDate;

    private Integer status;

    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Long faceValue) {
        this.faceValue = faceValue;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Date getSendBeginDate() {
        return sendBeginDate;
    }

    public void setSendBeginDate(Date sendBeginDate) {
        this.sendBeginDate = sendBeginDate;
    }

    public Date getSendEndDate() {
        return sendEndDate;
    }

    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    public Integer getGoodsRange() {
        return goodsRange;
    }

    public void setGoodsRange(Integer goodsRange) {
        this.goodsRange = goodsRange;
    }

    public Integer getSuperposition() {
        return superposition;
    }

    public void setSuperposition(Integer superposition) {
        this.superposition = superposition;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Date getUseBeginDate() {
        return useBeginDate;
    }

    public void setUseBeginDate(Date useBeginDate) {
        this.useBeginDate = useBeginDate;
    }

    public Date getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    public String getRedPacketType() {
        return redPacketType;
    }

    public void setRedPacketType(String redPacketType) {
        this.redPacketType = redPacketType == null ? null : redPacketType.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}