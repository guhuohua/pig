package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.SysUserMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dto.BaseParam;
import com.ch.entity.SysUser;
import com.ch.entity.UserInfo;
import com.ch.entity.UserInfoExample;
import com.ch.service.SysShopUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysShopUserServiceImpl implements SysShopUserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public ResponseResult list(BaseParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andShopIdEqualTo(sysUser.getShopId());
        if (BeanUtils.isNotEmpty(param.getName())) {
            criteria.andNicknameLike(param.getName());
        }
        if (BeanUtils.isNotEmpty(param.getPhone())) {
            criteria.andTelLike(param.getPhone());
        }
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        return result;
    }
}
