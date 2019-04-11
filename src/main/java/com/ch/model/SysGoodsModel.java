package com.ch.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SysGoodsModel {
    private Integer id;

    private Integer shopId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private String title;

    private String desc;

    private Integer recommend;

    private Integer sale;

    private Integer sort;

    private Integer type;

    private Integer inventory;

    private BigDecimal originalPrice;

    private BigDecimal presentPrice;

    private String sn;

    private Integer memberDiscount;

    private Integer limitBuy;

    private Integer salesVolume;

    private String name;

    private Integer catrgoryId;

    private String keyWords;

    private Long freight;

    private String units;

    private List<SysGoodsImageModel> goodsImageModelList;

    private List<SysGoodsSkuModel> sysGoodsSkuModelList;
}
