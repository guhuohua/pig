package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodsSkuModel {

    private Integer id;

    private Integer shopId;

    private Integer status;

    private String attrs;

    private Long originalPrice;

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
}
