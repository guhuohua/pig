package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysDeliveryInfoDTO;
import com.ch.dto.SysOrderDetailDTO;
import com.ch.dto.SysOrderParam;
import com.ch.entity.*;
import com.ch.enums.OderStatusEnum;
import com.ch.handler.ActiveMQHandler;
import com.ch.service.SysOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysOrderServiceImpl implements SysOrderService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsOrderMapper orderMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Autowired
    OrderRefundMapper orderRefundMapper;

    @Autowired
    ActiveMQHandler activeMQHandler;


    @Override
    public ResponseResult list(SysOrderParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<GoodsOrder> orders = userInfoMapper.orderList(param, sysUser.getShopId());
        PageInfo<GoodsOrder> pageInfo = new PageInfo<>(orders);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult deliverGoods(DeliverGoodsParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsOrderExample orderExample = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getOrderId());
        List<GoodsOrder> orders = orderMapper.selectByExample(orderExample);
        if (orders.stream().findFirst().isPresent()) {
            GoodsOrder order = orders.stream().findFirst().get();
            order.setTrackNumber(param.getExpressCode());
            order.setModifyDate(new Date());
            order.setOrderStatus(Integer.valueOf(OderStatusEnum.UNRECEIVED.code));
            order.setTakeDate(new Date());
            orderMapper.updateByPrimaryKey(order);
            activeMQHandler.delivery("delivery", order.getId());
        }
        return result;
    }

    @Override
    public ResponseResult detail(String oderId, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysOrderDetailDTO sysOrderDetailDTO = new SysOrderDetailDTO();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsOrderExample orderExample = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(oderId);
        List<GoodsOrder> orders = orderMapper.selectByExample(orderExample);
        if (orders.stream().findFirst().isPresent()) {
            GoodsOrder order = orders.stream().findFirst().get();
            sysOrderDetailDTO.setOrderId(order.getId());
            StringBuilder sb = new StringBuilder();
            if (BeanUtils.isNotEmpty(order.getDeliveryId())) {
                UserAddressExample userAddressExample = new UserAddressExample();
                userAddressExample.createCriteria().andIdEqualTo(order.getDeliveryId()).andShopIdEqualTo(sysUser.getShopId());
                List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
                if (userAddresses.stream().findFirst().isPresent()) {
                    UserAddress userAddress = userAddresses.stream().findFirst().get();
                    sb.append(userAddress.getProvince()).append("-").append(userAddress.getCity()).append("-").append(userAddress.getCity()).append(" ").append(userAddress.getAddress());
                    sysOrderDetailDTO.setDeliveryName(userAddress.getName());
                    sysOrderDetailDTO.setPhone(userAddress.getTel());
                }
            }
            if (BeanUtils.isNotEmpty(order.getRefundId())) {
                OrderRefundExample orderRefundExample = new OrderRefundExample();
                orderRefundExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(order.getRefundId());
                List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(orderRefundExample);
                if (orderRefunds.stream().findFirst().isPresent()) {
                    OrderRefund orderRefund = orderRefunds.stream().findFirst().get();
                    sysOrderDetailDTO.setRefundId(orderRefund.getId());
                    sysOrderDetailDTO.setRefundDate(orderRefund.getCreateDate());
                    sysOrderDetailDTO.setPhoto(orderRefund.getPhotos());
                    sysOrderDetailDTO.setRefundPrice(orderRefund.getPrice());
                    sysOrderDetailDTO.setRealPrice(orderRefund.getRealPrice());
                    sysOrderDetailDTO.setRefundNumber(orderRefund.getNumber());
                    sysOrderDetailDTO.setRefundReason(orderRefund.getRemarks());
                }
            }

            sysOrderDetailDTO.setAddress(sb.toString());
            sysOrderDetailDTO.setOrderStatus(order.getOrderStatus());
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(order.getUserId());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
            if (userInfos.stream().findFirst().isPresent()) {
                UserInfo info = userInfos.stream().findFirst().get();
                sysOrderDetailDTO.setUserName(info.getNickname());
            }
            if (order.getOrderStatus() != 1) {
                sysOrderDetailDTO.setPayName("微信支付");
                sysOrderDetailDTO.setTotal(order.getOrderPrice());
                sysOrderDetailDTO.setPayDate(order.getPayDate());
            }
            List<SysDeliveryInfoDTO> infoDTOS = new ArrayList<>();
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andOrderIdEqualTo(order.getId());
            List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
            for (OrderItem orderItem:orderItems) {
                SysDeliveryInfoDTO sysDeliveryInfoDTO = new SysDeliveryInfoDTO();
                sysDeliveryInfoDTO.setGoodsId(orderItem.getGoodsId());
                sysDeliveryInfoDTO.setGoodsName(orderItem.getGoodsName());
                sysDeliveryInfoDTO.setNum(orderItem.getNumber());
                sysDeliveryInfoDTO.setPrice(orderItem.getPrice());
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andIdEqualTo(orderItem.getGoodsId()).andShopIdEqualTo(sysUser.getShopId());
                List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                if (goodsList.stream().findFirst().isPresent()) {
                    Goods goods = goodsList.stream().findFirst().get();
                    sysDeliveryInfoDTO.setPic(goods.getGoodsImgUrl());
                }
                GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
                goodsSkuExample.createCriteria().andIdEqualTo(orderItem.getSkuAttrId()).andShopIdEqualTo(sysUser.getShopId());
                List<GoodsSku> goodsSkus = goodsSkuMapper.selectByExample(goodsSkuExample);
                if (goodsSkus.stream().findFirst().isPresent()) {
                    GoodsSku goodsSku = goodsSkus.stream().findFirst().get();
                    sysDeliveryInfoDTO.setSkuCode(goodsSku.getSn());
                    sysDeliveryInfoDTO.setSkuName(goodsSku.getSkuName());
                }
                infoDTOS.add(sysDeliveryInfoDTO);
            }
            sysOrderDetailDTO.setInfoDTOS(infoDTOS);
        }
        result.setData(sysOrderDetailDTO);
        return result;
    }

    @Override
    public ResponseResult cancelOrder(String oderId) {
        ResponseResult result = new ResponseResult();
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(oderId);
        if (BeanUtils.isNotEmpty(goodsOrder) && Integer.valueOf(OderStatusEnum.UNPAID.code) == goodsOrder.getOrderStatus()) {
            goodsOrder.setOrderStatus(Integer.valueOf(OderStatusEnum.CANCEL.code));
            goodsOrder.setModifyDate(new Date());
            orderMapper.updateByPrimaryKey(goodsOrder);
        }
        return result;
    }

    @Override
    public ResponseResult deliver(String oderId) {
        ResponseResult result = new ResponseResult();
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(oderId);
        if (BeanUtils.isNotEmpty(goodsOrder) && Integer.valueOf(OderStatusEnum.UNRECEIVED.code) == goodsOrder.getOrderStatus()) {
            goodsOrder.setOrderStatus(Integer.valueOf(OderStatusEnum.UNEVALUATED.code));
            goodsOrder.setModifyDate(new Date());
            orderMapper.updateByPrimaryKey(goodsOrder);
        }
        return result;
    }
}
