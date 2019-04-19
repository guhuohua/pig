package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.OrderMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysOrderParam;
import com.ch.entity.*;
import com.ch.enums.OderStatusEnum;
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
    OrderMapper orderMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ResponseResult list(SysOrderParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        if (BeanUtils.isNotEmpty(param.getName())) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andNicknameLike(param.getName());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
            List<Integer> userIds = new ArrayList<>();
            for (UserInfo info:userInfos) {
                userIds.add(info.getId());
            }
            criteria.andUserIdIn(userIds);
        }
        if (BeanUtils.isNotEmpty(param.getOrderId())) {
            criteria.andIdEqualTo(param.getOrderId());
        }
        if (BeanUtils.isNotEmpty(param.getOrderStatus())) {
            criteria.andOrderStatusEqualTo(param.getOrderStatus());
        }
        if (BeanUtils.isNotEmpty(param.getBeginDate()) && BeanUtils.isNotEmpty(param.getEndDate())) {
            criteria.andCreateDateGreaterThanOrEqualTo(new Date(param.getBeginDate()));
            criteria.andCreateDateLessThanOrEqualTo(new Date(param.getEndDate()));
        }
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<Order> orders = orderMapper.selectByExample(orderExample);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult deliverGoods(DeliverGoodsParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getOrderId());
        List<Order> orders = orderMapper.selectByExample(orderExample);
        if (orders.stream().findFirst().isPresent()) {
            Order order = orders.stream().findFirst().get();
            order.setTrackNumber(param.getExpressCode());
            order.setModifyDate(new Date());
            order.setOrderStatus(Integer.valueOf(OderStatusEnum.SHIPPED.code));
            orderMapper.updateByPrimaryKey(order);
        }
        return result;
    }

    @Override
    public ResponseResult detail(String oderId, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);

        return result;
    }
}
