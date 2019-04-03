/**
 * Author: 常富文
 * Date:   2019/4/2 16:08
 * Description: shop实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.ShopMapper;
import com.ch.entity.Shop;
import com.ch.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public Shop findShopById(Integer Id) {
        return shopMapper.selectByPrimaryKey(Id);
    }

    @Override
    public ResponseResult findAll() {
        ResponseResult result = new ResponseResult();
        List<Shop> shops = shopMapper.selectByExample(null);
        result.setData(shops);
        return result;
    }

    @Override
    public ResponseResult updateByPrimaryKey(Shop record) {
        ResponseResult result = new ResponseResult();
        shopMapper.updateByPrimaryKey(record);
        return result;
    }

    @Override
    public ResponseResult deleteByPrimaryKey(Integer id) {
        ResponseResult result = new ResponseResult();
        shopMapper.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public ResponseResult insert(Shop record) {
        ResponseResult result = new ResponseResult();
        shopMapper.insert(record);
        return result;
    }



}
