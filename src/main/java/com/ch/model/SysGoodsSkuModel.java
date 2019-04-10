package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodsSkuModel {

    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private String attrs;

    private Long originalPrice;

    private Long presentPrice;

    private Integer inventory;

    private String sn;

    private Integer sale;

    private String goodsImage;
}
