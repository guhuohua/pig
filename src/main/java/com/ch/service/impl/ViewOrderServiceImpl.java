/**
 * Author: 常富文
 * Date:   2019/4/16 17:24
 * Description: 订单
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.OrderDto;
import com.ch.entity.*;
import com.ch.enums.OderStatusEnum;
import com.ch.service.ViewOrderService;
import com.ch.util.RandomUtils;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ExampleWhereClauseElementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ViewOrderServiceImpl implements ViewOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserAddressMapper userAddressMapper;


    @Override
    public ResponseResult addOrder(OrderDto[] orderDtoList, String openId, Integer shopId) {

        Long totalFee = 0l;
        Long orderFee = 0l;
        Order order = new Order();
        order.setId(System.currentTimeMillis() + RandomUtils.getRandomNumber(6));
        order.setShopId(shopId);
        order.setStatus(1);
        order.setCreateDate(new Date());
        order.setOrderPrice(orderFee);
        if (orderDtoList.length > 0) {
            for (OrderDto orderDto : orderDtoList) {
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderDto.getSkuId());
                Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                goodsSku.setInventory(goodsSku.getInventory() - 1);
                goodsSku.setSale(goodsSku.getSale() + 1);
                goodsSkuMapper.updateByPrimaryKey(goodsSku);
                totalFee = (goodsSku.getPresentPrice() * orderDto.getNum())+goods.getFreight();
                orderFee += totalFee;

                OrderItem orderItem = new OrderItem();
                orderItem.setGoodsId(goodsSku.getGoodsId());


                goods.setInventory(goodsSku.getInventory() - 1);
                goods.setSale(goodsSku.getSale() + 1);
                goodsMapper.updateByPrimaryKey(goods);
                orderItem.setGoodsName(goodsSku.getSkuName());
                orderItem.setNumber(orderDto.getNum());
                orderItem.setPrice(goodsSku.getPresentPrice());
                orderItem.setOrderId(order.getId());
                orderItem.setShopId(shopId);
                orderItemMapper.insert(orderItem);
            }
        }
        orderMapper.insert(order);
        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult showOrder(String orderId, String openId) {
        Map map = new HashMap();
        UserInfoExample exampleInfo = new UserInfoExample();
        UserInfoExample.Criteria criteria1 = exampleInfo.createCriteria();
        criteria1.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(exampleInfo);
        List<UserAddress> userAddresses1 = new ArrayList<>();
        List<UserAddress> userAddresses = new ArrayList<>();
        UserAddress userAddress = null;
        if (userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            UserAddressExample exampleAddress = new UserAddressExample();
            UserAddressExample.Criteria criteria = exampleAddress.createCriteria();
            criteria.andUserIdEqualTo(userInfo.getId());
            userAddresses1 = userAddressMapper.selectByExample(exampleAddress);

            UserAddressExample exampleAddress1 = new UserAddressExample();
            UserAddressExample.Criteria criteria2 = exampleAddress.createCriteria();
            criteria.andUserIdEqualTo(userInfo.getId());
            criteria.andStatusEqualTo(0);
            userAddresses = userAddressMapper.selectByExample(exampleAddress1);
            if (userAddresses.size() > 0) {
                userAddress = userAddresses.get(0);
            }
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        map.put("userAddresses1", userAddresses1);
        map.put("userAddress", userAddress);
        map.put("order", order);
        map.put("orderItems", orderItems);
        ResponseResult result = new ResponseResult();
        result.setData(map);
        return result;
    }

    @Override
    public ResponseResult updateOrder(OrderDto orderDto) {
        orderMapper.updateByPrimaryKey(orderDto.getOrder());
        userAddressMapper.updateByPrimaryKey(orderDto.getUserAddress());


        return null;
    }


}
