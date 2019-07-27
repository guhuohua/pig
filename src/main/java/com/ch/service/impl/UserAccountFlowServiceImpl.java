package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.model.PageParam;
import com.ch.service.SysMemberService;
import com.ch.service.UserAccountFlowService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.FlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountFlowServiceImpl implements UserAccountFlowService {

    @Autowired
    UserAccountFlowMapper userAccountFlowMapper;
    @Autowired
    ViewUserInfoService viewUserInfoService;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    SysMemberService sysMemberService;



    @Override
    public ResponseResult list(String openId) {
        ResponseResult result = new ResponseResult();
        UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
       // PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSzie());
        UserAccountFlowExample example = new UserAccountFlowExample();
        UserAccountFlowExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        List<UserAccountFlow> userAccountFlows = userAccountFlowMapper.selectByExample(example);
        PageInfo<UserAccountFlow> pageInfo = new PageInfo<>(userAccountFlows);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult addAccountFlow(String orderId) {
        ResponseResult result = new ResponseResult();
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        OrderItem orderItem = orderItems.get(0);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(goodsOrder.getUserId());
        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
        FlowUtil.addFlowTel(Long.valueOf(goodsSku.getConsumptionIntegral()),"INTEGRAL","INTEGRAL",1,userInfo.getId());
        sysMemberService.synchronizedIntegral(userInfo.getId());
        return result;
    }
}
