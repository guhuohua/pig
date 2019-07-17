/**
 * Author: 常富文
 * Date:   2019/4/26 10:18
 * Description: 商品评价
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.SysMemberService;
import com.ch.service.ViewOrderEvaluteService;
import com.ch.util.FlowUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewOrderEvaluteServiceImpl implements ViewOrderEvaluteService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    GoodsEvaluationMapper goodsEvaluationMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    BaseIntegralMapper baseIntegralMapper;
    @Autowired
    SysMemberService sysMemberService;




    @Override
    public ResponseResult  addEvalute(GoodsEvaluation goodsEvaluation, Integer shopId, String openId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        BaseIntegral baseIntegral = baseIntegralMapper.selectByPrimaryKey(1);
        userInfo.setIntegral(userInfo.getIntegral()+baseIntegral.getComment());
        userInfo.setUseIntegral(userInfo.getUseIntegral()+baseIntegral.getComment());
        userInfoMapper.updateByPrimaryKey(userInfo);
        sysMemberService.synchronizedIntegral(userInfo.getId());
        FlowUtil.addFlowTel(baseIntegral.getPerfect().longValue(),"comment","INTEGRAL",0);
        String nickname = userInfo.getNickname();
        String name = nickname.substring(0, 1) + "**" + nickname.substring((nickname.length() - 1), nickname.length());
        goodsEvaluation.setShopId(shopId);
        goodsEvaluation.setCreateTime(new Date());
        goodsEvaluation.setStatus(0);
        goodsEvaluation.setName(name);
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(goodsEvaluation.getOrderItemId());
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderItem.getOrderId());
        goodsEvaluation.setOrderId(goodsOrder.getId());
        goodsEvaluation.setGoodsId(orderItem.getGoodsId());
        goodsEvaluationMapper.insert(goodsEvaluation);
        goodsOrder.setOrderStatus(11);
        goodsOrderMapper.updateByPrimaryKey(goodsOrder);
        return result;

    }

    @Override
    public ResponseResult showGoodEvluate(Integer goodsId, Integer shopId) {
        GoodsEvaluationExample example = new GoodsEvaluationExample();
        example.setOrderByClause("create_time desc");
        GoodsEvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andShopIdEqualTo(shopId);
        criteria.andScoreEqualTo(5);
        List<GoodsEvaluation> list = goodsEvaluationMapper.selectByExample(example);
        ResponseResult result = new ResponseResult();
        Collections.reverse(list);
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult showBadEvluate(Integer goodsId, Integer shopId) {
        GoodsEvaluationExample example = new GoodsEvaluationExample();
        example.setOrderByClause("create_time desc");
        GoodsEvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andShopIdEqualTo(shopId);
        criteria.andScoreLessThanOrEqualTo(2);
        List<GoodsEvaluation> list = goodsEvaluationMapper.selectByExample(example);
        ResponseResult result = new ResponseResult();
        Collections.reverse(list);
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult showMediumEvluate(Integer goodsId, Integer shopId) {
        GoodsEvaluationExample example = new GoodsEvaluationExample();
        example.setOrderByClause("create_time desc");
        GoodsEvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andScoreEqualTo(3);
        criteria.andShopIdEqualTo(shopId);
        GoodsEvaluationExample.Criteria criteria1 = example.createCriteria();
        criteria1.andScoreEqualTo(4);
        example.or(criteria1);
        List<GoodsEvaluation> list = goodsEvaluationMapper.selectByExample(example);
        ResponseResult result = new ResponseResult();
        Collections.reverse(list);
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult showAllEvluate(Integer goodsId, Integer shopId) {
        GoodsEvaluationExample example = new GoodsEvaluationExample();
        example.setOrderByClause("create_time desc");
        GoodsEvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andShopIdEqualTo(shopId);
        List<GoodsEvaluation> list = goodsEvaluationMapper.selectByExample(example);
        ResponseResult result = new ResponseResult();
        Collections.reverse(list);
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult showMyEvluate(Integer shopId, String openId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        String nickname = userInfo.getNickname();
        String name = nickname.substring(0, 1) + "**" + nickname.substring((nickname.length() - 1), nickname.length());
        GoodsEvaluationExample example1 = new GoodsEvaluationExample();
        GoodsEvaluationExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andNameEqualTo(name);
        criteria1.andShopIdEqualTo(shopId);
        List<GoodsEvaluation> list = goodsEvaluationMapper.selectByExample(example1);
        List list1 = new ArrayList();
        for (GoodsEvaluation goodsEvaluation : list) {
            OrderItem orderItem = orderItemMapper.selectByPrimaryKey(goodsEvaluation.getOrderItemId());
            goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
            Map map = new HashMap();
            map.put("orderItem", orderItem);
            map.put("goodsEvaluation", goodsEvaluation);
            list1.add(map);
        }
        result.setData(list1);
        return result;
    }
}
