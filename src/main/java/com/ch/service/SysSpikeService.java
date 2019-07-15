package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.SysSpikeGoodsModel;

public interface SysSpikeService {

    /**
     * 管理秒杀
     * @param model
     * @return
     */
    ResponseResult mange(SysSpikeGoodsModel model);
}
