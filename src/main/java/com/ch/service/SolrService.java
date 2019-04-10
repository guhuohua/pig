package com.ch.service;

public interface SolrService {

    /**
     * Solr上架商品
     * @param goodsId
     * @param userId
     */
    void releaseGoods(Integer goodsId, Integer userId);

    /**
     * Solr下架商品
     * @param goodsId
     */
    void lowerShelf(Integer goodsId);
}
