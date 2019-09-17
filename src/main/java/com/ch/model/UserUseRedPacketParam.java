package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class UserUseRedPacketParam {

    private List<Integer> skuIds;

    private Integer redPacketId;

    private Long orderPrice;

}
