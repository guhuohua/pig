package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodsImageModel {

    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private Integer goodsId;

    private String url;

    private Integer sort;
}
