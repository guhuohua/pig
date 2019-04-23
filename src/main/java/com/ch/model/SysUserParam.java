package com.ch.model;

import lombok.Data;

@Data
public class SysUserParam {

    private String username;

    private String password;

    private String salt;

    private String phone;

    private Integer status;

    private Integer shopId;

    private Integer userId;

    private String avatar;

}
