package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.UserInfos;
import com.ch.entity.User;
import com.ch.entity.UserInfo;

public interface ViewUserInfoService {
    UserInfos findByOpenId(String openId);

    User findUserByOpenId(String openId);

    void updateByPrimaryKey(User record);

    UserInfo findByUserId(Integer userId);

    void insert(UserInfo record);

    UserInfo findOneByOpenId(String openId);

    void updateByPrimaryKey(UserInfo userInfo);

     ResponseResult addTel(String openId , String tel);

    ResponseResult sign(String openId);




}
