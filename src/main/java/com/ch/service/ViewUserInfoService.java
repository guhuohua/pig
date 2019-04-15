package com.ch.service;

import com.ch.dto.UserInfos;
import com.ch.entity.User;

public interface ViewUserInfoService {
     UserInfos findByOpenId(String openId);

     User findUserByOpenId(String openId);

     void updateByPrimaryKey(User record);

}
