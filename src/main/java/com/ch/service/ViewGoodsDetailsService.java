package com.ch.service;

import com.ch.base.ResponseResult;

public interface ViewGoodsDetailsService {
     ResponseResult findGoodsDetailsByGoodsId(Integer goodsId,Integer shopId);

}
