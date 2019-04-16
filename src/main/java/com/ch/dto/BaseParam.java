package com.ch.dto;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class BaseParam extends PageQuery {

    private String name;

    private String phone;
}
