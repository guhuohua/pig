/**
 * Author: 常富文
 * Date:   2019/4/26 10:18
 * Description: 商品评价
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsEvaluationMapper;
import com.ch.dao.GoodsOrderMapper;
import com.ch.dao.OrderItemMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.*;
import com.ch.service.ViewOrderEvaluteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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


    @Override
    public ResponseResult addEvalute(GoodsEvaluation goodsEvaluation, Integer shopId, String openId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        goodsEvaluation.setShopId(shopId);
        goodsEvaluation.setCreateTime(new Date());
        goodsEvaluation.setName(userInfo.getNickname());
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(goodsEvaluation.getOrderItemId());
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderItem.getOrderId());
        goodsEvaluation.setOrderId(goodsOrder.getId());
        goodsEvaluation.setGoodsId(orderItem.getGoodsId());
        goodsEvaluationMapper.insert(goodsEvaluation);
        return result;
    }
}
