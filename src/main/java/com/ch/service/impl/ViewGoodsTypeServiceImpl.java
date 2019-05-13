/**
 * Author: 常富文
 * Date:   2019/4/15 18:23
 * Description: 类目
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsTypeMapper;
import com.ch.entity.GoodsType;
import com.ch.entity.GoodsTypeExample;
import com.ch.service.ViewGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ViewGoodsTypeServiceImpl implements ViewGoodsTypeService {

    @Autowired
    GoodsTypeMapper goodsTypeMapper;


    @Override
    public ResponseResult findTree(Integer shopId) {
        ResponseResult result = new ResponseResult();

        try {//查询所有菜单

            GoodsTypeExample example = new GoodsTypeExample();
            GoodsTypeExample.Criteria criteria = example.createCriteria();
            criteria.andShopIdEqualTo(shopId);
            criteria.andStatusEqualTo(1);
            List<GoodsType> allMenu = goodsTypeMapper.selectByExample(example);
            //根节点
            List<GoodsType> rootMenu = new ArrayList<GoodsType>();
            for (GoodsType nav : allMenu) {
                if (nav.getParentId() == 0) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (GoodsType nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<GoodsType> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }
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
            return new ArrayList<GoodsType>();
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
}
