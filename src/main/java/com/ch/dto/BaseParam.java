package com.ch.dto;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class BaseParam extends PageQuery {

    private String name;

    private String phone;

    private Integer status;

    private Integer score;

    private Integer id;

    private String content;

}
