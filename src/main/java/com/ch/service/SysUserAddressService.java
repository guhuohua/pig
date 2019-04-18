package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.BaseParam;

public interface SysUserAddressService {

    ResponseResult list(BaseParam param, Integer userId);
}
