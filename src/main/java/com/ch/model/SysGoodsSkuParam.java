package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class SysGoodsSkuParam {

    private Integer id;

    private String title;

    private Integer sort;

    private List<Integer> categoryIds;

    private List<SysGoodsSkuValueParam> paramList;
}
