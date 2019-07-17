package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.SysSpikeGoodsModel;

import java.util.List;

public interface SysSpikeService {

    /**
     * 管理秒杀
     * @param model
     * @return
     */
    ResponseResult mange(SysSpikeGoodsModel model);

    /**
     * 秒杀列表
     * @param sn
     * @return
     */
    ResponseResult list(String sn, Integer currentPage, Integer pageSize);


    /**
     * 删除秒杀
     * @param id
     * @return
     */
    ResponseResult delete(Integer id);
}
