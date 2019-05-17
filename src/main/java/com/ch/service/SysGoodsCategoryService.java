package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsType;
import com.ch.model.SysCategoryParam;

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


    /**
     * 改变类目状态
     * @param param
     * @param userId
     * @return
     */
    ResponseResult updateStatus(SysCategoryParam param, Integer userId);

    /**
     * 查找一级类目ID以及名称
     * @param userId
     * @return
     */
    ResponseResult findOneCategory(Integer userId);


    /**
     * 根据ID查询
     * @param id
     * @param userId
     * @return
     */
    ResponseResult findById(Integer id, Integer userId);
}
