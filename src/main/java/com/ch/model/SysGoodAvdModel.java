package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodAvdModel {

    private Integer id;

    private String pictureUrl;

    private Integer sortOrder;

    private Integer shopId;

    private Date createTime;

    private Date updateTime;

    private Integer goodsId;
}
