/**
 * Author: 常富文
 * Date:   2019/4/13 13:53
 * Description: solr批量导入工具类
 */


package com.ch.util;

import com.ch.dao.GoodsAreaMapper;
import com.ch.dao.GoodsMapper;
import com.ch.entity.*;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SolrUtil {

    @Autowired
    SolrClient solrClient;

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsAreaMapper goodsAreaMapper;

    public static void main(String[] args) {

        SolrUtil solrUtil =new SolrUtil();
        solrUtil.importSolr();

    }

    public void importSolr(){

        GoodsExample example =new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<Goods> goods = goodsMapper.selectByExample(example);
        List<Integer> goodsIds = new ArrayList<>();

        for (Goods good : goods ){
            GoodsAreaExample exampleArea = new GoodsAreaExample();
            GoodsAreaExample.Criteria criteria1 = exampleArea.createCriteria();
            criteria1.andGoodsIdEqualTo(good.getId());
            List<GoodsArea> goodsAreas = goodsAreaMapper.selectByExample(exampleArea);
            GoodsArea goodsArea = null;
            if (goodsAreas.size()>0){
                 goodsArea = goodsAreas.get(0);
            }
            GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
            goodsSolrSchema.setId(UUID.randomUUID().toString());
            goodsSolrSchema.setGoodsId(good.getId());
            goodsSolrSchema.setCategoryId(good.getCatrgoryId());
            goodsSolrSchema.setShopId(good.getShopId());
           // goodsSolrSchema.setGoodName(good.getName());
           // goodsSolrSchema.setGoodsImgUrl();

        }
        List<GoodsSolrSchema> goodsSolrSchemaList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("HOT");
        GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
      //  goodsSolrSchema.setId(UUID.randomUUID().toString());
        goodsSolrSchema.setGoodsId(9);
        goodsSolrSchema.setShopId(2);
        goodsSolrSchema.setSort(8);
        goodsSolrSchema.setNewSort(8);
        goodsSolrSchema.setStatus(1);
        goodsSolrSchema.setGoodsSalesArea("NEW");
        goodsSolrSchema.setCategoryId(1);
        goodsSolrSchemaList.add(goodsSolrSchema);
        try {
            //solrClient.deleteById("516a4cf5-3af5-42b1-9416-d4d580a96bfd");
            //solrClient.add
            solrClient.addBean(goodsSolrSchema);
            solrClient.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

    }
}


