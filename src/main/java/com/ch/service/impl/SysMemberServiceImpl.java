package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BaseIntegralMapper;
import com.ch.dao.MemberRankMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.BaseIntegral;
import com.ch.entity.MemberRank;
import com.ch.entity.UserInfo;
import com.ch.model.SysBaseSettingParam;
import com.ch.service.SysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMemberServiceImpl implements SysMemberService {

    @Autowired
    MemberRankMapper memberRankMapper;

    @Autowired
    BaseIntegralMapper baseIntegralMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    public ResponseResult baseSetting(SysBaseSettingParam param) {
        ResponseResult result = new ResponseResult();
        MemberRank memberRank = new MemberRank();
        memberRankMapper.deleteByExample(null);
        baseIntegralMapper.deleteByExample(null);
        memberRank.setBronze(param.getBronze());
        memberRank.setDiamonds(param.getDiamonds());
        memberRank.setGold(param.getGold());
        memberRank.setPlatinum(param.getPlatinum());
        memberRank.setSilver(param.getSilver());
        memberRankMapper.insert(memberRank);
        BaseIntegral baseIntegral = new BaseIntegral();
        baseIntegral.setComment(param.getComment());
        baseIntegral.setPerfect(param.getPerfect());
        baseIntegral.setSign(param.getSign());
        baseIntegralMapper.insert(baseIntegral);
        return result;
    }

    @Override
    public ResponseResult synchronizedIntegral() {
        MemberRank memberRank = memberRankMapper.selectByExample(null).get(0);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(null);
        for (UserInfo userInfo:userInfos) {
        }
        return null;
    }
}
