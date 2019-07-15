package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysSpikeGoodsModel {

    private Integer id;

    private Integer goodsId;

    private Integer skuId;

    private Integer shopId;

    private Long beginDate;

    private Long endDate;

    private Long spikePrice;

    private Integer spikeNum;
}
