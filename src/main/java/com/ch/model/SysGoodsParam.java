package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class SysGoodsParam extends PageQuery {

    private String sn;

    private String name;

    private Integer sale;

    private String goodsType;
}
