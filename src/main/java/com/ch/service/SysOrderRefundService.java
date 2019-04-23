package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.SysOrderRefundParam;
import com.ch.model.SysRefundThroughParam;

public interface SysOrderRefundService {

    /**
     * 售后列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult list(SysOrderRefundParam param, Integer userId);


    /**
     * 售后处理
     * @param param
     * @param userId
     * @return
     */
    ResponseResult refundHandle(SysRefundThroughParam param, Integer userId);

}
