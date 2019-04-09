package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class SysGoodsParam extends PageQuery {

    private String title;

    private String name;

    private Integer recommend;

    private Integer sale;
}
