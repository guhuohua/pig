package com.ch.model;

import lombok.Data;

@Data
public class SysUserListDTO {

    private Integer id;

    private String userHead;

    private String nikename;

    private String tel;

    private Integer gender;

    private Double orderPrice;

    private Double orderCount;
}
