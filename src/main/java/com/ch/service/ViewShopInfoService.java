package com.ch.service;

import com.ch.dto.ShopInfo;
import com.ch.entity.ShopMiniProgram;

public interface ViewShopInfoService {
    ShopInfo findShopInfoByAppId(String appId);
}
