package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.ShopUserParam;

public interface SysUserMangeService {


    ResponseResult findByShopId(ShopUserParam param);


    ResponseResult insertUser();
}
