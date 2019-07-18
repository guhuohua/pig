/**
 * Author: 常富文
 * Date:   2019/4/10 16:16
 * Description: 用户信息
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.UserInfos;
import com.ch.entity.*;
import com.ch.service.SysMemberService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.FlowUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.math3.analysis.function.Sin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ViewUserInfoServiceImpl implements ViewUserInfoService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    BaseIntegralMapper baseIntegralMapper;
    @Autowired
    SysMemberService sysMemberService;
    @Autowired
    UserAccountFlowMapper userAccountFlowMapper;
    @Autowired
    SignMapper signMapper;

    @Override
    public UserInfos findByOpenId(String openId) {

        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(openId);
        List<SysUser> users = sysUserMapper.selectByExample(example);
        UserInfos userInfos = new UserInfos();
        if (users.size() > 0) {
            SysUser user = users.get(0);
            userInfos.setShopId(user.getShopId());
            userInfos.setUserId(user.getId());
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
        if (users.size() > 0) {
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
        return userInfo;
    }

    @Override
    public void insert(UserInfo record) {
        userInfoMapper.insert(record);
    }

    @Override
    public UserInfo findOneByOpenId(String openId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        return userInfo;
    }

    @Override
    public void updateByPrimaryKey(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public ResponseResult addTel(String openId, String tel) {
        ResponseResult result = new ResponseResult();
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        BaseIntegral baseIntegral = null;
        if (baseIntegrals.size()>0){
            baseIntegral  = baseIntegrals.get(0);
        }
        UserInfo userInfo = findOneByOpenId(openId);
        userInfo.setTel(tel);
        userInfo.setIntegral(userInfo.getIntegral()+baseIntegral.getPerfect());
        userInfo.setUseIntegral(userInfo.getUseIntegral()+baseIntegral.getPerfect());
        userInfoMapper.updateByPrimaryKey(userInfo);
        sysMemberService.synchronizedIntegral(userInfo.getId());
        FlowUtil.addFlowTel(baseIntegral.getPerfect().longValue(),"tel","INTEGRAL",0);
        return result;
    }

    @Override
    public ResponseResult sign(String openId) {
        ResponseResult result = new ResponseResult();
        UserInfo userInfo = findOneByOpenId(openId);
        Date endOfDay = getEndOfDay(new Date());
        SignExample example = new SignExample();
        SignExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        criteria.andSignDateLessThan(endOfDay);
        List<Sign> signs = signMapper.selectByExample(example);
        if (signs.size()>0){
            result.setCode(500);
            result.setError_description("今天已签到");
        }else {
            Sign sign = new Sign();
            sign.setSignDate(new Date());
            sign.setUserId(userInfo.getId());
            sign.setSignSatus(1+"");
            signMapper.insert(sign);
            result.setCode(0);
            result.setError_description("签到成功");
            BaseIntegral baseIntegral = baseIntegralMapper.selectByPrimaryKey(1);
            userInfo.setIntegral(userInfo.getIntegral()+baseIntegral.getSign());
            userInfo.setUseIntegral(userInfo.getUseIntegral()+baseIntegral.getSign());
            userInfo.setSignStatus(1);
            userInfoMapper.updateByPrimaryKey(userInfo);
            sysMemberService.synchronizedIntegral(userInfo.getId());
            FlowUtil.addFlowTel(baseIntegral.getPerfect().longValue(),"sign","INTEGRAL",0);
        }
        return result;
    }


    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


}
