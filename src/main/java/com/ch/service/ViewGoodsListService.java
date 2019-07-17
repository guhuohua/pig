package com.ch.service;

import com.ch.base.PageQuery;
import com.ch.base.ResponseResult;
import com.ch.dto.SolrDto;

public interface ViewGoodsListService {

    /**
     * 展示商品列表
     *
     * @param solrDto
     * @return
     */
    ResponseResult findGoodsList(SolrDto solrDto, Integer shopId);

    ResponseResult shouCondition(String condition, Integer shopId);

    /**
     * 秒杀列表
     * @param query
     * @return
     */
    ResponseResult spikeGoodsList(Integer pageNum, Integer pageSize);
}
