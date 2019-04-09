package com.ch.ch;

import com.ch.entity.GoodsSolrSchema;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChApplicationTests {

    @Autowired
    SolrClient solrClient;
    @Autowired
    //SolrTemplate solrTemplate;



    @Test
    public void testUploadImage(){

        String condition = "HOT";

       String str = null;

        Map params = new HashMap<>();

        params.put("start", 0);
        params.put("rows", 4);
        params.put("q", "*:*");

        //StringBuilder str = new StringBuilder();
        str = "goodsSalesArea:"+"\""+condition+"\""+" OR name:"+"\""+condition+"\""+" OR title:"+"\""+condition+"\"";
        System.out.println(str);

       // params.put("fq","goodsSalesArea:\"NEW\" OR goodsSalesArea:\"HOT\"");
       // params.put("fq",str);
        SolrParams mapSolrParams = new MapSolrParams(params);

        QueryResponse query1 = null;

        try {

            query1 = solrClient.query(mapSolrParams);

            SolrDocumentList results = query1.getResults();

            for (SolrDocument result : results) {
                // SolrDocument 数据结构为Map
                System.out.println(result);
                //GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
               // goodsSolrSchema.setGoodsId((Integer.valueOf((String) result.getFieldValue("goodsId"))));
                //System.out.println(goodsSolrSchema.getGoodsId());


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }





/*
        List<String> strings = new ArrayList<>();
        strings.add("JINGPIN");
        GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
        goodsSolrSchema.setGoodsId(890);
        goodsSolrSchema.setGoodsSalesArea(strings);
        goodsSolrSchema.setTitle("华为手机真的好");
        goodsSolrSchema.setImg("换个手机壳");
        goodsSolrSchema.setShopId(123);
        goodsSolrSchema.setInventory(123);
        goodsSolrSchema.setName("华为手机");
        goodsSolrSchema.setPrice(28.22);
        try {
            solrClient.addBean(goodsSolrSchema);
            solrClient.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }*/

       /* try {
            //HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(SOLR_URL).build();
            SolrQuery query = new SolrQuery();
            //设定查询字段
            query.set("q", "title:测试");
            //指定返回结果字段
            query.setIncludeScore(true);
            //分页开始页数
            query.setStart(1);
            //设定返回记录数，默认为10条
            query.setRows(2);


            QueryResponse response = solrClient.query(query);

            //获取bean
            //  List<Object> bean = response.getBeans(Object.class);


            SolrDocumentList list = response.getResults();
            System.out.println(list);
            //return list;

        } catch (Exception e) {
            e.printStackTrace();
        }*/



    }


}
