package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.ShopMapper;
import com.ch.dao.SysRoleMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dao.SysUserRoleMapper;
import com.ch.entity.*;
import com.ch.model.ShopUserParam;
import com.ch.model.SysUserParam;
import com.ch.service.SysUserMangeService;
import com.ch.util.BaiduTranslateUtil;
import com.ch.util.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserMangeServiceImpl implements SysUserMangeService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    BaiduTranslateUtil baiduTranslateUtil;

    @Autowired
    ShopMapper shopMapper;

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
    @Transactional
    public ResponseResult insertUser(SysUserParam sysUserParam) {
        ResponseResult result = new ResponseResult();
        //判断该店铺下是否已经存在管理员
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andShopIdEqualTo(sysUserParam.getShopId());
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        for (SysRole sysRole:sysRoles) {
            if ("管理员".equals(sysRole.getRoleName())) {
                result.setCode(500);
                result.setError("该店铺下已经存在管理员，不要重复添加");
                result.setError_description("该店铺下已经存在管理员，不要重复添加");
                return result;
            }
        }
        SysUser sysUser = new SysUser();
        modelMapper.map(sysUserParam, sysUser);
        sysUser.setCreateTime(new Date());
        if (BeanUtils.isEmpty(sysUserParam.getUserId())) {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUsernameEqualTo(sysUserParam.getUsername());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if (sysUsers.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            SysUserExample sysUserExample2 = new SysUserExample();
            sysUserExample2.createCriteria().andPhoneEqualTo(sysUserParam.getPhone());
            List<SysUser> sysUsers2 = sysUserMapper.selectByExample(sysUserExample2);
            if (sysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            try {
                //构建密码以及加密
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(sysUserParam.getPassword());
                sysUser.setSalt(salt);
                sysUser.setPassword(encodedPassword);
                sysUserMapper.insert(sysUser);
                //构建角色信息
                SysRole sysRole = new SysRole();
                sysRole.setRoleName("管理员");
                sysRole.setCreateTime(new Date());
                sysRole.setShopId(sysUserParam.getShopId());
                Shop shop = shopMapper.selectByPrimaryKey(sysUserParam.getShopId());
                sysRole.setRoleCode(baiduTranslateUtil.translate(shop.getTitle()) + "admin");
                sysRole.setRoleDesc(shop.getTitle() + "的管理员");
                sysRoleMapper.insert(sysRole);
                Integer roleId = sysRole.getRoleId();
                //构建用户角色中间表
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setShopId(sysUserParam.getShopId());
                sysUserRole.setUserId(sysUser.getUserId());
                sysUserRoleMapper.insert(sysUserRole);
            } catch (Exception e) {
                result.setCode(500);
                result.setError(e.getMessage());
                result.setError_description("新增用户失败");
                return result;
            }
        } else {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUsernameEqualTo(sysUserParam.getUsername()).andUserIdNotEqualTo(sysUserParam.getUserId());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if (sysUsers.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            SysUserExample sysUserExample2 = new SysUserExample();
            sysUserExample2.createCriteria().andPhoneEqualTo(sysUserParam.getPhone()).andUserIdNotEqualTo(sysUserParam.getUserId());
            List<SysUser> sysUsers2 = sysUserMapper.selectByExample(sysUserExample2);
            if (sysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            if (BeanUtils.isNotEmpty(sysUserParam.getPassword())) {
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(sysUserParam.getPassword());
                sysUser.setSalt(salt);
                sysUser.setPassword(encodedPassword);
            }
            sysUser.setUpdateTime(new Date());
            sysUserMapper.updateByPrimaryKey(sysUser);
        }
        return result;
    }

    @Override
    public ResponseResult deleteUser(Integer userId, Integer tokenUserId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(tokenUserId);
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andShopIdEqualTo(sysUser.getShopId());
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        for (SysRole sysRole:sysRoles) {
            if ("管理员".equals(sysRole.getRoleName())) {
                result.setCode(500);
                result.setError("该用户为管理员，不允许删除");
                result.setError_description("该用户为管理员，不允许删除");
                return result;
            }
        }
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andShopIdEqualTo(Long.valueOf(sysUser.getShopId())).andUserIdEqualTo(userId);
        sysUserMapper.deleteByExample(sysUserExample);
        return result;
    }
}
