package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsSkuMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.GoodsSkuExample;
import com.ch.entity.SysUser;
import com.ch.service.SysGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysGoodsSkuServiceImpl implements SysGoodsSkuService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Override
    public ResponseResult list(String name, Integer userId, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        GoodsSkuExample.Criteria criteria = goodsSkuExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        if (BeanUtils.isNotEmpty(name)) {
        }
        goodsSkuMapper.selectByExample(goodsSkuExample);
        return result;
    }
}
