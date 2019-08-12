/**
 * Author: 常富文
 * Date:   2019/4/16 10:28
 * Description: 购物车实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.CarDto;
import com.ch.entity.*;
import com.ch.service.ViewGoodsCarService;
import com.ch.service.ViewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    SpikeGoodsMapper spikeGoodsMapper;
    @Autowired
    ViewUserInfoService viewUserInfoService;



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

        SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
        SpikeGoodsExample.Criteria exampleCriteria = spikeGoodsExample.createCriteria();
        exampleCriteria.andSkuIdEqualTo(skuId);
        exampleCriteria.andBeginDateLessThan(new Date());
        exampleCriteria.andEndDateGreaterThan(new Date());
        List<SpikeGoods> spikeGoods = spikeGoodsMapper.selectByExample(spikeGoodsExample);
        if (goodsCars.size() > 0) {
            for (GoodsCar goodsCar : goodsCars) {
                if (goodsCar.getSkuId() == goodsSku.getId()) {
                    goodsCar.setNum(goodsCar.getNum() + num);
                    if (spikeGoods.size() > 0) {
                        totalFee = spikeGoods.get(0).getSpikePrice() * goodsCar.getNum();
                    }else {
                        totalFee = goodsSku.getPresentPrice() * goodsCar.getNum();
                    }
                    goodsCar.setTotalFee(totalFee);
                    goodsCarMapper.updateByPrimaryKey(goodsCar);
                    break;
                } else {
                    GoodsCarExample goodsCarExample = new GoodsCarExample();
                    goodsCarExample.createCriteria().andShopIdEqualTo(shopId)
                            .andUserIdEqualTo(userInfo.getId()).andSkuIdEqualTo(skuId);
                    boolean present = goodsCarMapper.selectByExample(goodsCarExample).stream().findFirst().isPresent();
                    if (present) {
                        GoodsCar goodsCar2 = goodsCarMapper.selectByExample(goodsCarExample).stream().findFirst().get();
                        goodsCar2.setNum(goodsCar2.getNum() + num);
                        if (spikeGoods.size() > 0) {
                            totalFee = spikeGoods.get(0).getSpikePrice() * goodsCar2.getNum();
                        }else {
                            totalFee = goodsSku.getPresentPrice() * goodsCar2.getNum();
                        }

                        goodsCar2.setTotalFee(totalFee);
                        goodsCarMapper.updateByPrimaryKey(goodsCar2);
                        break;
                    } else {
                        GoodsCar goodsCar1 = new GoodsCar();
                        goodsCar1.setShopId(shopId);
                        goodsCar1.setNum(num);
                        goodsCar1.setSkuId(skuId);
                        if (spikeGoods.size() > 0) {
                            totalFee = spikeGoods.get(0).getSpikePrice() * num;
                        }else {
                            totalFee = goodsSku.getPresentPrice() * num;
                        }
                        goodsCar1.setTotalFee(totalFee);
                        goodsCar1.setUserId(userInfo.getId());
                        goodsCarMapper.insert(goodsCar1);
                        break;
                    }
                }
            }
        } else {
            GoodsCar goodsCar1 = new GoodsCar();
            goodsCar1.setShopId(shopId);
            goodsCar1.setNum(num);
            goodsCar1.setSkuId(skuId);
            if (spikeGoods.size() > 0) {
                totalFee = spikeGoods.get(0).getSpikePrice() * num;
            }else {
                totalFee = goodsSku.getPresentPrice() * num;
            }
            goodsCar1.setTotalFee(totalFee);
            goodsCar1.setUserId(userInfo.getId());
            goodsCarMapper.insert(goodsCar1);
        }

        ResponseResult result = new ResponseResult();
        return result;

    }

    @Override
    public ResponseResult showCar(String openId, Integer shopId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        int discount = viewUserInfoService.findDiscountByOpenId(openId);
        GoodsCarExample example1 = new GoodsCarExample();
        GoodsCarExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userInfo.getId());
        criteria1.andShopIdEqualTo(shopId);
        List<GoodsCar> goodsCars = goodsCarMapper.selectByExample(example1);
        List list = new ArrayList<>();


        for (GoodsCar goodsCar : goodsCars) {
            CarDto carDto = new CarDto();
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(goodsCar.getSkuId());
            goodsSku.setPresentPrice(goodsSku.getPresentPrice()*discount/100);
            Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());

            SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
            SpikeGoodsExample.Criteria exampleCriteria = spikeGoodsExample.createCriteria();
            exampleCriteria.andSkuIdEqualTo(goodsSku.getId());
            exampleCriteria.andBeginDateLessThan(new Date());
            exampleCriteria.andEndDateGreaterThan(new Date());
            List<SpikeGoods> spikeGoods = spikeGoodsMapper.selectByExample(spikeGoodsExample);

            if (goods != null) {
                if (1 == goods.getStatus()) {
                    carDto.setGoodsSku(goodsSku);
                    carDto.setNum(goodsCar.getNum());
                    carDto.setName(goods.getName());
                    if (spikeGoods.size()>0){
                        carDto.setSpikeGoods(spikeGoods.get(0));
                    }
                    carDto.setFlag(1);
                    list.add(carDto);
                }


                if (0 == goods.getStatus()) {
                    carDto.setGoodsSku(goodsSku);
                    carDto.setNum(goodsCar.getNum());
                    carDto.setName(goods.getName());
                    carDto.setFlag(0);
                    if (spikeGoods.size()>0){
                        carDto.setSpikeGoods(spikeGoods.get(0));
                    }
                    list.add(carDto);
                }
            }
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
                /*GoodsCarExample exampl1 = new GoodsCarExample();
                GoodsCarExample.Criteria criteria1 = exampl1.createCriteria();
                criteria1.andSkuIdNotEqualTo(carDto.getGoodsSku().getId());
                goodsCarMapper.deleteByExample(exampl1);*/
            }
        }
        ResponseResult result = new ResponseResult();
        return result;
    }


    @Override
    public ResponseResult deleCar(Integer[] ids) {
        for (Integer id : ids) {
            GoodsCarExample example = new GoodsCarExample();
            GoodsCarExample.Criteria criteria = example.createCriteria();
            criteria.andSkuIdEqualTo(id);
            goodsCarMapper.deleteByExample(example);
        }
        ResponseResult result = new ResponseResult();
        return result;
    }


}
