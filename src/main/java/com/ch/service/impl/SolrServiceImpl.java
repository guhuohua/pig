package com.ch.service.impl;

import com.ch.dao.GoodsAreaMapper;
import com.ch.dao.GoodsMapper;
import com.ch.dao.SysUserMapper;
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

    @Override
    @Async
    public void releaseGoods(Integer goodsId, Integer shopId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(shopId).andIdEqualTo(goodsId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (goodsList.stream().findFirst().isPresent()) {
            Goods goods = goodsList.stream().findFirst().get();
            if (goods.getStatus() == 1) {
                GoodsSolrSchema goodsSolrSchema = new GoodsSolrSchema();
                modelMapper.map(goods, goodsSolrSchema);
                goodsSolrSchema.setId(UUID.randomUUID().toString());
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
