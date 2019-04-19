package com.ch.dto;

import lombok.Data;

@Data
public class SysDeliveryInfoDTO {

    private Integer goodsId;

    private String goodsName;

    private String skuCode;

    private String skuName;

    private Long price;

    private Integer num;

    private String pic;

}
