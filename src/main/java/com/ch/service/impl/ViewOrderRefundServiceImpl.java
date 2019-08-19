/**
 * Author: 常富文
 * Date:   2019/4/26 10:55
 * Description: 售后
 */


package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.ViewOrderRefundService;
import com.ch.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ViewOrderRefundServiceImpl implements ViewOrderRefundService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    OrderRefundMapper orderRefundMapper;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    GoodsOrderMapper orderMapper;


    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    GoodsMapper goodsMapper;


    @Override
    public ResponseResult addOrderRefund(List<OrderRefund> orderRefunds, String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        OrderRefundExample example1 = new OrderRefundExample();
        OrderRefundExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOrderIdEqualTo(orderRefunds.stream().findFirst().get().getOrderId());
        List<OrderRefund> orderRefundList = orderRefundMapper.selectByExample(example1);
        if (orderRefundList.size() > 0) {
            result.setCode(500);
            result.setError_description("当前订单已申请售后，请勿重复添加");
            return result;
        } else {
            for (OrderRefund orderRefund : orderRefunds) {
                UserInfoExample example = new UserInfoExample();
                UserInfoExample.Criteria criteria = example.createCriteria();
                criteria.andOpenIdEqualTo(openId);
                List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
                UserInfo userInfo = null;
                if (userInfos.size() > 0) {
                    userInfo = userInfos.get(0);
                }
                orderRefund.setId(IdUtil.createIdByUUID());
                orderRefund.setCreateDate(new Date());
                orderRefund.setShopId(shopId);
                orderRefund.setRefundStatus(1);
                orderRefund.setUserId(userInfo.getId());
                orderRefundMapper.insert(orderRefund);
                GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
                goodsOrder.setRefundId(orderRefund.getId());
                goodsOrder.setOrderStatus(11);
                goodsOrderMapper.updateByPrimaryKey(goodsOrder);
            }
        }
        return result;
    }

    @Override
    public ResponseResult showRefundList(Integer status, String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        List list = new ArrayList();
        //售后中
        if (status == 1) {
            OrderRefundExample example1 = new OrderRefundExample();
            OrderRefundExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andShopIdEqualTo(shopId);
            criteria1.andRefundStatusEqualTo(1);
            List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(example1);
            for (OrderRefund orderRefund : orderRefunds) {
                GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
                OrderItemExample orderExample1 = new OrderItemExample();
                OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                if (BeanUtils.isNotEmpty(goodsOrder)) {
                    criteria2.andOrderIdEqualTo(goodsOrder.getId());
                    criteria2.andGoodsIdEqualTo(orderRefund.getGoodsId());
                    criteria2.andSkuAttrIdEqualTo(orderRefund.getSkuId());
                }
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                OrderItem orderItem = orderItems.stream().findFirst().get();
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                Map map = new HashMap();
                map.put("orderRefund", orderRefund);
                map.put("goodsSku1", goodsSku);
                map.put("goods1", goods);
                map.put("orderItem", orderItem);
                list.add(map);
            }
        }

        //售后通过
        if (status == 2) {
            OrderRefundExample example1 = new OrderRefundExample();
            OrderRefundExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andShopIdEqualTo(shopId);
            criteria1.andRefundStatusEqualTo(2);
            List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(example1);
            for (OrderRefund orderRefund : orderRefunds) {
                GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
                OrderItemExample orderExample1 = new OrderItemExample();
                OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                if (BeanUtils.isNotEmpty(goodsOrder)) {
                    criteria2.andOrderIdEqualTo(goodsOrder.getId());
                    criteria2.andGoodsIdEqualTo(orderRefund.getGoodsId());
                    criteria2.andSkuAttrIdEqualTo(orderRefund.getSkuId());
                }
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                OrderItem orderItem = orderItems.stream().findFirst().get();
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                Map map = new HashMap();
                map.put("orderRefund", orderRefund);
                map.put("goodsSku1", goodsSku);
                map.put("goods1", goods);
                map.put("orderItem", orderItem);
                list.add(map);
            }
        }
        //售后拒绝
        if (status == 3) {
            OrderRefundExample example1 = new OrderRefundExample();
            OrderRefundExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andShopIdEqualTo(shopId);
            criteria1.andRefundStatusEqualTo(3);
            List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(example1);
            for (OrderRefund orderRefund : orderRefunds) {
                GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
                OrderItemExample orderExample1 = new OrderItemExample();
                OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                if (BeanUtils.isNotEmpty(goodsOrder)) {
                    criteria2.andOrderIdEqualTo(goodsOrder.getId());
                    criteria2.andGoodsIdEqualTo(orderRefund.getGoodsId());
                    criteria2.andSkuAttrIdEqualTo(orderRefund.getSkuId());
                }
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                OrderItem orderItem = orderItems.stream().findFirst().get();
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                Map map = new HashMap();
                map.put("orderRefund", orderRefund);
                map.put("goodsSku1", goodsSku);
                map.put("goods1", goods);
                map.put("orderItem", orderItem);
                list.add(map);
            }
        }
        result.setData(list);
        return result;
    }
    @Override
    public ResponseResult refundCount(String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }

        OrderRefundExample example1 = new OrderRefundExample();
        OrderRefundExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userInfo.getId());
        criteria1.andShopIdEqualTo(shopId);
        criteria1.andRefundStatusEqualTo(1);
        List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(example1);
        result.setData(orderRefunds.size());
        return result;
    }
}
