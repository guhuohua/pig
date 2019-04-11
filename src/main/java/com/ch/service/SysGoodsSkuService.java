package com.ch.service;

import com.ch.base.ResponseResult;

public interface SysGoodsSkuService {

    /**
     * 商品规格分页查询
     * @param name
     * @param userId
     * @return
     */
    ResponseResult list(String name, Integer userId, Integer currentPage, Integer pageSize);
}
