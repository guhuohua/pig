/**
 * Author: 常富文
 * Date:   2019/4/26 10:55
 * Description: 售后
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsOrderMapper;
import com.ch.dao.OrderItemMapper;
import com.ch.dao.OrderRefundMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.GoodsOrder;
import com.ch.entity.OrderRefund;
import com.ch.entity.UserInfo;
import com.ch.entity.UserInfoExample;
import com.ch.service.ViewOrderRefundService;
import com.ch.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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


    @Override
    public ResponseResult addOrderRefund(OrderRefund orderRefund, String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
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
        orderRefund.setRefundStatus(0);
        orderRefundMapper.insert(orderRefund);
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderRefund.getOrderId());
        goodsOrder.setRefundId(orderRefund.getId());
        goodsOrderMapper.updateByPrimaryKey(goodsOrder);
        return result;
    }
}
