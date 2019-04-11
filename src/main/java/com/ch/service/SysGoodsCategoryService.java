package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsType;

public interface SysGoodsCategoryService {

    /**
     * 商品类目树
     * @param userId
     * @return
     */
    ResponseResult list(Integer userId);


    /**
     * 管理商品类目
     * @param goodsType
     * @param userId
     * @return
     */
    ResponseResult mange(GoodsType goodsType, Integer userId);

    /**
     * 删除类目
     * @param id
     * @param userId
     * @return
     */
    ResponseResult delete(Integer id, Integer userId);
}
