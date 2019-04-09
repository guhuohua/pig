package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsImageMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.*;
import com.ch.model.SysGoodsListModel;
import com.ch.model.SysGoodsParam;
import com.ch.service.SysGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysGoodsServiceImpl implements SysGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsImageMapper goodsImageMapper;

    @Override
    public ResponseResult goodsList(SysGoodsParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        if (BeanUtils.isNotEmpty(param.getName())) {
            criteria.andNameLike(param.getName());
        }
        if (BeanUtils.isNotEmpty(param.getTitle())) {
            criteria.andTitleLike(param.getTitle());
        }
        if (BeanUtils.isNotEmpty(param.getRecommend())) {
            criteria.andRecommendEqualTo(param.getRecommend());
        }
        if (BeanUtils.isNotEmpty(param.getSale())) {
            criteria.andSaleEqualTo(param.getSale());
        }
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> page = new PageInfo<>(goodsList);
        for (Goods goods:page.getList()) {
            GoodsImageExample goodsImageExample = new GoodsImageExample();
            goodsImageExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(goods.getId());
            GoodsImage goodsImage = goodsImageMapper.selectByExample(goodsImageExample).stream().findFirst().get();
            goods.setGoodsImgUrl(goodsImage.getUrl());
        }
        result.setData(page);
        return result;
    }
}
