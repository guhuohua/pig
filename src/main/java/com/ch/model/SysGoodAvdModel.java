package com.ch.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysGoodAvdModel {

    private Integer id;

    private String title;

    private String path;

    private String pictureUrl;

    private Integer status;

    private Integer sortOrder;

    private Integer shopId;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private Integer browseNumber;
}
