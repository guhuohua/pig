/**
 * Author: 常富文
 * Date:   2019/4/16 10:28
 * Description: 购物车实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsCarMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsSkuMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dto.CarDto;
import com.ch.entity.*;
import com.ch.service.ViewGoodsCarService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ViewGoodsCarServiceImpl implements ViewGoodsCarService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsCarMapper goodsCarMapper;

    @Override

    public ResponseResult addCar(Integer skuId, Integer num, String openId, Integer shopId) {
        Long totalFee = null;
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(skuId);
        GoodsCarExample example1 = new GoodsCarExample();
        GoodsCarExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andShopIdEqualTo(shopId);
        criteria1.andUserIdEqualTo(userInfo.getId());
        List<GoodsCar> goodsCars = goodsCarMapper.selectByExample(example1);
        for (GoodsCar goodsCar : goodsCars) {
            if (goodsCar.getSkuId() == goodsSku.getId()) {
                goodsCar.setNum(goodsCar.getNum() + num);
                totalFee = goodsSku.getPresentPrice() * goodsCar.getNum();
                goodsCar.setTotalFee(totalFee);
                goodsCarMapper.updateByPrimaryKey(goodsCar);
            } else {
                GoodsCar goodsCar1 = new GoodsCar();
                goodsCar1.setShopId(shopId);
                goodsCar1.setNum(num);
                goodsCar1.setSkuId(skuId);
                goodsCar1.setTotalFee(totalFee);
                goodsCar1.setUserId(userInfo.getId());
                goodsCarMapper.insert(goodsCar1);
            }
        }
        ResponseResult result = new ResponseResult();
        return result;

    }

    @Override
    public ResponseResult showCar(String openId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        GoodsCarExample example1 = new GoodsCarExample();
        GoodsCarExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userInfo.getId());
        List<GoodsCar> goodsCars = goodsCarMapper.selectByExample(example1);
        List list = new ArrayList<>();

        for (GoodsCar goodsCar : goodsCars) {
            CarDto carDto = new CarDto();
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(goodsCar.getSkuId());
            Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
            carDto.setGoodsSku(goodsSku);
            carDto.setNum(goodsCar.getNum());
            carDto.setName(goods.getName());
            list.add(carDto);
        }

        ResponseResult result = new ResponseResult();
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult updateCar(CarDto[] carDtos) {
        for (CarDto carDto : carDtos) {
            GoodsCarExample example = new GoodsCarExample();
            GoodsCarExample.Criteria criteria = example.createCriteria();
            criteria.andSkuIdEqualTo(carDto.getGoodsSku().getId());
            List<GoodsCar> goodsCars = goodsCarMapper.selectByExample(example);
            if (goodsCars.size() > 0) {
                for (GoodsCar goodsCar : goodsCars) {
                    goodsCar.setNum(carDto.getNum());
                    goodsCarMapper.updateByPrimaryKey(goodsCar);

                }

                GoodsCarExample exampl1 = new GoodsCarExample();
                GoodsCarExample.Criteria criteria1 = exampl1.createCriteria();
                criteria1.andSkuIdNotEqualTo(carDto.getGoodsSku().getId());
                goodsCarMapper.deleteByExample(exampl1);
            }
        }
        ResponseResult result = new ResponseResult();
        return result;
    }


}
