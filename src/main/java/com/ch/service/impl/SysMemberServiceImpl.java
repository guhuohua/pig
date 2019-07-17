package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.BaseIntegralMapper;
import com.ch.dao.MemberRankMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.BaseIntegral;
import com.ch.entity.MemberRank;
import com.ch.entity.UserInfo;
import com.ch.enums.MemberEnum;
import com.ch.model.SysBaseSettingParam;
import com.ch.service.SysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    /*    MemberRank memberRank = new MemberRank();
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
        synchronizedIntegral(null);*/
        return result;
    }

    @Override
    public ResponseResult synchronizedIntegral(Integer userId) {

        ResponseResult result = new ResponseResult();
       /* List<UserInfo> userInfos = new ArrayList<>();
        if (null != userId) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            userInfos.add(userInfo);
        } else {
            userInfos = userInfoMapper.selectByExample(null);
        }
        MemberRank memberRank = memberRankMapper.selectByExample(null).get(0);
        for (UserInfo userInfo:userInfos) {
            if (userInfo.getIntegral() >= memberRank.getBronze()) {
                userInfo.setMember(MemberEnum.BRONZE.getMessage());
            }
            if (userInfo.getIntegral() >= memberRank.getSilver()) {
                userInfo.setMember(MemberEnum.SILVER.getMessage());
            }
            if (userInfo.getIntegral() >= memberRank.getGold()) {
                userInfo.setMember(MemberEnum.GOLD.getMessage());
            }
            if (userInfo.getIntegral() >= memberRank.getPlatinum()) {
                userInfo.setMember(MemberEnum.PLATINUM.getMessage());
            }
            if (userInfo.getIntegral() >= memberRank.getDiamonds()) {
                userInfo.setMember(MemberEnum.MASONRY.getMessage());
            }
            userInfoMapper.updateByPrimaryKey(userInfo);
        }*/
        return result;
    }

    @Override
    public ResponseResult findBaseSetting() {
        ResponseResult result = new ResponseResult();
        SysBaseSettingParam param = new SysBaseSettingParam();
        List<MemberRank> memberRanks = memberRankMapper.selectByExample(null);
        if (BeanUtils.isNotEmpty(memberRanks)) {
            MemberRank memberRank = memberRanks.get(0);
            param.setBronze(memberRank.getBronze());
            param.setSilver(memberRank.getSilver());
            param.setGold(memberRank.getGold());
            param.setPlatinum(memberRank.getPlatinum());
            param.setDiamonds(memberRank.getDiamonds());
        }
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        if (BeanUtils.isNotEmpty(baseIntegrals)) {
            BaseIntegral baseIntegral = baseIntegrals.get(0);
            param.setSign(baseIntegral.getSign());
            param.setPerfect(baseIntegral.getPerfect());
            param.setComment(baseIntegral.getComment());
        }
        result.setData(param);
        return result;
    }
}
