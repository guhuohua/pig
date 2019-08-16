package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.ForRecordService;
import com.ch.service.ViewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForRecordServiceImpl implements ForRecordService {

    @Autowired
    ForRecordMapper forRecordMapper;
    @Autowired
    ViewUserInfoService viewUserInfoService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public ResponseResult list(String openId) {
        ResponseResult result = new ResponseResult();
        List list = new ArrayList();
        UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
        ForRecordExample example = new ForRecordExample();
        ForRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        List<ForRecord> forRecords = forRecordMapper.selectByExample(example);
        if (forRecords.size() > 0) {
            for (ForRecord forRecord : forRecords) {
                forRecord.setFormartCreateTime(forRecord.getCreateTime().getTime());
                Map map = new HashMap();
                Goods goods = goodsMapper.selectByPrimaryKey(forRecord.getGoosId());
                if (BeanUtils.isNotEmpty(goods)) {
                    map.put("goods", goods);
                    map.put("forRecord", forRecord);
                    GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(forRecord.getGoodsSkuId());
                    map.put("goodsSku", goodsSku);
                    result.setData(map);
                    list.add(map);
                }
            }
        }
        Collections.reverse(list);
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult add(String orderId) {
        ResponseResult result = new ResponseResult();
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(goodsOrder.getId());
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        for (OrderItem orderItem : orderItems) {
            ForRecord forRecord = new ForRecord();
            forRecord.setCreateTime(new Date());
            forRecord.setGoosId(orderItem.getGoodsId());
            forRecord.setGoodsSkuId(orderItem.getSkuAttrId());
            forRecord.setUserId(goodsOrder.getUserId());
            forRecordMapper.insert(forRecord);
        }
        return result;
    }
}
