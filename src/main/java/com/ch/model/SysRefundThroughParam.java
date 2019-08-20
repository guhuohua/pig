package com.ch.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SysRefundThroughParam {

    private String refundId;

    private BigDecimal price;

    private String refuse;

    private Integer refundStatus;

    private String orderId;
}
