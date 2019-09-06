package com.ch.dto;

import lombok.Data;

@Data
public class MyRedPacketDTO {

    private Integer id;

    private String name;

    private Long faceValue;

    private Long sendBeginDate;

    private Long sendEndDate;

    private String goodsRange;

    private Integer superposition;

    private Long minPrice;

    private Long useBeginDate;

    private Long useEndDate;

    private String redPacketType;

    /**
     * 0：未使用， 1：使用
     */
    private Integer status;

    private String describe;

}
