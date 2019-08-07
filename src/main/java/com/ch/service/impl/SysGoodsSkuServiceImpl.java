package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
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

import java.util.*;

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

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

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
//        if (param.getParamList().size() > 1) {
//            result.setCode(600);
//            result.setError("长度最多为2，请删除后重试");
//            result.setError_description("长度最多为2，请删除后重试");
//            return result;
//        }
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isEmpty(param.getId())) {
            Specification specification = new Specification();
            specification.setCreateTime(new Date());
            specification.setShopId(sysUser.getShopId());
            specification.setSort(param.getSort());
            specification.setTitle(param.getTitle());
            specification.setStatus(0);
            specification.setDelFlag(0);
            specification.setCategoryId(param.getCategoryIds().get(1));
            specificationMapper.insert(specification);
            for (SysGoodsSkuValueParam goodsSkuValueParam:param.getParamList()) {
                SpecificationAttribute specificationAttribute = new SpecificationAttribute();
                specificationAttribute.setCreateTime(new Date());
                specificationAttribute.setName(goodsSkuValueParam.getName());
                specificationAttribute.setShopId(sysUser.getShopId());
                specificationAttribute.setSort(param.getSort());
                specificationAttribute.setSpecificationId(specification.getId());
                specificationAttribute.setStatus(0);
                specificationAttribute.setDelFlag(0);
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
                specification.setCategoryId(param.getCategoryIds().get(1));
                specificationMapper.updateByPrimaryKey(specification);
            }
            for (SysGoodsSkuValueParam goodsSkuValueParam:param.getParamList()) {
                if (BeanUtils.isNotEmpty(goodsSkuValueParam.getId())) {
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
                } else {
                    SpecificationAttribute specificationAttribute = new SpecificationAttribute();
                    specificationAttribute.setCreateTime(new Date());
                    specificationAttribute.setName(goodsSkuValueParam.getName());
                    specificationAttribute.setShopId(sysUser.getShopId());
                    specificationAttribute.setSort(param.getSort());
                    specificationAttribute.setSpecificationId(param.getId());
                    specificationAttribute.setStatus(0);
                    specificationAttributeMapper.insert(specificationAttribute);
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

    @Override
    public ResponseResult deleteSpecificationAttribute(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SpecificationAttributeExample example = new SpecificationAttributeExample();
        example.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(id);
        specificationAttributeMapper.deleteByExample(example);
        return result;
    }

    @Override
    public ResponseResult goodsClassification(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andStatusEqualTo(1);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
        List<GoodsType> rootMenu = new ArrayList<GoodsType>();
        for (GoodsType nav : goodsTypes) {
            if (nav.getParentId() == 0) {
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (GoodsType nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<GoodsType> childList = getChild(nav.getId(), goodsTypes);
            nav.setChildren(childList);//给根节点设置子节点
        }
        result.setData(rootMenu);
        return result;
    }

    public List<GoodsType> getChild(Integer id, List<GoodsType> allMenu) {
        //子菜单
        List<GoodsType> childList = new ArrayList<GoodsType>();
        for (GoodsType nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (GoodsType nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        Collections.sort(childList, order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    public Comparator<GoodsType> order() {
        Comparator<GoodsType> comparator = new Comparator<GoodsType>() {
            @Override
            public int compare(GoodsType o1, GoodsType o2) {
                if (o1.getSort() != o2.getSort()) {
                    return o1.getSort() - o2.getSort();
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public ResponseResult findById(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysGoodsSkuParam sysGoodsSkuParam = new SysGoodsSkuParam();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SpecificationExample specificationExample = new SpecificationExample();
        specificationExample.createCriteria().andIdEqualTo(id).andShopIdEqualTo(sysUser.getShopId());
        List<Specification> specifications = specificationMapper.selectByExample(specificationExample);
        if (specifications.stream().findFirst().isPresent()) {
            Specification specification = specifications.stream().findFirst().get();
            sysGoodsSkuParam.setId(specification.getId());
            List<Integer> ids = new ArrayList<>();
            List<SysGoodsSkuValueParam> sysGoodsSkuValueParams = new ArrayList<>();

            GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(specification.getCategoryId());
            GoodsType goodsType1 = goodsTypeMapper.selectByPrimaryKey(goodsType.getParentId());
            ids.add(goodsType1.getId());
            ids.add(goodsType.getId());

            sysGoodsSkuParam.setCategoryIds(ids);
            sysGoodsSkuParam.setSort(specification.getSort());
            sysGoodsSkuParam.setTitle(specification.getTitle());


            SpecificationAttributeExample specificationAttributeExample = new SpecificationAttributeExample();
            specificationAttributeExample.createCriteria().andSpecificationIdEqualTo(id);
            List<SpecificationAttribute> specificationAttributes = specificationAttributeMapper.selectByExample(specificationAttributeExample);
            if (BeanUtils.isNotEmpty(specificationAttributes) && specificationAttributes.size() > 0) {
                for (SpecificationAttribute specificationAttribute:specificationAttributes) {
                    SysGoodsSkuValueParam sysGoodsSkuValueParam = new SysGoodsSkuValueParam();
                    sysGoodsSkuValueParam.setId(specificationAttribute.getId());
                    sysGoodsSkuValueParam.setName(specificationAttribute.getName());
                    sysGoodsSkuValueParam.setSort(specificationAttribute.getSort());
                    sysGoodsSkuValueParams.add(sysGoodsSkuValueParam);
                }
            }

            sysGoodsSkuParam.setParamList(sysGoodsSkuValueParams);
        }
        result.setData(sysGoodsSkuParam);
        return result;
    }
}
