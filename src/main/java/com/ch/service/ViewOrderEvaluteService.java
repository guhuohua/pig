package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsEvaluation;

public interface ViewOrderEvaluteService {
    ResponseResult addEvalute(GoodsEvaluation goodsEvaluation, Integer shopId, String openId);
    ResponseResult showGoodEvluate(Integer goodsId);
    ResponseResult showBadEvluate(Integer goodsId);
    ResponseResult showMediumEvluate(Integer goodsId);
    ResponseResult showAllEvluate(Integer goodsId);
    ResponseResult showMyEvluate(Integer shopId, String openId);

}
