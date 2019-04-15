package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class SysGoodsSkuId {

    private Integer specificationId;

    private List<Integer> specificationAttrId;
}
