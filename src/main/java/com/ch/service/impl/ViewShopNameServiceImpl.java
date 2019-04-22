/**
 * Author: 常富文
 * Date:   2019/4/19 18:17
 * Description: 展示商家名字
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.ShopMapper;
import com.ch.dao.ShopMiniProgramMapper;
import com.ch.entity.Shop;
import com.ch.entity.ShopMiniProgram;
import com.ch.entity.ShopMiniProgramExample;
import com.ch.service.ViewShopNameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewShopNameServiceImpl implements ViewShopNameService {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;



    @Override
    public ResponseResult showShopName(Integer shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        ResponseResult result = new ResponseResult();
        result.setData(shop);
        return result;
    }

    @Override
    public ShopMiniProgram shopPayInfo(Integer shopId) {
        ShopMiniProgramExample example = new ShopMiniProgramExample();
        ShopMiniProgramExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        List<ShopMiniProgram> shopMiniPrograms = shopMiniProgramMapper.selectByExample(example);
        ShopMiniProgram shopMiniProgram = null;
        if (shopMiniPrograms.stream().findFirst().isPresent()){
            shopMiniProgram   = shopMiniPrograms.stream().findFirst().get();
        }

        return shopMiniProgram;
    }
}
