/**
 * Author: 常富文
 * Date:   2019/4/4 15:50
 * Description: 商品详细页实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsEvaluationMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsSTypeMapper;
import com.ch.entity.*;
import com.ch.service.ViewGoodsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ViewGoodsDetailsServiceImpl implements ViewGoodsDetailsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsEvaluationMapper goodsEvaluationMapper;
    @Autowired
    GoodsSTypeMapper goodsSTypeMapper;
    @Override
    public ResponseResult findGoodsDetailsByGoodsId(Integer goodsId) {
        //List goodsDatils = new ArrayList();
        Map goodsDetailsMap = new HashMap();
        //查询商品表
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        goodsDetailsMap.put("goods",goods);
        //查询商品规格表
        GoodsSpecificationExample example = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = example.createCriteria();
        criteria.andSnEqualTo(goods.getSn());
        List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(example);
        if(goodsSpecifications.size()>0){
            GoodsSpecification goodsSpecification = goodsSpecifications.get(0);
            goodsDetailsMap.put("Spec",goodsSpecification);
        }
        //查询商品评价表
        GoodsEvaluationExample example1 = new GoodsEvaluationExample();
        GoodsEvaluationExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andGoodsIdEqualTo(goods.getId());
        List<GoodsEvaluation> goodsEvaluations = goodsEvaluationMapper.selectByExample(example1);
        goodsDetailsMap.put("goodsEvaluations",goodsEvaluations);
        ResponseResult result = new ResponseResult();
        result.setData(goodsDetailsMap);
        return result;
    }
}
