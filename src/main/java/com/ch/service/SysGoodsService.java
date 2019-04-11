package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.GoodsParam;
import com.ch.model.SysGoodsParam;

public interface SysGoodsService {

    /**
     * 后台商品列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult goodsList(SysGoodsParam param, Integer userId);

    /**
     * 改变商品状态
     * @param param
     * @param userId
     * @return
     */
    ResponseResult goodsStatus(GoodsParam param, Integer userId);

    /**
     * 删除商品
     * @param goodsId
     * @param userId
     * @return
     */
    ResponseResult deleteGoods(Integer goodsId, Integer userId);
}
