package com.ch.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysShopInfoParam {

    private Integer id;

    private Integer shopAccountId;

    private String title;

    private String address;

    private String shareTitle;

    private String tel;

    private String serviceTime;

    private String logo;

    private String shareImage;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private List<SysGoodAvdModel> sysGoodAvdModels;
}
