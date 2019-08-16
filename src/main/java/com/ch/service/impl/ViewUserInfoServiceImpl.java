/**
 * Author: 常富文
 * Date:   2019/4/10 16:16
 * Description: 用户信息
 */

package com.ch.service.impl;

import com.alibaba.fastjson.JSON;
import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.LoginDTO;
import com.ch.dto.UserInfos;
import com.ch.entity.*;
import com.ch.model.TelParam;
import com.ch.service.SysMemberService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.FlowUtil;
import com.ch.util.RandomUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.math3.analysis.function.Sin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    MemberLevelMapper memberLevelMapper;
    @Autowired
    FlowUtil flowUtil;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MemberRankMapper memberRankMapper;


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
        String stringRandom = RandomUtil.getStringRandom(6);
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        BaseIntegral baseIntegral = null;
        if (baseIntegrals.size() > 0) {
            baseIntegral = baseIntegrals.get(0);
        }
        UserInfo userInfo = findOneByOpenId(openId);
        userInfo.setTel(tel);
        userInfo.setInvitationCode(stringRandom);
        userInfo.setIntegral(userInfo.getIntegral() + baseIntegral.getPerfect());
        userInfo.setUseIntegral(userInfo.getUseIntegral() + baseIntegral.getPerfect());
        flowUtil.addFlowTel(baseIntegral.getPerfect().longValue(), "tel", "INTEGRAL", 0, userInfo.getId());
        userInfoMapper.updateByPrimaryKey(userInfo);
        sysMemberService.synchronizedIntegral(userInfo.getId());
        result.setData(stringRandom);
        return result;
    }

    @Override
    public ResponseResult addInvitationCode(String openId, String invitationCode) {
        ResponseResult result = new ResponseResult();
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        BaseIntegral baseIntegral = null;
        if (baseIntegrals.size() > 0) {
            baseIntegral = baseIntegrals.get(0);
        }
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andInvitationCodeEqualTo(invitationCode);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo1 = userInfos.get(0);
        UserInfo userInfo = findOneByOpenId(openId);
        if ("".equals(userInfo.getSuperiorInvitationCode()) || null == userInfo.getSuperiorInvitationCode()) {
            userInfo.setSuperiorInvitationCode(invitationCode);
            userInfo1.setIntegral(userInfo.getIntegral() + baseIntegral.getFirstShare());
            userInfo1.setUseIntegral(userInfo.getUseIntegral() + baseIntegral.getFirstShare());
            flowUtil.addFlowTel(baseIntegral.getFirstShare().longValue(), "first", "INTEGRAL", 0, userInfo1.getId());
        } else {
            result.setCode(500);
            result.setError_description("已绑定上级分销商");
            return result;
        }
       userInfoMapper.updateByPrimaryKey(userInfo1);
        userInfoMapper.updateByPrimaryKey(userInfo);
        sysMemberService.synchronizedIntegral(userInfo.getId());

        return result;
    }

    @Override
    @Transactional
    public ResponseResult sign(String openId) {
        ResponseResult result = new ResponseResult();
        UserInfo userInfo = findOneByOpenId(openId);
        Date endOfDay = getEndOfDay(new Date());
        Date startDay = getStartOfDay(new Date());
        SignExample example = new SignExample();
        SignExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        criteria.andSignDateLessThan(endOfDay);
        criteria.andSignDateGreaterThan(startDay);
        List<Sign> signs = signMapper.selectByExample(example);
        if (signs.size() > 0) {
            result.setCode(500);
            result.setError_description("今天已签到");
        } else {
            Sign sign = new Sign();
            sign.setSignDate(new Date());
            sign.setUserId(userInfo.getId());
            sign.setSignSatus(1 + "");
            signMapper.insert(sign);
            List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
            BaseIntegral baseIntegral = baseIntegrals.get(0);
            userInfo.setIntegral(userInfo.getIntegral() + baseIntegral.getSign());
            userInfo.setUseIntegral(userInfo.getUseIntegral() + baseIntegral.getSign());
            userInfo.setSignStatus(1);
            userInfoMapper.updateByPrimaryKey(userInfo);
            flowUtil.addFlowTel(baseIntegral.getSign().longValue(), "sign", "INTEGRAL", 0, userInfo.getId());
            sysMemberService.synchronizedIntegral(userInfo.getId());
        }
        return result;
    }

    @Override
    public int findDiscountByOpenId(String openId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        List<MemberRank> memberRanks = new ArrayList<>();
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
            MemberRankExample example1 = new MemberRankExample();
            MemberRankExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andMemberTypeEqualTo(userInfo.getMember());
            memberRanks = memberRankMapper.selectByExample(example1);
        }
        return memberRanks.get(0).getDiscount();
    }

    @Override
    public ResponseResult signStatus(String openId) {
        ResponseResult result = new ResponseResult();

        if (BeanUtils.isNotEmpty(openId)) {
            Date endOfDay = getEndOfDay(new Date());
            Date startDay = getStartOfDay(new Date());
            UserInfo userInfo = findOneByOpenId(openId);
            SignExample example1 = new SignExample();
            SignExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andSignDateLessThan(endOfDay);
            criteria1.andSignDateGreaterThan(startDay);
            List<Sign> signs = signMapper.selectByExample(example1);
            if (signs.size() == 0) {
                userInfo.setSignStatus(0);

            }
            List<MemberRank> memberRankss = memberRankMapper.selectByExample(null);
            int gold = 0;
            int plat = 0;
            int dia = 0;
            for (MemberRank memberRank : memberRankss) {
                if ("GOLD".equals(memberRank.getMemberType())) {
                    gold = memberRank.getIntegral();
                }
                if ("PLATINUM".equals(memberRank.getMemberType())) {
                    plat = memberRank.getIntegral();
                }
                if ("DIAMONDS".equals(memberRank.getMemberType())) {
                    dia = memberRank.getIntegral();
                }
            }
            MemberRankExample example = new MemberRankExample();
            MemberRankExample.Criteria criteria = example.createCriteria();
            criteria.andMemberTypeEqualTo(userInfo.getMember());
            List<MemberRank> memberRanks = memberRankMapper.selectByExample(example);
            userInfoMapper.updateByPrimaryKey(userInfo);
            LoginDTO loginDTO = new LoginDTO();
            modelMapper.map(userInfo, loginDTO);
            loginDTO.setDiscount(memberRanks.get(0).getDiscount());
            if (userInfo.getIntegral() >= dia) {
                loginDTO.setNextMemberIntegral(dia);
                loginDTO.setNextMember("DIAMONDS");
            }
            if (userInfo.getIntegral() >= plat && userInfo.getIntegral() < dia) {
                loginDTO.setNextMemberIntegral(dia);
                loginDTO.setNextMember("DIAMONDS");

            }
            if (userInfo.getIntegral() >= gold && userInfo.getIntegral() < plat) {
                loginDTO.setNextMemberIntegral(plat);
                loginDTO.setNextMember("PLATINUM");
            }
            if (userInfo.getIntegral() >= 0 && userInfo.getIntegral() < gold) {
                loginDTO.setNextMemberIntegral(gold);
                loginDTO.setNextMember("GOLD");
            }
            result.setData(loginDTO);
            return result;
        } else {
            result.setCode(500);
            result.setError_description("openId不能为空");
            return result;
        }

    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;

    }

}
