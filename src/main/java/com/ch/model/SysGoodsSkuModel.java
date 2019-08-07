package com.ch.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysGoodsSkuModel {

    private Integer id;

    private Integer shopId;

    private Integer status;

    private String attrs;

    //接受前端传来的金额
    private Long originalPrice;

    //接受前端传来的金额
    private Long presentPrice;

    private Integer inventory;

    private String sn;

    private Integer sale;

    private String goodsImage;

    private Integer goodsId;

    private String skuName;

    private Integer getIntegral;

    private Integer consumptionIntegral;

    private Long spikePrice;

    private Integer spikeNum;

    private Integer spikeGoods;

    private Long beginDate;

    private Long endDate;

    private BigDecimal originalPrice2;

    private BigDecimal presentPrice2;
}
