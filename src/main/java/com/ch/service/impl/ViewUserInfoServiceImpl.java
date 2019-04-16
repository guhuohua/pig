/**
 * Author: 常富文
 * Date:   2019/4/10 16:16
 * Description: 用户信息
 */


package com.ch.service.impl;

import com.ch.dao.SysUserMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dao.UserMapper;
import com.ch.dto.UserInfos;
import com.ch.entity.*;
import com.ch.service.ViewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewUserInfoServiceImpl implements ViewUserInfoService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfos findByOpenId(String openId) {

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(openId);
        List<SysUser> users = sysUserMapper.selectByExample(example);
        UserInfos userInfos = new UserInfos();
        if(users.size()>0){
            SysUser user = users.get(0);
            userInfos.setShopId(user.getShopId());
            userInfos.setUserId(user.getUserId());
            userInfos.setUsername(user.getUsername());
        }

        return userInfos;
    }

    @Override
    public User findUserByOpenId(String openId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(openId);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        if(users.size()>0){
             user = users.get(0);

        }
        return user;
    }

    @Override
    public void updateByPrimaryKey(User record) {


        userMapper.updateByPrimaryKey(record);

    }

    @Override
    public UserInfo findByUserId(Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        return  userInfo;
    }

    @Override
    public void insert(UserInfo record) {
        userInfoMapper.insert(record);
    }


}
