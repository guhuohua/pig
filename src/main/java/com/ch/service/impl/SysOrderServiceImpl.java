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
import com.ch.service.SysMemberService;
import com.ch.service.SysOrderService;
import com.ch.util.FlowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    @Autowired
    BaseIntegralMapper baseIntegralMapper;
    @Autowired
    SysMemberService sysMemberService;


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
            for (OrderItem orderItem : orderItems) {
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
        BaseIntegral baseIntegral = baseIntegralMapper.selectByPrimaryKey(1);
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(oderId);
        if (BeanUtils.isNotEmpty(goodsOrder) && Integer.valueOf(OderStatusEnum.UNRECEIVED.code) == goodsOrder.getOrderStatus()) {
            goodsOrder.setOrderStatus(Integer.valueOf(OderStatusEnum.UNEVALUATED.code));
            goodsOrder.setModifyDate(new Date());
            orderMapper.updateByPrimaryKey(goodsOrder);
            //消费返积分
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(goodsOrder.getUserId());
            OrderItemExample example = new OrderItemExample();
            OrderItemExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(oderId);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
            for (OrderItem orderItem : orderItems) {
                Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                if ("ORDINARY".equals(goods.getGoodsType()) && BeanUtils.isNotEmpty(userInfo.getSuperiorInvitationCode())) {
                    BigDecimal orderPrice = new BigDecimal(orderItem.getNumber() * orderItem.getPrice());
                    BigDecimal pp = new BigDecimal("100.00");
                    BigDecimal divide = orderPrice.divide(pp);
                    double d = baseIntegral.getPerfect();
                    double s = baseIntegral.getSuperintendence();
                    double e = 100;
                    double f = d / e;
                    double z = s /e;
                    BigDecimal multiply = divide.multiply(new BigDecimal(f));
                    BigDecimal multiply1 = divide.multiply(new BigDecimal(z));
                    double v = multiply.doubleValue();
                    double v1 = multiply1.doubleValue();
                    int floor = (int) Math.floor(v);
                    int floors = (int) Math.floor(v1);
                    userInfo.setUseIntegral(userInfo.getUseIntegral() + floor);
                    userInfo.setIntegral(userInfo.getIntegral() + floor);
                    userInfoMapper.updateByPrimaryKey(userInfo);
                    long floor1 = floor;
                    long floors1 = floors;
                    FlowUtil.addFlowTel(floor1, "payment", "INTEGRAL", 0);
                    sysMemberService.synchronizedIntegral(userInfo.getId());
                    UserInfoExample example1 = new UserInfoExample();
                    UserInfoExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andSuperiorInvitationCodeEqualTo(userInfo.getSuperiorInvitationCode());
                    List<UserInfo> userInfos = userInfoMapper.selectByExample(example1);
                    UserInfo userInfo1 = userInfos.get(0);
                    userInfo1.setIntegral(userInfo1.getIntegral() + floors);
                    userInfo1.setUseIntegral(userInfo1.getUseIntegral() + floors);
                    userInfoMapper.updateByPrimaryKey(userInfo);
                    FlowUtil.addFlowTel(floors1, "super", "INTEGRAL", 0);
                    sysMemberService.synchronizedIntegral(userInfo.getId());
                }
            }
        }
        return result;
    }
}
