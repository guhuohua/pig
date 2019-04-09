package com.ch.ch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ch.dao.GoodsSpecificationMapper;
import com.ch.entity.GoodsSolrSchema;
import com.ch.entity.GoodsSpecification;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChApplicationTests {

    @Autowired
    SolrClient solrClient;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;


    @Test
    public void testUploadImage() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", "*:*");
        params.put("start", "1");
        params.put("rows", "1");
        SolrParams mapSolrParams = new MapSolrParams(params);
        try {
            QueryResponse query = solrClient.query(mapSolrParams);
            SolrDocumentList results = query.getResults();
            for (SolrDocument result : results) {
                // SolrDocument 数据结构为Map
                System.out.println(result);
//                GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
//                goodsSolrSchema.setGoodsId((Integer.valueOf((String)result.getFieldValue("goodsId"))));
//                System.out.println(goodsSolrSchema.getGoodsId());
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GoodsSpecification goodsSpecification1 = goodsSpecificationMapper.selectByPrimaryKey(1);
        GoodsSpecification goodsSpecification2 = goodsSpecificationMapper.selectByPrimaryKey(1);
        GoodsSpecification goodsSpecification3 = goodsSpecificationMapper.selectByPrimaryKey(1);
        GoodsSpecification goodsSpecification4 = goodsSpecificationMapper.selectByPrimaryKey(1);
        Map specMap = JSON.parseObject(goodsSpecification1.getAttrs());
        List<String> strings = new ArrayList<>();
        strings.add("HOT");
        GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
        goodsSolrSchema.setGoodsId(234);
        goodsSolrSchema.setGoodsSalesArea(strings);
        goodsSolrSchema.setTitle("测试");
        goodsSolrSchema.setImg("换个手机壳");
        goodsSolrSchema.setShopId(123);
        goodsSolrSchema.setInventory(123);
        goodsSolrSchema.setName("测试商品");
        goodsSolrSchema.setPrice(28.22);
        //goodsSolrSchema.setGoodsSpecification(goodsSpecification1.getAttrs());


        try {
            solrClient.addBean(goodsSolrSchema);
            solrClient.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testUploadImage1(){
        String str = "[{\"attributeName\":\"网络制式\",\"attributeValue\":[\"移动3G\",\"移动4G\"]},{\"attributeName\":\"屏幕尺寸\",\"attributeValue\":[\"6寸\",\"5寸\"]}]";
        Map map = JSON.parseObject(str);
        System.out.println(map);
    }


}
