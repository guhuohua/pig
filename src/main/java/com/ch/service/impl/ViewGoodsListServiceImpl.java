/**
 * Author: 常富文
 * Date:   2019/4/8 11:22
 * Description: 商品列表实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.SysUserMapper;
import com.ch.dto.SolrDto;
import com.ch.entity.SysUser;
import com.ch.service.ViewGoodsListService;
import io.swagger.models.auth.In;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViewGoodsListServiceImpl implements ViewGoodsListService {
    @Autowired
    SolrClient solrClient;
    @Autowired
    SysUserMapper userMapper;

    @Override
    public ResponseResult findGoodsList(SolrDto solrDto, Integer userId) {
        SysUser sysUser = userMapper.selectByPrimaryKey(userId);
        ResponseResult result1 = new ResponseResult();
        Map params = new HashMap<>();
        String str = null;

        params.put("q", "shopId :" + sysUser.getShopId() );
        params.put("q", "status : 1");
        params.put("start", solrDto.getStart());
        params.put("rows", solrDto.getRows());
        params.put("sort", "sort asc");
        if (solrDto.getCondition() != null) {
            if ("新品".equals(solrDto.getCondition())) {
                str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
                params.put("fq", str);
                params.remove("sort");
                params.put("sort", "newSort asc");
            }
            if ("热品".equals(solrDto.getCondition())) {
                str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
                params.put("fq", str);
                params.remove("sort");
                params.put("sort", "hotSort asc");
            }
            if ("精品".equals(solrDto.getCondition())) {
                str = "goodsSalesArea:" + "\"" + solrDto.getCondition() + "\"" + " OR name:" + "\"" + solrDto.getCondition() + "\"" + " OR title:" + "\"" + solrDto.getCondition() + "\"";
                params.put("fq", str);
                params.remove("sort");
                params.put("sort", "boutiqueSort asc");
            }
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
}
