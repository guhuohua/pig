package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.PageParam;

public interface UserAccountFlowService {
    ResponseResult list(String openId);

    ResponseResult addAccountFlow(String orderId);
}
