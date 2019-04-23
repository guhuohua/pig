/**
 * Author: 常富文
 * Date:   2019/4/3 9:20
 * Description: 用户实现类
 */


package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.model.UserDto;
import com.ch.service.SysUserService;
import com.ch.util.PasswordUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Logger LOGGER = LogManager.getLogger(SysUserServiceImpl.class);
   @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
   SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public UserDto findById(Integer userId) {
        UserDto dto = new UserDto();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser!=null){
            dto.setUserId(sysUser.getUserId());
            dto.setUsername(sysUser.getUsername());

            SysUserRoleExample example = new SysUserRoleExample();
            SysUserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andShopIdEqualTo(sysUser.getShopId());
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(example);
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            for (SysUserRole role : sysUserRoles){
                if (role != null){
                    SysRole sysRole = sysRoleMapper.selectByPrimaryKey(role.getRoleId());
                    SysRolePermissionExample example1 = new SysRolePermissionExample();
                    SysRolePermissionExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andRoleIdEqualTo(role.getRoleId());
                    List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectByExample(example1);
                    for (SysRolePermission rolePermission: sysRolePermissions ){
                        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
                        if (BeanUtils.isNotEmpty(sysPermission)) {
                            permissions.add(sysPermission.getDesc());
                        }
                    }
                    roles.add(sysRole.getRoleName());
                }

            }
            dto.setRoles(roles);
            dto.setPremissions(permissions);
        }
        return dto;
    }

    @Override
    public ResponseResult login(UserDto userDto) {
       ResponseResult result = new ResponseResult();
        if (userDto.getUsername() == null) {
            result.setCode(500);
            result.setError("用户名不能为空");
            result.setError_description("用户名不能为空");
            return result;
        }
        if (userDto.getPassword() == null) {
            result.setCode(500);
            result.setError("密码不能为空");
            result.setError_description("密码不能为空");
            return result;
        }
       SysUserExample example  = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userDto.getUsername());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size()>0){
            SysUser sysUser = sysUsers.get(0);
            if(sysUser.getStatus()==1){
                result.setCode(500);
                result.setError("该用户已被禁用，请联系管理员");
                result.setError_description("该用户已被禁用，请联系管理员");
                return result;
            }
            PasswordUtil encoderMd5 = new PasswordUtil(sysUser.getSalt(), "sha-256");
            String encodedPassword = encoderMd5.encode(userDto.getPassword());
            if (sysUser.getPassword().equals(encodedPassword)) {
                UserDto dto = findById(sysUser.getUserId());
                result.setData(dto);
                return result;
            } else {
                result.setCode(500);
                result.setError("账号或者密码输入错误请重新输入");
                result.setError_description("账号或者密码输入错误请重新输入");
                return result;
            }

        }else {
            result.setCode(500);
            result.setError("该用户不存在，请重新输入");
            result.setError_description("该用户不存在，请重新输入");
            return result;

        }
    }
}
