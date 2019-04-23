package com.ch.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SysOrderRefundDTO {

    private String refundId;

    private String orderId;

    private String name;

    private Long refundPrice;

    private Long realPrice;

    private Date payDate;

    private String logisticsNumber;

    private Date refundDate;

    private Integer refundStatus;
}
