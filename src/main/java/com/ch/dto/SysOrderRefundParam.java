package com.ch.dto;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class SysOrderRefundParam extends PageQuery {


    private String refundId;

    private String name;

    private Integer refundStatus;

}
