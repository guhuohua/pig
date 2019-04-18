package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.BaseParam;

public interface SysEvaluateService {

    /**
     * 后台评价列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult list(BaseParam param, Integer userId);

    /**
     * 评价列表统计
     * @param userId
     * @return
     */
    ResponseResult count(Integer userId);

    /**
     * 评价列表回复
     * @param param
     * @param userId
     * @return
     */
    ResponseResult reply(BaseParam param, Integer userId);


    /**
     * 删除评价
     * @param param
     * @param userId
     * @return
     */
    ResponseResult delete(BaseParam param, Integer userId);
}
