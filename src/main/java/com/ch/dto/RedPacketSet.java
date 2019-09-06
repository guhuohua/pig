package com.ch.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RedPacketSet {

    private Integer superposition;

    private BigDecimal minPrice;

    private Long useBeginDate;

    private Long useEndDate;

    private String describe;
}
