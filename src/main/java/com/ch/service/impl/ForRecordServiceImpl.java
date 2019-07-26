package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.ForRecordMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsSkuMapper;
import com.ch.entity.*;
import com.ch.service.ForRecordService;
import com.ch.service.ViewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResponseResult list(String openId) {
        ResponseResult result = new ResponseResult();
        Map map = new HashMap();
        UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
        ForRecordExample example = new ForRecordExample();
        ForRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        List<ForRecord> forRecords = forRecordMapper.selectByExample(example);
        if (forRecords.size()>0){
            for (ForRecord forRecord : forRecords) {
                map.put("forRecord",forRecord);
                Goods goods = goodsMapper.selectByPrimaryKey(forRecord.getGoosId());
                map.put("goods",goods);
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(forRecord.getId());
                map.put("goodsSku",goodsSku);
            }



        }
        result.setData(map);
        return result;
    }
}
