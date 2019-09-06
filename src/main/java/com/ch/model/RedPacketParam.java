package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class RedPacketParam extends PageQuery {

    private String name;

    private Long useBeginDate;

    private Long useEndDate;

    private Integer status;

    private String redPacketType;
}
