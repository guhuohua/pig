package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsTypeMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.*;
import com.ch.model.SysCategoryParam;
import com.ch.service.SysGoodsCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysGoodsCategoryServiceImpl implements SysGoodsCategoryService {


    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public ResponseResult list(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        GoodsTypeExample.Criteria criteria = goodsTypeExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        criteria.andStatusEqualTo(1);
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

    private List<GoodsType> getChild(Integer id, List<GoodsType> allMenu) {
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
    public ResponseResult mange(GoodsType goodsType, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isEmpty(goodsType.getId())) {
            goodsType.setCreateTime(new Date());
            goodsType.setShopId(sysUser.getShopId());
            goodsTypeMapper.insert(goodsType);
        } else {
            goodsType.setUpdateTime(new Date());
            goodsTypeMapper.updateByPrimaryKey(goodsType);
        }
        return result;
    }

    @Override
    public ResponseResult delete(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andCatrgoryIdEqualTo(id);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.size() > 0) {
            result.setCode(500);
            result.setError("该类目下存在商品，不允许删除");
            result.setError_description("该类目下存在商品，不允许删除");
            return result;
        }
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(id);

        goodsTypeMapper.deleteByExample(goodsTypeExample);
        return result;
    }

    @Override
    public ResponseResult updateStatus(SysCategoryParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(param.getId());
        if (BeanUtils.isNotEmpty(goodsType)) {
            goodsType.setStatus(param.getStatus());
            goodsType.setUpdateTime(new Date());
            goodsTypeMapper.updateByPrimaryKey(goodsType);
        }
        return result;
    }

    @Override
    public ResponseResult findOneCategory(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andParentIdEqualTo(0).andStatusEqualTo(1);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
        result.setData(goodsTypes);
        return result;
    }

    @Override
    public ResponseResult findById(Integer id, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(id);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
        if (goodsTypes.stream().findFirst().isPresent()) {
            result.setData(goodsTypes.stream().findFirst().get());
        }
        return result;
    }
}
