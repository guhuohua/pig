package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.SysGoodsSkuParam;

public interface SysGoodsSkuService {

    /**
     * 商品规格分页查询
     * @param name
     * @param userId
     * @return
     */
    ResponseResult list(String name, Integer userId, Integer currentPage, Integer pageSize);


    /**
     * 管理商品规格
     * @param param
     * @param userId
     * @return
     */
    ResponseResult mange(SysGoodsSkuParam param, Integer userId);


    /**
     * 删除商品规格
     * @param id
     * @param userId
     * @return
     */
    ResponseResult delete(Integer id, Integer userId);


    /**
     * 规格值删除
     * @param id
     * @param userId
     * @return
     */
    ResponseResult deleteSpecificationAttribute(Integer id, Integer userId);


    /**
     * 商品分类下拉菜单
     * @param userId
     * @return
     */
    ResponseResult goodsClassification(Integer userId);

    /**
     * 根据ID查询规格信息
     * @param id
     * @param userId
     * @return
     */
    ResponseResult findById(Integer id, Integer userId);
}
