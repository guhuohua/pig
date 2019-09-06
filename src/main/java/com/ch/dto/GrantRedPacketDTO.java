package com.ch.dto;

import lombok.Data;

@Data
public class GrantRedPacketDTO {

    private Integer id;

    private Long faceValue;

    private String redPacketType;

    private Integer status;

    private String userName;

    private Long receiveDate;

    private Long useDate;

}
