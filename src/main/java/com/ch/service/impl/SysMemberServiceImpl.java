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
import com.ch.model.MemberModel;
import com.ch.model.SysBaseSettingParam;
import com.ch.service.SysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.From;
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
        for (MemberModel memberModel : param.getMemberModels()) {
            if (BeanUtils.isEmpty(memberModel.getId())) {
                MemberRank memberRank = new MemberRank();
                memberRank.setMemberType(memberModel.getMemberType());
                memberRank.setIntegral(memberModel.getIntegral());
                memberRank.setDiscount(memberModel.getDiscount());
                memberRankMapper.insert(memberRank);
            } else {
                MemberRank memberRank = memberRankMapper.selectByPrimaryKey(memberModel.getId());
                memberRank.setMemberType(memberModel.getMemberType());
                memberRank.setIntegral(memberModel.getIntegral());
                memberRank.setDiscount(memberModel.getDiscount());
                memberRankMapper.updateByPrimaryKey(memberRank);
            }

        }
        synchronizedIntegral(null);
        return result;
    }

    @Override
    public ResponseResult acquisitionMethod(SysBaseSettingParam param) {
        ResponseResult result = new ResponseResult();
        baseIntegralMapper.deleteByExample(null);
        BaseIntegral baseIntegral = new BaseIntegral();
        baseIntegral.setComment(param.getComment());
        baseIntegral.setPerfect(param.getPerfect());
        baseIntegral.setSign(param.getSign());
        baseIntegral.setFirstShare(param.getFirstShare());
        baseIntegral.setCashIntegral(param.getCashIntegral());
        baseIntegral.setSuperintendence(param.getSuperintendence());
        baseIntegral.setPaymentIntegral(param.getPaymentIntegral());
        baseIntegralMapper.insert(baseIntegral);
        synchronizedIntegral(null);
        return result;
    }

    @Override
    public ResponseResult synchronizedIntegral(Integer userId) {
        ResponseResult result = new ResponseResult();
        List<UserInfo> userInfos = new ArrayList<>();
        if (null != userId) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            userInfos.add(userInfo);
        } else {
            userInfos = userInfoMapper.selectByExample(null);
        }
        List<MemberRank> memberRanks = memberRankMapper.selectByExample(null);
        int gold = 0;
        int plat = 0;
        int dia = 0;
        int tour = 0;
        for (MemberRank memberRank:memberRanks) {
            if ("GOLD".equals(memberRank.getMemberType())) {
                gold = memberRank.getIntegral();
            }
            if ("PLATINUM".equals(memberRank.getMemberType())) {
                plat = memberRank.getIntegral();
            }
            if ("DIAMONDS".equals(memberRank.getMemberType())) {
                dia = memberRank.getIntegral();
            }
            if ("TOURIST".equals(memberRank.getMemberType())) {
                tour = memberRank.getIntegral();
            }
        }
        for (UserInfo userInfo : userInfos) {
            if (userInfo.getIntegral() >= dia) {
                userInfo.setMember("DIAMONDS");
            }
            if (userInfo.getIntegral() >= plat && userInfo.getIntegral() < dia) {
                userInfo.setMember("PLATINUM");
            }
            if (userInfo.getIntegral() >= gold && userInfo.getIntegral() < plat) {
                userInfo.setMember("GOLD");
            }
            if (userInfo.getIntegral() >= 0 && userInfo.getIntegral() < gold) {
                userInfo.setMember("TOURIST");
            }
            userInfoMapper.updateByPrimaryKey(userInfo);
        }
        return result;
    }

    @Override
    public ResponseResult findBaseSetting() {
        ResponseResult result = new ResponseResult();
        SysBaseSettingParam param = new SysBaseSettingParam();
        List<MemberModel> memberModels = new ArrayList<>();
        List<MemberRank> memberRanks = memberRankMapper.selectByExample(null);
        if (BeanUtils.isNotEmpty(memberRanks)) {
            for (MemberRank memberRank : memberRanks) {
                MemberModel memberModel = new MemberModel();
                memberModel.setId(memberRank.getId());
                memberModel.setDiscount(memberRank.getDiscount());
                memberModel.setIntegral(memberRank.getIntegral());
                memberModel.setMemberType(memberRank.getMemberType());
                memberModels.add(memberModel);
            }
        }
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        if (BeanUtils.isNotEmpty(baseIntegrals)) {
            BaseIntegral baseIntegral = baseIntegrals.get(0);
            param.setSign(baseIntegral.getSign());
            param.setPerfect(baseIntegral.getPerfect());
            param.setComment(baseIntegral.getComment());
            param.setFirstShare(baseIntegral.getFirstShare());
            param.setPaymentIntegral(baseIntegral.getPaymentIntegral());
            param.setSuperintendence(baseIntegral.getSuperintendence());
            param.setCashIntegral(baseIntegral.getCashIntegral());
        }
        param.setMemberModels(memberModels);
        result.setData(param);
        return result;
    }
}
