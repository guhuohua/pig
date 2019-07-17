package com.ch.model;

import lombok.Data;

@Data
public class ViewSpikeGoodsDTO {

    private Integer goodsId;

    private String title;

    private String img;

    private Long beginDate;

    private Long endDate;

    private Long originalPrice;

    private Long spikePrice;

}
