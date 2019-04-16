package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.BaseParam;

public interface SysShopUserService {

    /**
     * 后台用户管理列表
     * @param param
     * @param userId
     * @return
     */
    ResponseResult list(BaseParam param, Integer userId);
}
