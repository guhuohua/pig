package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.SysUserMapper;
import com.ch.entity.SysUser;
import com.ch.entity.SysUserExample;
import com.ch.model.ShopUserParam;
import com.ch.service.SysUserMangeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserMangeServiceImpl implements SysUserMangeService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public ResponseResult findByShopId(ShopUserParam  shopUserParam) {
        ResponseResult result = new ResponseResult();
        SysUserExample sysUserExample = new SysUserExample();
        PageHelper.startPage(shopUserParam.getCurrentPage(), shopUserParam.getPageSize());
        sysUserExample.createCriteria().andShopIdEqualTo(Long.valueOf(shopUserParam.getShopId()));
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        PageInfo<SysUser> page = new PageInfo<>(sysUserList);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult insertUser() {
        return null;
    }
}
