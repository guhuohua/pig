/**
 * Author: 常富文
 * Date:   2019/4/19 18:17
 * Description: 展示商家名字
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.ShopMapper;
import com.ch.entity.Shop;
import com.ch.service.ViewShopNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewShopNameServiceImpl implements ViewShopNameService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public ResponseResult showShopName(Integer shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        ResponseResult result = new ResponseResult();
        result.setData(shop);
        return result;
    }
}
