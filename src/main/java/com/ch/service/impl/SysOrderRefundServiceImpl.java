package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.OrderRefundMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.SysOrderRefundDTO;
import com.ch.dto.SysOrderRefundParam;
import com.ch.entity.OrderRefund;
import com.ch.entity.OrderRefundExample;
import com.ch.entity.SysUser;
import com.ch.model.SysRefundThroughParam;
import com.ch.service.SysOrderRefundService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysOrderRefundServiceImpl implements SysOrderRefundService {

    @Autowired
    OrderRefundMapper orderRefundMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public ResponseResult list(SysOrderRefundParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<SysOrderRefundDTO> list = orderRefundMapper.list(sysUser.getShopId(), param.getName(), param.getRefundId(), param.getRefundStatus());
        PageInfo<SysOrderRefundDTO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult refundHandle(SysRefundThroughParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        OrderRefundExample orderRefundExample = new OrderRefundExample();
        orderRefundExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getRefundId());
        List<OrderRefund> orderRefunds = orderRefundMapper.selectByExample(orderRefundExample);
        if (orderRefunds.stream().findFirst().isPresent()) {
            OrderRefund orderRefund = orderRefunds.stream().findFirst().get();
            if (param.getRefundStatus() == 2) {
                orderRefund.setRefundStatus(2);
                orderRefund.setRealPrice(param.getPrice());
                orderRefund.setModifyDate(new Date());
                // 调用微信退款


                orderRefundMapper.updateByPrimaryKey(orderRefund);
            }
            if (param.getRefundStatus() == 3) {
                orderRefund.setRefundStatus(3);
                orderRefund.setModifyDate(new Date());
                orderRefund.setRefuseReason(param.getRefuse());
                orderRefundMapper.updateByPrimaryKey(orderRefund);
            }
        }
        return result;
    }
}
