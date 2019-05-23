/**
 * Author: 常富文
 * Date:   2019/4/10 15:50
 * Description: 店铺信息
 */


package com.ch.service.impl;

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

        ShopMiniProgramExample example = new ShopMiniProgramExample();
        ShopMiniProgramExample.Criteria criteria = example.createCriteria();
        criteria.andAppIdEqualTo(appId);
        List<ShopMiniProgram> shopMiniPrograms = shopMiniProgramMapper.selectByExample(example);
        ShopInfo shopInfo = new ShopInfo();
        if (shopMiniPrograms.size() > 0) {
            ShopMiniProgram shopMiniProgram = shopMiniPrograms.get(0);
            shopInfo.setSecret(shopMiniProgram.getSecret());
            Integer shopId = shopMiniProgram.getShopId();
            Shop shop = shopMapper.selectByPrimaryKey(shopId);
            if (shop.getStartTime().getTime() <= new Date().getTime() && shop.getEndTime().getTime() >=  new Date().getTime()){
                shopInfo.setShopId(shop.getId());
              //  System.out.println(shopInfo);

            }
        }
        return shopInfo;
    }
}
