package com.ch.service;

import com.ch.dto.UserInfos;

public interface ViewUserInfoService {
     UserInfos findByOpenId(String openId);


}
