package com.ch.model;

import com.ch.dto.GoodsSkuListDTO;
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

    private String sn;

    private Integer memberDiscount;

    private Integer limitBuy;

    private Integer salesVolume;

    private String name;

    private List<Integer> categoryIds;

    private String keyWords;

    private BigDecimal freight;

    private String units;

    private String goodsImgUrl;

    private Integer integralGoods;

    private String goodsType;

    private Long beginDate;

    private Long endDate;

    private Integer maxNum;

    private List<SysGoodsImageModel> goodsImgList;

    private List<SysGoodsSkuModel> sysGoodsSkuModelList;

    private List<GoodsSkuListDTO> goodsSkuListDTOList;
}
