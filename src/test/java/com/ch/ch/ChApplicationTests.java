package com.ch.ch;

import com.ch.entity.GoodsSolrSchema;
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


    @Test
    public void testUploadImage(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", "title:测试");
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

//        List<String> strings = new ArrayList<>();
//        strings.add("HOT");
//        GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
//        goodsSolrSchema.setGoodsId(234);
//        goodsSolrSchema.setGoodsSalesArea(strings);
//        goodsSolrSchema.setTitle("测试");
//        goodsSolrSchema.setImg("换个手机壳");
//        goodsSolrSchema.setShopId(123);
//        goodsSolrSchema.setInventory(123);
//        goodsSolrSchema.setName("测试商品");
//        goodsSolrSchema.setPrice(28.22);
//        try {
//            solrClient.addBean(goodsSolrSchema);
//            solrClient.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        }

    }


}
