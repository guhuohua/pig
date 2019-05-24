package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.SysUserMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dto.BaseParam;
import com.ch.entity.SysUser;
import com.ch.model.SysUserListDTO;
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
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<SysUserListDTO> userInfos = userInfoMapper.list(param.getName(), param.getPhone(), sysUser.getShopId());
        PageInfo<SysUserListDTO> pageInfo = new PageInfo<>(userInfos);
        for (SysUserListDTO sysUserListDTO:pageInfo.getList()) {
            Long orderPrice = userInfoMapper.orderPrice(sysUserListDTO.getId(), sysUser.getShopId());
            if (BeanUtils.isNotEmpty(orderPrice)) {
                sysUserListDTO.setOrderPrice(orderPrice);
            } else {
                sysUserListDTO.setOrderPrice(0l);
            }
            Long orderCount = userInfoMapper.orderCount(sysUserListDTO.getId(), sysUser.getShopId());
            if (BeanUtils.isNotEmpty(orderCount)) {
                sysUserListDTO.setOrderCount(orderCount);
            } else {
                sysUserListDTO.setOrderCount(0l);
            }

        }
        result.setData(pageInfo);
        return result;
    }
}
