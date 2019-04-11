package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.GoodsDto;
import com.ch.dto.SolrDto;

public interface ViewGoodsListService {

    /**
     * 展示商品列表
     * @param solrDto
     * @return
     */
    ResponseResult findGoodsList(SolrDto  solrDto,Integer userId);
}
