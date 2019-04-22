package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.ShopMiniProgram;

public interface ViewShopNameService {
    public ResponseResult showShopName(Integer shopId);

    public ShopMiniProgram shopPayInfo(Integer shopId);

}
