package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodsListModel {

    private Integer goodsId;

    private Integer status;

    private Integer recommend;

    private Integer sale;

    private Date createTime;

    private String title;

    private String desc;

    private Integer type;

    private Integer inventory;

    private Double originalPrice;

    private Double presentPrice;

    private Integer salesVolume;

    private Integer catrgoryId;

    private String imgUrl;
}
