package com.ch.service.impl;

import com.alibaba.fastjson.JSON;
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
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Autowired
    GoodsAreaMapper goodsAreaMapper;

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
        if (null != param.getSale()) {
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
            goods.setRecommend(param.getStatus());
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
        GoodsAreaExample goodsAreaExample = new GoodsAreaExample();
        goodsAreaExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(goodsId);
        List<GoodsArea> goodsAreas = goodsAreaMapper.selectByExample(goodsAreaExample);
        if (goodsAreas.size() > 0) {
            result.setCode(500);
            result.setError("该商品已挂载销售区域，请删除后重试");
            result.setError_description("该商品已挂载销售区域，请删除后重试");
            return result;
        }
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            if (goods.getStatus() == 1) {
                result.setCode(500);
                result.setError("发布中的商品禁止删除，请下架后重试");
                result.setError_description("发布中的商品禁止删除，请下架后重试");
                return result;
            } else {
                goodsMapper.deleteByExample(goodsExample);
            }
        }
        return result;
    }

    @Override
    public ResponseResult skuList(List<Integer> categoryIds, Integer userId, Integer goodsId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        List<GoodsSkuAttribute> goodsSkuAttributes = new ArrayList<>();
        SpecificationExample specificationExample = new SpecificationExample();
        specificationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andCategoryId(categoryIds.get(1));
        List<Specification> specifications = specificationMapper.selectByExample(specificationExample);
        if (BeanUtils.isNotEmpty(goodsId)) {
            GoodsSkuAttributeExample goodsSkuAttributeExample = new GoodsSkuAttributeExample();
            goodsSkuAttributeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(goodsId);
            goodsSkuAttributes = goodsSkuAttributeMapper.selectByExample(goodsSkuAttributeExample);
        }
        List<GoodsSkuListDTO> goodsSkuListDTOList = new ArrayList<>();
        for (Specification specification:specifications) {
            GoodsSkuListDTO goodsSkuListDTO = new GoodsSkuListDTO();
            goodsSkuListDTO.setSpecificationId(specification.getId());
            goodsSkuListDTO.setSpecificationName(specification.getTitle());
            goodsSkuListDTO.setDelFlag(0);
            List<SpecificationAttrDTO> specificationAttrDTOS = new ArrayList<>();
            SpecificationAttributeExample example = new SpecificationAttributeExample();
            example.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSpecificationIdEqualTo(specification.getId());
            List<SpecificationAttribute> specificationAttributes = specificationAttributeMapper.selectByExample(example);
            for (SpecificationAttribute specificationAttribute:specificationAttributes) {
                SpecificationAttrDTO specificationAttrDTO = new SpecificationAttrDTO();
                specificationAttrDTO.setAttrId(specificationAttribute.getId());
                specificationAttrDTO.setAttrName(specificationAttribute.getName());
                specificationAttrDTO.setSpecificationId(specification.getId());
                specificationAttrDTO.setDelFlag(0);
                for (GoodsSkuAttribute goodsSkuAttribute:goodsSkuAttributes) {
                    if (specification.getId() == goodsSkuAttribute.getSpecificationId()) {
                        goodsSkuListDTO.setDelFlag(1);
                    }
                    if (specificationAttribute.getId() == goodsSkuAttribute.getSpecificationAttributeId()) {
                        specificationAttrDTO.setDelFlag(1);
                    }
                }
                specificationAttrDTOS.add(specificationAttrDTO);
            }
            goodsSkuListDTO.setSpecificationAttrDTOList(specificationAttrDTOS);
            goodsSkuListDTOList.add(goodsSkuListDTO);
        }
        result.setData(goodsSkuListDTOList);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult mange(SysGoodsModel model, Integer userId) {
        ResponseResult result = new ResponseResult();
        System.out.println(JSON.toJSON(model));
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        List<SysGoodsSkuModel> sysGoodsSkuModelList = model.getSysGoodsSkuModelList();
        long max = sysGoodsSkuModelList.stream().mapToLong(SysGoodsSkuModel::getPresentPrice).max().getAsLong();
        long min = sysGoodsSkuModelList.stream().mapToLong(SysGoodsSkuModel::getPresentPrice).min().getAsLong();
        if (BeanUtils.isEmpty(model.getId())) {

            StringBuilder sn = new StringBuilder(sysUser.getShopId());
            sn.append(sysUser.getId());
            sn.append(new Date().getTime());
            Goods goods = new Goods();
            modelMapper.map(model, goods);
            goods.setStatus(0);
            goods.setRecommend(0);
            goods.setSn(sn.toString());
            goods.setSale(0);
            goods.setShopId(sysUser.getShopId());
            goods.setCatrgoryId(model.getCategoryIds().get(1));
            goods.setCreateTime(new Date());
            goods.setOriginalPrice(max);
            goods.setPresentPrice(min);
            goods.setSalesVolume(0);
            goodsMapper.insert(goods);
            Integer count = 0;

            for (SysGoodsSkuModel skuModel:sysGoodsSkuModelList) {
                GoodsSku sku = new GoodsSku();
                modelMapper.map(skuModel, sku);
                sku.setCreateTime(new Date());
                sku.setGoodsId(goods.getId());
                sku.setSn(sn.toString());
                sku.setStatus(0);
                sku.setSale(0);
                sku.setShopId(sysUser.getShopId());
                sku.setSkuName(sku.getSkuName());
                sku.setCategoryId(model.getCategoryIds().get(1));
                goodsSkuMapper.insert(sku);
                count += skuModel.getInventory();
            }
            goods.setInventory(count);
            goodsMapper.updateByPrimaryKey(goods);
            for (SysGoodsImageModel imgUrl:model.getGoodsImgList()) {
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setShopId(sysUser.getShopId());
                goodsImage.setStatus(0);
                goodsImage.setUrl(imgUrl.getUrl());
                goodsImage.setCreateTime(new Date());
                goodsImage.setGoodsId(goods.getId());
                goodsImageMapper.insert(goodsImage);
            }
            for (GoodsSkuListDTO goodsSkuListDTO:model.getGoodsSkuListDTOList()) {
                GoodsSkuAttribute goodsSkuAttribute = new GoodsSkuAttribute();
                goodsSkuAttribute.setCreateDate(new Date());
                goodsSkuAttribute.setShopId(sysUser.getShopId());
                goodsSkuAttribute.setGoodsId(goods.getId());
                goodsSkuAttribute.setSpecificationId(goodsSkuListDTO.getSpecificationId());
                Specification specification = specificationMapper.selectByPrimaryKey(goodsSkuListDTO.getSpecificationId());
                specification.setDelFlag(goodsSkuListDTO.getDelFlag());
                specificationMapper.updateByPrimaryKey(specification);
                for (SpecificationAttrDTO specificationAttrDTO:goodsSkuListDTO.getSpecificationAttrDTOList()) {
                    SpecificationAttribute specificationAttribute = specificationAttributeMapper.selectByPrimaryKey(specificationAttrDTO.getAttrId());
                    specificationAttribute.setDelFlag(specificationAttrDTO.getDelFlag());
                    specificationAttributeMapper.updateByPrimaryKey(specificationAttribute);
                    if (goodsSkuListDTO.getDelFlag() == 1 && specificationAttrDTO.getDelFlag() == 1) {
                        goodsSkuAttribute.setSkuId(123);
                        goodsSkuAttribute.setSpecificationAttributeId(specificationAttribute.getId());
                        goodsSkuAttributeMapper.insert(goodsSkuAttribute);
                    }
                }
            }
        } else {
            Goods goods = new Goods();
            Integer count = 0;
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(model.getId());
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            if (goodsList.stream().findFirst().isPresent()) {
                goods = goodsList.stream().findFirst().get();
                goods.setTitle(model.getTitle());
                goods.setName(model.getName());
                goods.setKeyWords(model.getKeyWords());
                goods.setDesc(model.getDesc());
                goods.setUpdateTime(new Date());
                goods.setOriginalPrice(max);
                goods.setPresentPrice(min);
                goods.setCatrgoryId(model.getCategoryIds().get(1));
                goods.setKeyWords(model.getKeyWords());
                goods.setFreight(model.getFreight());
                goods.setUnits(model.getUnits());
                goods.setGoodsImgUrl(model.getGoodsImgUrl());
                goodsMapper.updateByPrimaryKey(goods);
            }



            GoodsImageExample goodsImageExample = new GoodsImageExample();
            goodsImageExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(model.getId());
            goodsImageMapper.deleteByExample(goodsImageExample);


            GoodsSkuAttributeExample goodsSkuAttributeExample = new GoodsSkuAttributeExample();
            goodsSkuAttributeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(model.getId());
            goodsSkuAttributeMapper.deleteByExample(goodsSkuAttributeExample);


            for (GoodsSkuListDTO goodsSkuListDTO:model.getGoodsSkuListDTOList()) {
                GoodsSkuAttribute goodsSkuAttribute = new GoodsSkuAttribute();
                goodsSkuAttribute.setCreateDate(new Date());
                goodsSkuAttribute.setShopId(sysUser.getShopId());
                goodsSkuAttribute.setGoodsId(goods.getId());
                goodsSkuAttribute.setSpecificationId(goodsSkuListDTO.getSpecificationId());
                Specification specification = specificationMapper.selectByPrimaryKey(goodsSkuListDTO.getSpecificationId());
                specification.setDelFlag(goodsSkuListDTO.getDelFlag());
                specificationMapper.updateByPrimaryKey(specification);
                for (SpecificationAttrDTO specificationAttrDTO:goodsSkuListDTO.getSpecificationAttrDTOList()) {
                    SpecificationAttribute specificationAttribute = specificationAttributeMapper.selectByPrimaryKey(specificationAttrDTO.getAttrId());
                    specificationAttribute.setDelFlag(specificationAttrDTO.getDelFlag());
                    specificationAttributeMapper.updateByPrimaryKey(specificationAttribute);
                    if (specificationAttribute.getDelFlag() == 1) {
                        goodsSkuAttribute.setSpecificationAttributeId(specificationAttribute.getId());
                        goodsSkuAttribute.setSkuId(123);
                        goodsSkuAttributeMapper.insert(goodsSkuAttribute);
                    }
                }
            }

            for (SysGoodsSkuModel skuModel:sysGoodsSkuModelList) {
                if (BeanUtils.isNotEmpty(skuModel.getId())) {
                    GoodsSku sku = goodsSkuMapper.selectByPrimaryKey(skuModel.getId());
                    sku.setCreateTime(new Date());
                    sku.setGoodsId(model.getId());
                    sku.setSn(model.getSn());
                    sku.setStatus(0);
                    sku.setShopId(sysUser.getShopId());
                    sku.setSkuName(skuModel.getSkuName());
                    sku.setOriginalPrice(skuModel.getOriginalPrice());
                    sku.setPresentPrice(skuModel.getPresentPrice());
                    sku.setGoodsImage(skuModel.getGoodsImage());
                    sku.setInventory(skuModel.getInventory());
                    goodsSkuMapper.updateByPrimaryKey(sku);
                    count += skuModel.getInventory();
                } else {
                    StringBuilder sn = new StringBuilder(sysUser.getShopId());
                    sn.append(sysUser.getId());
                    sn.append(new Date().getTime());
                    GoodsSku sku = new GoodsSku();
                    modelMapper.map(skuModel, sku);
                    sku.setCreateTime(new Date());
                    sku.setGoodsId(goods.getId());
                    sku.setSn(sn.toString());
                    sku.setStatus(0);
                    sku.setSale(0);
                    sku.setShopId(sysUser.getShopId());
                    sku.setSkuName(sku.getSkuName());
                    sku.setCategoryId(model.getCategoryIds().get(1));
                    goodsSkuMapper.insert(sku);
                    count += skuModel.getInventory();
                }
            }
            goods.setInventory(count);
            goodsMapper.updateByPrimaryKey(goods);
            for (SysGoodsImageModel imgUrl:model.getGoodsImgList()) {
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setShopId(sysUser.getShopId());
                goodsImage.setStatus(0);
                goodsImage.setUrl(imgUrl.getUrl());
                goodsImage.setCreateTime(new Date());
                goodsImage.setGoodsId(model.getId());
                goodsImageMapper.insert(goodsImage);
            }
        }
        return result;
    }

    @Override
    public ResponseResult findById(Integer goodsId, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysGoodsModel sysGoodsModel = new SysGoodsModel();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goodsId).andShopIdEqualTo(sysUser.getShopId());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {

            List<Integer> categoryIds = new ArrayList<>();


            Goods goods = goodsList.stream().findFirst().get();
            GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(goods.getCatrgoryId());
            GoodsType goodsType1 = goodsTypeMapper.selectByPrimaryKey(goodsType.getParentId());
            categoryIds.add(goodsType1.getId());
            categoryIds.add(goodsType.getId());
            modelMapper.map(goods, sysGoodsModel);

            List<SysGoodsImageModel> goodsImageModelList = new ArrayList<>();
            List<SysGoodsSkuModel> sysGoodsSkuModelList = new ArrayList<>();
            sysGoodsModel.setCategoryIds(categoryIds);
            GoodsImageExample goodsImageExample = new GoodsImageExample();
            goodsImageExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(goodsId);
            List<GoodsImage> goodsImages = goodsImageMapper.selectByExample(goodsImageExample);
            for (GoodsImage goodsImage:goodsImages) {
                SysGoodsImageModel sysGoodsImageModel = new SysGoodsImageModel();
                sysGoodsImageModel.setUrl(goodsImage.getUrl());
                goodsImageModelList.add(sysGoodsImageModel);
            }

            GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
            goodsSkuExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andGoodsIdEqualTo(goodsId);
            List<GoodsSku> goodsSkus = goodsSkuMapper.selectByExample(goodsSkuExample);
            for (GoodsSku goodsSku:goodsSkus) {
                SysGoodsSkuModel sysGoodsSkuModel = new SysGoodsSkuModel();
                modelMapper.map(goodsSku, sysGoodsSkuModel);
                sysGoodsSkuModelList.add(sysGoodsSkuModel);
            }
            sysGoodsModel.setGoodsImgList(goodsImageModelList);
            sysGoodsModel.setSysGoodsSkuModelList(sysGoodsSkuModelList);


            result.setData(sysGoodsModel);
        }
        return result;
    }

    @Override
    public ResponseResult deleteSku(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
        goodsSkuExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(id);
        goodsSkuMapper.deleteByExample(goodsSkuExample);
        return result;
    }
}
