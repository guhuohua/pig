package com.ch.model;

import lombok.Data;

import javax.print.attribute.standard.PrinterURI;

@Data
public class ViewSpikeGoodsDTO {

    private Integer goodsId;

    private Integer skuId;

    private String title;

    private String img;

    private Long beginDate;

    private Long endDate;

    private Long originalPrice;

    private Long spikePrice;

}
