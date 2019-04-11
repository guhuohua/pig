/**
 * Author: 常富文
 * Date:   2019/4/10 16:16
 * Description: 用户信息
 */


package com.ch.service.impl;

import com.ch.dao.SysUserMapper;
import com.ch.dao.UserMapper;
import com.ch.dto.UserInfos;
import com.ch.entity.SysUser;
import com.ch.entity.SysUserExample;
import com.ch.entity.User;
import com.ch.entity.UserExample;
import com.ch.service.ViewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewUserInfoServiceImpl implements ViewUserInfoService {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public UserInfos findByOpenId(String openId) {

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(openId);
        List<SysUser> users = userMapper.selectByExample(example);
        UserInfos userInfos = new UserInfos();
        if(users.size()>0){
            SysUser user = users.get(0);
            userInfos.setShopId(user.getShopId());
            userInfos.setUserId(user.getUserId());
            userInfos.setUsername(user.getUsername());
        }

        return userInfos;
    }
}