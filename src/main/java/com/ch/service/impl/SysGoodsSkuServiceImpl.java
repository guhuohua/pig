package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.SpecificationAttributeMapper;
import com.ch.dao.SpecificationMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.*;
import com.ch.model.SpecificationModel;
import com.ch.model.SysGoodsSkuParam;
import com.ch.model.SysGoodsSkuValueParam;
import com.ch.service.SysGoodsSkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysGoodsSkuServiceImpl implements SysGoodsSkuService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Autowired
    SpecificationMapper specificationMapper;

    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;

    @Override
    public ResponseResult list(String name, Integer userId, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(currentPage, pageSize);
        List<SpecificationModel> specificationMapperPage = specificationMapper.findPage(name, sysUser.getShopId());
        PageInfo<SpecificationModel> pageInfo = new PageInfo<>(specificationMapperPage);
        for (SpecificationModel specificationModel:pageInfo.getList()) {
            SpecificationAttributeExample attributeExample = new SpecificationAttributeExample();
            attributeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSpecificationIdEqualTo(specificationModel.getId());
            List<SpecificationAttribute> specificationAttributes = specificationAttributeMapper.selectByExample(attributeExample);
            StringBuilder stringBuilder = new StringBuilder();
            for (SpecificationAttribute specificationAttribute:specificationAttributes) {
                stringBuilder.append(specificationAttribute.getName());
                stringBuilder.append(",");
            }
            specificationModel.setValues(stringBuilder.toString());
        }
        result.setData(pageInfo);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult mange(SysGoodsSkuParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isEmpty(param.getId())) {
            Specification specification = new Specification();
            specification.setCreateTime(new Date());
            specification.setShopId(sysUser.getShopId());
            specification.setSort(param.getSort());
            specification.setTitle(param.getTitle());
            specification.setStatus(0);
            specification.setCategoryId(param.getCategoryId());
            specificationMapper.insert(specification);
            for (SysGoodsSkuValueParam goodsSkuValueParam:param.getParamList()) {
                SpecificationAttribute specificationAttribute = new SpecificationAttribute();
                specificationAttribute.setCreateTime(new Date());
                specificationAttribute.setName(goodsSkuValueParam.getName());
                specificationAttribute.setShopId(sysUser.getShopId());
                specificationAttribute.setSort(param.getSort());
                specificationAttribute.setSpecificationId(specification.getId());
                specificationAttribute.setStatus(0);
                specificationAttributeMapper.insert(specificationAttribute);
            }
        } else {
            SpecificationExample specificationExample  = new SpecificationExample();
            specificationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
            List<Specification> specifications = specificationMapper.selectByExample(specificationExample);
            if (specifications.stream().findFirst().isPresent()) {
                Specification specification = specifications.stream().findFirst().get();
                specification.setTitle(param.getTitle());
                specification.setSort(param.getSort());
                specification.setUpdateTime(new Date());
                specification.setCategoryId(param.getCategoryId());
                specificationMapper.updateByPrimaryKey(specification);
            }
            for (SysGoodsSkuValueParam goodsSkuValueParam:param.getParamList()) {
                SpecificationAttributeExample example = new SpecificationAttributeExample();
                example.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(goodsSkuValueParam.getId());
                List<SpecificationAttribute> specificationAttributes = specificationAttributeMapper.selectByExample(example);
                if (specificationAttributes.stream().findFirst().isPresent()) {
                    SpecificationAttribute specificationAttribute = specificationAttributes.stream().findFirst().get();
                    specificationAttribute.setName(goodsSkuValueParam.getName());
                    specificationAttribute.setSort(goodsSkuValueParam.getSort());
                    specificationAttribute.setUpdateTime(new Date());
                    specificationAttributeMapper.updateByPrimaryKey(specificationAttribute);
                }
            }
        }
        return result;
    }


    @Override
    @Transactional
    public ResponseResult delete(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SpecificationExample specificationExample  = new SpecificationExample();
        specificationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(id);
        specificationMapper.deleteByExample(specificationExample);
        SpecificationAttributeExample example = new SpecificationAttributeExample();
        example.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andSpecificationIdEqualTo(id);
        specificationAttributeMapper.deleteByExample(example);
        return result;
    }
}
