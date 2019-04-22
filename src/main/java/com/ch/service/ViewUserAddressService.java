package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.UserAddress;

public interface ViewUserAddressService {
    ResponseResult deleteByPrimaryKey(Integer id);

    ResponseResult insert(UserAddress record,String openId,Integer shopId);

    ResponseResult updateByPrimaryKey(UserAddress record,String openId,Integer shopId);
}