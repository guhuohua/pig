/**
 * Author: 常富文
 * Date:   2019/4/9 11:59
 * Description: 搜索条件
 */


package com.ch.dto;

public class SolrDto {
    private Integer start;

    private Integer rows;

    private String condition;

    private Integer categoryId;

    private String goodsType;

    private Integer salesVolume;

    private Long presentPrice;

    private Integer status;

    private Long createTime;

    private Integer evaluationNum;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getEvaluationNum() {
        return evaluationNum;
    }

    public void setEvaluationNum(Integer evaluationNum) {
        this.evaluationNum = evaluationNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Long getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(Long presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
