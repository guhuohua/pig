/**
 * Author: 常富文
 * Date:   2019/4/10 15:50
 * Description: 店铺信息
 */


package com.ch.service.impl;

import com.ch.dao.ShopMiniProgramMapper;

import com.ch.dto.ShopInfo;
import com.ch.entity.ShopMiniProgram;
import com.ch.entity.ShopMiniProgramExample;
import com.ch.service.ViewShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewShopInfoServiceImpl implements ViewShopInfoService {

    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;

    @Override
   public ShopInfo findShopInfoByAppId(String appId) {

        ShopMiniProgramExample example = new ShopMiniProgramExample();
        ShopMiniProgramExample.Criteria criteria = example.createCriteria();
        criteria.andAppIdEqualTo(appId);
        List<ShopMiniProgram> shopMiniPrograms = shopMiniProgramMapper.selectByExample(example);
        ShopInfo shopInfo = new ShopInfo();
        if (shopMiniPrograms.size() > 0) {
            ShopMiniProgram shopMiniProgram = shopMiniPrograms.get(0);

            shopInfo.setSecret(shopMiniProgram.getSecret());
            shopInfo.setShopId(shopMiniProgram.getShopId());
        }


        return shopInfo;
    }
}
