package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.SysUserMapper;
import com.ch.dao.UserAddressMapper;
import com.ch.dto.BaseParam;
import com.ch.entity.SysUser;
import com.ch.model.SysUserAddressModel;
import com.ch.service.SysUserAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserAddressServiceImpl implements SysUserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public ResponseResult list(BaseParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<SysUserAddressModel> list = userAddressMapper.list(param.getName(), param.getPhone(), sysUser.getShopId());
        PageInfo<SysUserAddressModel> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }
}
