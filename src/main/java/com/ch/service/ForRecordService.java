package com.ch.service;

import com.ch.base.ResponseResult;

public interface ForRecordService {
    ResponseResult list(String openId);

    ResponseResult add(String orderId);
}
