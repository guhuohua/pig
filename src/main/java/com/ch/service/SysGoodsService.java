package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.SysGoodsParam;

public interface SysGoodsService {

    /**
     * 后台商品列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult goodsList(SysGoodsParam param, Integer userId);
}
