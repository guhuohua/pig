package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.GoodsParam;
import com.ch.dto.GoodsSkuListDTO;
import com.ch.dto.SpecificationAttrDTO;
import com.ch.entity.*;
import com.ch.enums.OderStatusEnum;
import com.ch.model.*;
import com.ch.service.SolrService;
import com.ch.service.SysGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    GoodsSkuMapper goodsSkuMapper;

    @Autowired
    SolrService solrService;

    @Autowired
    SpecificationMapper specificationMapper;

    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;

    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;

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
        if (BeanUtils.isNotEmpty(param.getSn())) {
            criteria.andSnEqualTo(param.getSn());
        }
        if (BeanUtils.isNotEmpty(param.getSale())) {
            if (param.getSale() == 2) {
                param.setSale(0);
            }
            criteria.andSaleEqualTo(param.getSale());
        }
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> page = new PageInfo<>(goodsList);
        result.setData(page);
        return result;
    }



    @Override
    @Transactional
    public ResponseResult goodsStatus(GoodsParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            goods.setStatus(param.getStatus());
            goods.setSale(param.getStatus());
            goodsMapper.updateByPrimaryKey(goods);
        }
        if (0 == param.getStatus()) {
            solrService.lowerShelf(param.getId());
        }
        if (1 == param.getStatus()) {
            solrService.releaseGoods(param.getId(), sysUser.getShopId());
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

    @Override
    public ResponseResult skuList(List<Integer> categoryIds, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SpecificationExample specificationExample = new SpecificationExample();
        specificationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andCategoryId(categoryIds.get(1));
        List<Specification> specifications = specificationMapper.selectByExample(specificationExample);
        List<GoodsSkuListDTO> goodsSkuListDTOList = new ArrayList<>();
        for (Specification specification:specifications) {
            GoodsSkuListDTO goodsSkuListDTO = new GoodsSkuListDTO();
            goodsSkuListDTO.setSpecificationId(specification.getId());
            goodsSkuListDTO.setSpecificationName(specification.getTitle());
            List<SpecificationAttrDTO> specificationAttrDTOS = new ArrayList<>();
            SpecificationAttributeExample example = new SpecificationAttributeExample();
            example.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSpecificationIdEqualTo(specification.getId());
            List<SpecificationAttribute> specificationAttributes = specificationAttributeMapper.selectByExample(example);
            for (SpecificationAttribute specificationAttribute:specificationAttributes) {
                SpecificationAttrDTO specificationAttrDTO = new SpecificationAttrDTO();
                specificationAttrDTO.setAttrId(specificationAttribute.getId());
                specificationAttrDTO.setAttrName(specificationAttribute.getName());
                specificationAttrDTOS.add(specificationAttrDTO);
            }
            goodsSkuListDTO.setSpecificationAttrDTOList(specificationAttrDTOS);
        }
        result.setData(goodsSkuListDTOList);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult mange(SysGoodsModel model, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isEmpty(model.getId())) {
            StringBuilder sn = new StringBuilder(sysUser.getShopId());
            sn.append(new Date().getTime());
            Goods goods = new Goods();
            goods.setSn(sn.toString());
            goods.setStatus(0);
            modelMapper.map(model, goods);
            goods.setShopId(sysUser.getShopId());
            goods.setCreateTime(new Date());
            List<SysGoodsSkuModel> sysGoodsSkuModelList = model.getSysGoodsSkuModelList();
            long max = sysGoodsSkuModelList.stream().mapToLong(SysGoodsSkuModel::getPresentPrice).max().getAsLong();
            long min = sysGoodsSkuModelList.stream().mapToLong(SysGoodsSkuModel::getPresentPrice).min().getAsLong();
            goods.setOriginalPrice(BigDecimal.valueOf(max));
            goods.setPresentPrice(BigDecimal.valueOf(min));
            goodsMapper.insert(goods);
            for (SysGoodsSkuModel skuModel:sysGoodsSkuModelList) {
                GoodsSku sku = new GoodsSku();
                modelMapper.map(skuModel, sku);
                sku.setCreateTime(new Date());
                sku.setGoodsId(goods.getId());
                sku.setSn(sn.toString());
                sku.setStatus(0);
                sku.setShopId(sysUser.getShopId());
                sku.setSkuName(sku.getSkuName());
                goodsSkuMapper.insert(sku);
            }
            for (SysGoodsImageModel sysGoodsImageModel:model.getGoodsImageModelList()) {
                GoodsImage goodsImage = new GoodsImage();
                modelMapper.map(sysGoodsImageModel, goodsImage);
                goodsImage.setShopId(sysUser.getShopId());
                goodsImage.setStatus(0);
                goodsImage.setCreateTime(new Date());
                goodsImage.setGoodsId(goods.getId());
                goodsImageMapper.insert(goodsImage);
            }
        } else {
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(model.getId());
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            if (goodsList.stream().findFirst().isPresent()) {
                Goods goods = goodsList.stream().findFirst().get();
                modelMapper.map(model, goods);
                goods.setUpdateTime(new Date());
                goodsMapper.updateByPrimaryKey(goods);
            }

            GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
            goodsSkuExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(model.getId());
            goodsSkuMapper.deleteByExample(goodsSkuExample);


            GoodsImageExample goodsImageExample = new GoodsImageExample();
            goodsImageExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(model.getId());
            goodsImageMapper.deleteByExample(goodsImageExample);


            GoodsSkuAttributeExample goodsSkuAttributeExample = new GoodsSkuAttributeExample();
            goodsSkuAttributeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(model.getId());
            goodsSkuAttributeMapper.deleteByExample(goodsSkuAttributeExample);


            List<SysGoodsSkuModel> sysGoodsSkuModelList = model.getSysGoodsSkuModelList();
            for (SysGoodsSkuModel skuModel:sysGoodsSkuModelList) {
                GoodsSku sku = new GoodsSku();
                modelMapper.map(skuModel, sku);
                sku.setCreateTime(new Date());
                sku.setGoodsId(model.getId());
                sku.setSn(model.getSn());
                sku.setStatus(0);
                sku.setShopId(sysUser.getShopId());
                sku.setSkuName(sku.getSkuName());
                goodsSkuMapper.insert(sku);
            }
            for (SysGoodsImageModel sysGoodsImageModel:model.getGoodsImageModelList()) {
                GoodsImage goodsImage = new GoodsImage();
                modelMapper.map(sysGoodsImageModel, goodsImage);
                goodsImage.setShopId(sysUser.getShopId());
                goodsImage.setStatus(0);
                goodsImage.setCreateTime(new Date());
                goodsImage.setGoodsId(model.getId());
                goodsImageMapper.insert(goodsImage);
            }
        }
        return result;
    }
}
