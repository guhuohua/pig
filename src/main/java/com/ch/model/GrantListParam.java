package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class GrantListParam extends PageQuery {

    private String name;

    private Integer status;
}
