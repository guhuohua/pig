package com.ch.service.impl;


import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsAreaMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.GoodsAreaParam;
import com.ch.dto.RecommendGoodsDTO;
import com.ch.entity.*;
import com.ch.model.SysRecommendParam;
import com.ch.service.SolrService;
import com.ch.service.SysRecommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRecommendServiceImpl implements SysRecommendService {

    @Autowired
    GoodsAreaMapper goodsAreaMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SolrService solrService;

    @Override
    public ResponseResult list(SysRecommendParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<RecommendGoodsDTO> list = goodsAreaMapper.list(param.getTitle(), param.getRecommend(), param.getStatus(), sysUser.getShopId());
        PageInfo<RecommendGoodsDTO> page = new PageInfo<>(list);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult mange(GoodsAreaParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isEmpty(param.getId())) {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSnEqualTo(param.getSn());
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            if (goodsList.stream().findFirst().isPresent()) {
                Goods goods = goodsList.stream().findFirst().get();
                GoodsArea goodsArea = new GoodsArea();
                goodsArea.setGoodsClassification(param.getRecommend());
                goodsArea.setGoodsId(goods.getId());
                goodsArea.setShopId(sysUser.getShopId());
                goodsArea.setSort(param.getSort());
                goodsArea.setStatus(param.getStatus());
                goodsAreaMapper.insert(goodsArea);
                solrService.lowerShelf(goodsArea.getGoodsId());
                solrService.releaseGoods(goodsArea.getGoodsId(), sysUser.getShopId());
            }
        } else {
            GoodsAreaExample goodsAreaExample = new GoodsAreaExample();
            goodsAreaExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
            List<GoodsArea> goodsAreaList = goodsAreaMapper.selectByExample(goodsAreaExample);
            if (goodsAreaList.stream().findFirst().isPresent()) {
                GoodsArea goodsArea = goodsAreaList.stream().findFirst().get();
                GoodsExample goodsExample = new GoodsExample();
                goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSnEqualTo(param.getSn());
                List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                if (goodsList.stream().findFirst().isPresent()) {
                    Goods goods = goodsList.stream().findFirst().get();
                    goodsArea.setGoodsId(goods.getId());
                    goodsArea.setStatus(param.getStatus());
                    goodsArea.setSort(param.getSort());
                    goodsArea.setGoodsClassification(param.getRecommend());
                    goodsAreaMapper.updateByPrimaryKey(goodsArea);
                    solrService.lowerShelf(goodsAreaList.stream().findFirst().get().getGoodsId());
                    solrService.releaseGoods(goodsArea.getGoodsId(), sysUser.getShopId());
                }
            }
        }
        return result;
    }

    @Override
    public ResponseResult status(GoodsAreaParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsAreaExample goodsAreaExample = new GoodsAreaExample();
        goodsAreaExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getGoodsAreaId());
        List<GoodsArea> goodsAreaList = goodsAreaMapper.selectByExample(goodsAreaExample);
        if (goodsAreaList.stream().findFirst().isPresent()) {
            if (param.getStatus() == 0) {
                solrService.lowerShelf(goodsAreaList.stream().findFirst().get().getGoodsId());
            }
            GoodsArea goodsArea = goodsAreaList.stream().findFirst().get();
            goodsArea.setStatus(param.getStatus());
            goodsAreaMapper.updateByPrimaryKey(goodsArea);
            solrService.releaseGoods(goodsArea.getGoodsId(), sysUser.getShopId());
        }
        return result;
    }

    @Override
    public ResponseResult delete(GoodsAreaParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsAreaExample goodsAreaExample = new GoodsAreaExample();
        goodsAreaExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
        List<GoodsArea> goodsAreaList = goodsAreaMapper.selectByExample(goodsAreaExample);
        if (goodsAreaList.stream().findFirst().isPresent()) {
            GoodsArea goodsArea = goodsAreaList.stream().findFirst().get();
            if (goodsArea.getStatus() == 1) {
                result.setCode(500);
                result.setError("启用中的状态不允许删除");
                result.setError_description("启用中的状态不允许删除");
            } else {
                goodsAreaMapper.deleteByPrimaryKey(param.getId());
            }
        }
        return result;
    }
}
