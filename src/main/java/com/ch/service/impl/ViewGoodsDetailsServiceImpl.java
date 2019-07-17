/**
 * Author: 常富文
 * Date:   2019/4/4 15:50
 * Description: 商品详细页实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.ViewGoodsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewGoodsDetailsServiceImpl implements ViewGoodsDetailsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsEvaluationMapper goodsEvaluationMapper;

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;
    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    GoodsImageMapper goodsImageMapper;

    @Override
    public ResponseResult findGoodsDetailsByGoodsId(Integer goodsId, Integer shopId) {
        //SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        //商品详情的map
        ResponseResult result = new ResponseResult();
        Map goodsDetailsMap = new HashMap();
        //查询商品表
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
       // System.out.println(goods.getShopId());

            goodsDetailsMap.put("goods", goods);
            //查询sku列表
            GoodsSkuExample exampleSku = new GoodsSkuExample();
            GoodsSkuExample.Criteria criteria2 = exampleSku.createCriteria();
            criteria2.andGoodsIdEqualTo(goodsId);
            List<GoodsSku> goodsSkus = goodsSkuMapper.selectByExample(exampleSku);
            goodsDetailsMap.put("goodsSkus", goodsSkus);

           /* GoodsSkuAttributeExample example = new GoodsSkuAttributeExample();
            GoodsSkuAttributeExample.Criteria criteria = example.createCriteria();
            criteria.andGoodsIdEqualTo(goodsId);
            criteria.andShopIdEqualTo(goods.getShopId());
            List<GoodsSkuAttribute> goodsSkuAttributes = goodsSkuAttributeMapper.selectByExample(example);
            goodsDetailsMap.put("goodsSkuAttributes",goodsSkuAttributes);
            //商品规格属性map
            // List goodsArrList = new ArrayList();
            List<GoodsCategory> goodsCategories = new ArrayList<>();
            if (goodsSkuAttributes.size() > 0) {
                List<String> list = new ArrayList<>();
                List<Integer> listInt = new ArrayList();
                Set<String> set = new HashSet<>();
                Set<Integer> setInt = new HashSet<>();
                Specification specification = null;
                for (GoodsSkuAttribute goodsSkuAttribute : goodsSkuAttributes) {
                    Integer specificationId = goodsSkuAttribute.getSpecificationId();
                    specification = specificationMapper.selectByPrimaryKey(specificationId);
                    set.add(specification.getTitle());
                    setInt.add(specification.getId());
                }
                list.addAll(set);
                listInt.addAll(setInt);
                // System.out.println(listInt);
                for (Integer integer : listInt) {
                    GoodsCategory goodsCategory = new GoodsCategory();
                    //查询商品规格表
                    Specification specification1 = specificationMapper.selectByPrimaryKey(integer);
                    goodsCategory.setAttrName(specification1.getTitle());
                    List<SpecificationAttribute> specificationAttributes = null;
                    //查询商品规格属性表
                    SpecificationAttributeExample exampleAttr = new SpecificationAttributeExample();
                    SpecificationAttributeExample.Criteria criteria3 = exampleAttr.createCriteria();
                    criteria3.andSpecificationIdEqualTo(integer);
                    specificationAttributes = specificationAttributeMapper.selectByExample(exampleAttr);
                    //goodsDetailsMap.put("specificationAttributes", specificationAttributes);
                    List<SpecificationAttribute> specNames = new ArrayList<>();
                    for (SpecificationAttribute specAttrs : specificationAttributes) {

                        specNames.add(specAttrs);
                        goodsCategory.setAttrValue(specNames);
                    }

                    goodsCategories.add(goodsCategory);

                }
            }


            goodsDetailsMap.put("goodAttr", goodsCategories);


            //查询商品评价表
            GoodsEvaluationExample example1 = new GoodsEvaluationExample();
            GoodsEvaluationExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andGoodsIdEqualTo(goods.getId());
            List<GoodsEvaluation> goodsEvaluations = goodsEvaluationMapper.selectByExample(example1);

            //查询商品图片表

            GoodsImageExample exampleImg = new GoodsImageExample();
            GoodsImageExample.Criteria criteriaImg = exampleImg.createCriteria();
            criteriaImg.andGoodsIdEqualTo(goodsId);
            List<GoodsImage> goodsImages = goodsImageMapper.selectByExample(exampleImg);
            goodsDetailsMap.put("goodsImages", goodsImages);
            goodsDetailsMap.put("goodsEvaluations", goodsEvaluations);
*/
            //查询商品评价表
            GoodsEvaluationExample example1 = new GoodsEvaluationExample();
            GoodsEvaluationExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andGoodsIdEqualTo(goodsId);
            criteria1.andScoreEqualTo(5);
            List<GoodsEvaluation> goodsEvaluations1 = goodsEvaluationMapper.selectByExample(example1);
            List goodsEvaluations = new ArrayList();
            for (GoodsEvaluation goodsEvaluation : goodsEvaluations1){
                goodsEvaluations.add(goodsEvaluation);

                if (goodsEvaluations.size()>1){
                    break;
                }
            }
            Collections.reverse(goodsEvaluations);


            //查询商品图片表
            GoodsImageExample exampleImg = new GoodsImageExample();
            exampleImg.setOrderByClause("sort asc");
            GoodsImageExample.Criteria criteriaImg = exampleImg.createCriteria();
            criteriaImg.andGoodsIdEqualTo(goodsId);
            List<GoodsImage> goodsImages = goodsImageMapper.selectByExample(exampleImg);
            goodsDetailsMap.put("goodsImages", goodsImages);
            goodsDetailsMap.put("goodsEvaluations", goodsEvaluations);
            goodsDetailsMap.put("goodsImages", goodsImages);

        result.setData(goodsDetailsMap);
        return result;
    }
}
