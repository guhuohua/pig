package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsSpecificationMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.GoodsSpecificationExample;
import com.ch.entity.SysUser;
import com.ch.service.SysGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysGoodsSkuServiceImpl implements SysGoodsSkuService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public ResponseResult list(String name, Integer userId, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        if (BeanUtils.isNotEmpty(name)) {
        }
        goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        return result;
    }
}
