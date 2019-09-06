package com.ch.dto;

import lombok.Data;

@Data
public class RedPacketListDTO {
    private Integer id;

    private String name;

    private Long faceValue;

    private String expiryDate;

    private String redPacketType;

    private Long stock;

    private Long receiveCount;

    private Long usedCount;

    private Integer status;

}
