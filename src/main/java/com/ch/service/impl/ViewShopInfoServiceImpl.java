/**
 * Author: 常富文
 * Date:   2019/4/10 15:50
 * Description: 店铺信息
 */


package com.ch.service.impl;

import com.alibaba.fastjson.JSON;
import com.ch.dao.ShopMapper;
import com.ch.dao.ShopMiniProgramMapper;
import com.ch.dto.ShopInfo;
import com.ch.entity.Shop;
import com.ch.entity.ShopMiniProgram;
import com.ch.entity.ShopMiniProgramExample;
import com.ch.service.ViewShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViewShopInfoServiceImpl implements ViewShopInfoService {

    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;
    @Autowired
    ShopMapper shopMapper;

    @Override
    public ShopInfo findShopInfoByAppId(String appId) {
        ShopMiniProgram shopMiniProgram = shopMiniProgramMapper.findByAppid(appId);
        ShopInfo shopInfo = new ShopInfo();
        System.out.println("ShopMiniProgram:" + JSON.toJSON(shopMiniProgram));
        shopInfo.setSecret(shopMiniProgram.getSecret());
        Integer shopId = shopMiniProgram.getShopId();
        shopInfo.setShopId(shopId);
        return shopInfo;
    }
}
