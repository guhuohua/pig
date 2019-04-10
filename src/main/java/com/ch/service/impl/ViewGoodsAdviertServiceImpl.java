/**
 * Author: 常富文
 * Date:   2019/4/4 11:56
 * Description: 首页轮播图实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsAdvertMapper;
import com.ch.entity.GoodsAdvert;
import com.ch.entity.GoodsAdvertExample;
import com.ch.service.ViewGoodsAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewGoodsAdviertServiceImpl implements ViewGoodsAdvertService {


    @Autowired
    GoodsAdvertMapper goodsAdvertMapper;
    @Override
    public ResponseResult findByShopId(Integer shopId) {
        GoodsAdvertExample example = new GoodsAdvertExample();
        GoodsAdvertExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andStatusEqualTo(1);
        List<GoodsAdvert> goodsAdverts = goodsAdvertMapper.selectByExample(example);
        ResponseResult result = new ResponseResult();
        result.setData(goodsAdverts);
        return result;
    }
}
