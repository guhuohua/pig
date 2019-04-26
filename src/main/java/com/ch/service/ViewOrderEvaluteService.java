package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsEvaluation;

public interface ViewOrderEvaluteService {
    ResponseResult addEvalute(GoodsEvaluation goodsEvaluation, Integer shopId, String openId);

}
