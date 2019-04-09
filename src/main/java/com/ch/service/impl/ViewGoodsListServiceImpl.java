/**
 * Author: 常富文
 * Date:   2019/4/8 11:22
 * Description: 商品列表实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dto.SolrDto;
import com.ch.entity.GoodsSolrSchema;
import com.ch.service.ViewGoodsListService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViewGoodsListServiceImpl implements ViewGoodsListService {
    @Autowired
    SolrClient solrClient;

    @Override
    public ResponseResult findGoodsList(SolrDto solrDto) {
        ResponseResult result1 = new ResponseResult();
        Map params = new HashMap<>();
        String str = null;
        str = "goodsSalesArea:"+"\""+solrDto.getCondition()+"\""+" OR name:"+"\""+solrDto.getCondition()+"\""+" OR title:"+"\""+solrDto.getCondition()+"\"";
        params.put("q", "*:*");
        params.put("start", solrDto.getStart());
        params.put("rows", solrDto.getRows());
        params.put("fq",str);

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
