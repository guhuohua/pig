package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class SysRecommendParam extends PageQuery {

    private String title;

    private String recommend;

    private Integer status;
}
