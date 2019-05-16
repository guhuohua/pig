package com.ch.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SysEvaluateDTO {

    private Integer id;

    private String orderId;

    private Integer score;

    private String name;

    private Date createTime;

    private Integer status;

    private String content;
}
