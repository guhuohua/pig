package com.ch.service.impl;

import com.alibaba.fastjson.JSON;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.enums.GoodsSaleAreaEnum;
import com.ch.service.SolrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SolrServiceImpl implements SolrService {

    private static final Logger LOGGER = LogManager.getLogger(SolrServiceImpl.class);

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsAreaMapper goodsAreaMapper;

    @Autowired
    SolrClient solrClient;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    GoodsEvaluationMapper goodsEvaluationMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    SpikeGoodsMapper spikeGoodsMapper;


    @Override
    @Async
    public void releaseGoods(Integer goodsId, Integer shopId) {
        System.out.println("开始删除solr:" + goodsId);
        lowerShelf(goodsId);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(shopId).andIdEqualTo(goodsId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            if (1 == goods.getStatus()) {
                GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
                modelMapper.map(goods, goodsSolrSchema);
                GoodsEvaluationExample evaluationExample = new GoodsEvaluationExample();
                GoodsEvaluationExample.Criteria criteria = evaluationExample.createCriteria();
                criteria.andGoodsIdEqualTo(goodsId);
                List<GoodsEvaluation> goodsEvaluations = goodsEvaluationMapper.selectByExample(evaluationExample);
                goodsSolrSchema.setEvaluationNum(goodsEvaluations.size());
                goodsSolrSchema.setCreateTime(goods.getCreateTime().getTime());
                goodsSolrSchema.setId(UUID.randomUUID().toString());
                goodsSolrSchema.setCategoryId(goods.getCatrgoryId());
                GoodsAreaExample goodsAreaExample = new GoodsAreaExample();
                goodsAreaExample.createCriteria().andShopIdEqualTo(shopId).andGoodsIdEqualTo(goods.getId());
                goodsAreaMapper.selectByExample(goodsAreaExample).forEach(item -> {
                    if (item.getStatus() == 1) {
                        goodsSolrSchema.setGoodsSalesArea(item.getGoodsClassification());
                        if (GoodsSaleAreaEnum.BOUTIQUE.name().equals(item.getGoodsClassification())) {
                            goodsSolrSchema.setBoutiqueSort(item.getSort());
                        }
                        if (GoodsSaleAreaEnum.HOT.name().equals(item.getGoodsClassification())) {
                            goodsSolrSchema.setHotSort(item.getSort());
                        }
                        if (GoodsSaleAreaEnum.NEW.name().equals(item.getGoodsClassification())) {
                            goodsSolrSchema.setNewSort(item.getSort());
                        }
                    }
                });
                try {
                    System.out.println("准备同步solr:" + JSON.toJSONString(goodsSolrSchema));
                    solrClient.addBean(goodsSolrSchema);
                    solrClient.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("SOLR同步失败");
                } catch (SolrServerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    @Async
    public void lowerShelf(Integer goodsId) {
        try {
            solrClient.deleteByQuery("goodsId:" + goodsId);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
