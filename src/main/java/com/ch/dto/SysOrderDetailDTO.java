package com.ch.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysOrderDetailDTO {


    private String orderId;

    private Integer orderStatus;

    private String userName;

    private String deliveryName;

    private String phone;

    private String address;

    private List<SysDeliveryInfoDTO> infoDTOS;

    private Long total;

    private String payName;

    private Date payDate;

    private Date refundDate;

    private String refundReason;

    private String photo;

    private Long refundPrice;

    private Long realPrice;

    private Integer refundNumber;

    private String refundId;

}
