package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class ShopPersonMange {

    private Integer userId;

    private String name;

    private String phone;

    private String roleName;

    private Integer status;

    private Date createDate;
}
