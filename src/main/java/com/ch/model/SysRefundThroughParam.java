package com.ch.model;

import lombok.Data;

@Data
public class SysRefundThroughParam {

    private String refundId;

    private Long price;

    private String refuse;

    private Integer refundStatus;

    private String orderId;
}
