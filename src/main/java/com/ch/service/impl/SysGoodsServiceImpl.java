package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsImageMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.GoodsParam;
import com.ch.entity.*;
import com.ch.model.*;
import com.ch.service.SolrService;
import com.ch.service.SysGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    SolrService solrService;

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



    @Override
    @Transactional
    public ResponseResult goodsStatus(GoodsParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getGoodsId());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            goods.setRecommend(param.getStatus());
            goodsMapper.updateByPrimaryKey(goods);
        }
        if (0 == param.getStatus()) {
            solrService.lowerShelf(param.getGoodsId());
        }
        if (1 == param.getStatus()) {
            solrService.releaseGoods(param.getGoodsId(), sysUser.getShopId());
        }
        return result;
    }

    @Override
    public ResponseResult deleteGoods(Integer goodsId, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(goodsId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            if (goods.getRecommend() == 1) {
                result.setCode(500);
                result.setError("发布中的商品禁止删除，请下架后重试");
                result.setError_description("发布中的商品禁止删除，请下架后重试");
            } else {
                goodsMapper.deleteByExample(goodsExample);
            }
        }
        return result;
    }
}
