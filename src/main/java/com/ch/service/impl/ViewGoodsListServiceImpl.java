/**
 * Author: 常富文
 * Date:   2019/4/8 11:22
 * Description: 商品列表实现类
 */

package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.SolrDto;
import com.ch.entity.Goods;
import com.ch.entity.GoodsExample;
import com.ch.service.ViewGoodsListService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewGoodsListServiceImpl implements ViewGoodsListService {
    @Autowired
    SolrClient solrClient;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public ResponseResult findGoodsList(SolrDto solrDto, Integer shopId) {
        //  SysUser sysUser = userMapper.selectByPrimaryKey(userId);
        ResponseResult result1 = new ResponseResult();
        Map params = new HashMap<>();
        String str = null;

        int start = (solrDto.getStart() - 1) * solrDto.getRows();
        if (BeanUtils.isNotEmpty(solrDto.getCategoryId())) {
            String str1 =  "shopId:" + shopId + " AND " +"categoryId:" +solrDto.getCategoryId()+ " AND " +"goodsType:" +solrDto.getGoodsType();
            //str = "\""+"shopId:" + shopId +"\"" + "," + "\""+"categoryId:" +solrDto.getCategoryId()+"\"" ;
           // System.out.println(str1);
            params.put("q","*:*");
            params.put("fq", str1);
            params.put("start", start);
            params.put("rows", solrDto.getRows());
        }
       // System.out.println(params);

        //System.out.println("categoryId :"+solrDto.getCategoryId()+" AND "+"shopId:"+sysUser.getShopId());
        //params.put("q","*:*");

       /* params.put("q", "shopId:" + shopId);
        params.put("start", solrDto.getStart());
        params.put("rows", solrDto.getRows());
        params.put("sort", "sort asc");*/

        if (BeanUtils.isNotEmpty(solrDto.getCondition())) {
            str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
            //System.out.println(str);
            params.put("fq", str);
            params.put("start", start);
            params.put("rows", solrDto.getRows());
            params.put("q", "shopId:" + shopId);
        }
        if ("NEW".equals(solrDto.getCondition())) {
            str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
            //System.out.println(str);
            params.put("fq", str);
            params.put("start", start);
            params.put("rows", solrDto.getRows());
            params.put("sort", "newSort asc");
            params.put("q", "shopId:" + shopId);
        }
        if ("HOT".equals(solrDto.getCondition())) {
            str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
            params.put("fq", str);
            params.put("start", start);
            params.put("rows", solrDto.getRows());
            params.put("sort", "hotSort asc");
            params.put("q", "shopId:" + shopId);
        }
        if ("BOUTIQUE".equals(solrDto.getCondition())) {
            str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
            params.put("fq", str);
            params.put("start", start);
            params.put("rows", solrDto.getRows());
            params.put("sort", "boutiqueSort asc");
            params.put("q", "shopId:" + shopId);
        }
        SolrParams mapSolrParams = new MapSolrParams(params);
        QueryResponse query1 = null;
        try {
            query1 = solrClient.query(mapSolrParams);
            SolrDocumentList results = query1.getResults();
            result1.setData(results);
            // for (SolrDocument result : results) {
            // SolrDocument 数据结构为Map
            //System.out.println(result);
            //GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
            //goodsSolrSchema.setGoodsId((Integer.valueOf((String) result.getFieldValue("goodsId"))));
            //System.out.println(goodsSolrSchema.getGoodsId());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    @Override
    public ResponseResult shouCondition(String condition, Integer shopId) {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%" + condition + "%");
        criteria.andShopIdEqualTo(shopId);
        GoodsExample.Criteria criteria1 = example.createCriteria();
        criteria1.andNameLike("%" + condition + "%");
        example.or(criteria1);
        List<Goods> goods = goodsMapper.selectByExample(example);
        List<String> list = new ArrayList<>();
        for (Goods good : goods) {
            list.add(good.getName());
            list.add(good.getTitle());

        }
        Set set = new HashSet();
        set.addAll(list);
        ResponseResult result = new ResponseResult();
        result.setData(set);
        return result;
    }

}
