package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsEvaluation;

public interface ViewOrderEvaluteService {
    ResponseResult addEvalute(GoodsEvaluation goodsEvaluation, Integer shopId, String openId);
    ResponseResult showGoodEvluate(Integer goodsId,Integer shopId);
    ResponseResult showBadEvluate(Integer goodsId,Integer shopId);
    ResponseResult showMediumEvluate(Integer goodsId,Integer shopId);
    ResponseResult showAllEvluate(Integer goodsId,Integer shopId);
    ResponseResult showMyEvluate(Integer shopId, String openId);

}
