package com.ch.dto;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class SysOrderParam extends PageQuery {

    private String id;

    private String name;

    private Integer orderStatus;

    private Long beginDate;

    private Long endDate;
}
