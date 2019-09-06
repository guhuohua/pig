package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class UserUseRedPacketParam {

    private List<Integer> goodsIds;

    private Integer redPacketId;

    private Long orderPrice;

}
