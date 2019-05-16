package com.ch.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String avatar;

    private Integer shopId;

    private Date createTime;

    private Date updateTime;

    private String lockFlag;

    private String delFlag;

    private String wxOpenid;

    private String qqOpenid;

    private Integer status;

    private String roleName;

}