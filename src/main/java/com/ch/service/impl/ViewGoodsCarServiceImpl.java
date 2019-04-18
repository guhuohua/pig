/**
 * Author: 常富文
 * Date:   2019/4/16 10:28
 * Description: 购物车实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.ViewGoodsCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ViewGoodsCarServiceImpl implements ViewGoodsCarService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;
    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsMapper goodsMapper;



    @Override


    public ResponseResult addCar(Integer skuAtrId,Integer num,String openId,Integer shopId) {

        Map map = new HashMap();
        Long totalFee = null;
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size()>0){
            userInfo = userInfos.get(0);
        }

        GoodsSkuAttribute goodsSkuAttribute = goodsSkuAttributeMapper.selectByPrimaryKey(skuAtrId);

        Specification specification = specificationMapper.selectByPrimaryKey(goodsSkuAttribute.getSpecificationId());

        SpecificationAttribute specificationAttribute = specificationAttributeMapper.selectByPrimaryKey(goodsSkuAttribute.getSpecificationAttributeId());

        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(goodsSkuAttribute.getSkuId());

       // totalFee=goodsSku.getPresentPrice()*num;

        Goods goods = goodsMapper.selectByPrimaryKey(goodsSkuAttribute.getGoodsId());


       // totalFee=goodsSku.getPresentPrice()*num;
        BigDecimal big = new BigDecimal(totalFee);
        GoodCar goodCar = new GoodCar();
        goodCar.setNum(num);
        goodCar.setSkuId(goodsSkuAttribute.getSkuId());
        goodCar.setSkuAttrId(skuAtrId);
        goodCar.setTotalFee(big);
        goodCar.setUserId(userInfo.getId());

        map.put("spec",specification.getTitle());
        map.put("specAttr",specificationAttribute.getName());
        map.put("price",goodsSku.getPresentPrice());
        map.put("totalFee",totalFee);
        map.put("skuImg",goodsSku.getGoodsImage());
        map.put("num",num);
        map.put("name",goods.getName());

        ResponseResult result = new ResponseResult();
        result.setData(map);
        return result;

    }



}
