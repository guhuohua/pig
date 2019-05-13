package com.ch.service;

public interface SolrService {

    /**
     * Solr上架商品
     * @param goodsId
     * @param shopId
     */
    void releaseGoods(Integer goodsId, Integer shopId);

    /**
     * Solr下架商品
     * @param goodsId
     */
    void lowerShelf(Integer goodsId);
}
