package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.GoodsAreaParam;
import com.ch.model.SysRecommendParam;

public interface SysRecommendService {

    /**
     * 商品推荐分页查询
     * @param param
     * @return
     */
    ResponseResult list(SysRecommendParam param, Integer userId);

    /**
     * 商品推荐管理
     * @param param
     * @return
     */
    ResponseResult mange(GoodsAreaParam param, Integer userId);

    /**
     * 改变商品推荐状态
     * @param param
     * @param userId
     * @return
     */
    ResponseResult status(GoodsAreaParam param, Integer userId);

    /**
     * 删除商品推荐
     * @param param
     * @param userId
     * @return
     */
    ResponseResult delete(GoodsAreaParam param, Integer userId);
}
