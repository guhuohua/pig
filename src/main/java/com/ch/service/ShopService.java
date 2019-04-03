package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.Shop;

import java.util.List;

public interface ShopService {

     Shop findShopById( Integer id);

     ResponseResult findAll();

     ResponseResult updateByPrimaryKey(Shop record);

     ResponseResult deleteByPrimaryKey(Integer id);

     ResponseResult insert(Shop record);
}
