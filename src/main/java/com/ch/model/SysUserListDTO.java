package com.ch.model;

import lombok.Data;

@Data
public class SysUserListDTO {

    private Integer id;

    private String userHead;

    private String nickname;

    private String tel;

    private Integer gender;

    private Long orderPrice;

    private Long orderCount;
}
