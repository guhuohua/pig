package com.ch.service;

import com.ch.dto.ShopInfo;

public interface ViewShopInfoService {
    ShopInfo findShopInfoByAppId(String appId);


    Integer findShopId(String appId);
}
