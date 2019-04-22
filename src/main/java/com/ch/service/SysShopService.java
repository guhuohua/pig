package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.ShopParam;
import com.ch.entity.Shop;

public interface SysShopService {

     Shop findShopById( Integer id);

     ResponseResult findAll(ShopParam shopParam);

     ResponseResult updateByPrimaryKey(Shop record);

     ResponseResult deleteByPrimaryKey(Integer id);

     ResponseResult insert(Shop record);
     ResponseResult serach(ShopParam shopParam);
}
