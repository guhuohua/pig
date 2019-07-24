package com.ch.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SysSpikeSkuModel {

    private Integer id;

    private BigDecimal spikePrice;

    private Integer spikeNum;

    private Integer spikeGoods;
}
