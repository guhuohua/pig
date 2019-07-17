package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class SysSpikeGoodsModel {

    private Integer id;

    private Integer goodsId;

    private Long beginDate;

    private Long endDate;

    private List<SysSpikeSkuModel> sysSpikeSkuModels;

    private Integer maxNum;
}
