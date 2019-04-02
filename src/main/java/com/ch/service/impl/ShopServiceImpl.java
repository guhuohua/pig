/**
 * Author: 常富文
 * Date:   2019/4/2 16:08
 * Description: shop实现类
 */


package com.ch.service.impl;

import com.ch.dao.ShopMapper;
import com.ch.entity.Shop;
import com.ch.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public Shop findShopById(Integer Id) {
        return shopMapper.selectByPrimaryKey(Id);
    }
}
