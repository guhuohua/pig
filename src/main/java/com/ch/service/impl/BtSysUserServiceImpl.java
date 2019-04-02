package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.model.PersonMangeDTO;
import com.ch.model.PersonMangeParam;
import com.ch.model.PersonParam;
import com.ch.model.UserDTO;
import com.ch.entity.*;
import com.ch.service.BtSysUserService;
import com.ch.util.IdUtil;
import com.ch.util.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BtSysUserServiceImpl implements BtSysUserService {

    private static final Logger LOGGER = LogManager.getLogger(BtSysUserServiceImpl.class);

    @Autowired
    BtSysUserMapper btSysUserMapper;

    @Autowired
    BtSysUserRoleMapper btSysUserRoleMapper;

    @Autowired
    BtSysRoleMapper btSysRoleMapper;

    @Autowired
    BtSysRolePermissionMapper btSysRolePermissionMapper;

    @Autowired
    BtSysPermissionMapper btSysPermissionMapper;

    @Override
    public UserDTO findById(String userId) {
        UserDTO dto = new UserDTO();
        BtSysUser user = btSysUserMapper.findByUserId(userId);
        if (user != null) {
            dto.setUserId(userId);
            dto.setPassword(user.getPassword());
            dto.setUsername(user.getUsername());
            List<BtSysUserRole> btSysUserRoles = btSysUserRoleMapper.findByUserId(userId);
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            btSysUserRoles.stream().forEach(userRole -> {
                BtSysRole role = btSysRoleMapper.findByRoleId(userRole.getRoleId());
                if (role != null) {
                    roles.add(role.getRoleName());
                }
                BtSysRolePermissionExample btSysRolePermissionExample = new BtSysRolePermissionExample();
                btSysRolePermissionExample.createCriteria().andRoleIdEqualTo(userRole.getRoleId());
                List<BtSysRolePermission> btSysRolePermissions = btSysRolePermissionMapper.selectByExample(btSysRolePermissionExample);
                btSysRolePermissions.forEach(rolePermission -> {
                    BtSysPermissionExample btSysPermissionExample= new BtSysPermissionExample();
                    btSysPermissionExample.createCriteria().andPermissionIdEqualTo(rolePermission.getPermissionId());
                    btSysPermissionMapper.selectByExample(btSysPermissionExample).forEach(permission -> {
                        permissions.add(permission.getDesc());
                    });
                });
            });
            dto.setRoles(roles);
            dto.setPermissions(permissions);
        }
        return dto;
    }

    @Override
    public ResponseResult login(UserDTO userDTO) {
        ResponseResult result = new ResponseResult();
        if (userDTO.getUsername() == null) {
            result.setCode(500);
            result.setError("用户名不能为空");
            result.setError_description("用户名不能为空");
            return result;
        }
        if (userDTO.getPassword() == null) {
            result.setCode(500);
            result.setError("密码不能为空");
            result.setError_description("密码不能为空");
            return result;
        }
        BtSysUser btSysUser = btSysUserMapper.findByAccount(userDTO.getUsername());
        if (btSysUser.getStatus()==1) {
            result.setCode(500);
            result.setError("该用户已被禁用，请联系管理员");
            result.setError_description("该用户已被禁用，请联系管理员");
            return result;
        }
        if (btSysUser!=null) {
            PasswordUtil encoderMd5 = new PasswordUtil(btSysUser.getSalt(), "sha-256");
            String encodedPassword = encoderMd5.encode(userDTO.getPassword());
            if (btSysUser.getPassword().equals(encodedPassword)) {
                UserDTO dto = findById(btSysUser.getUserId());
                result.setData(dto);
                return result;
            } else {
                result.setCode(500);
                result.setError("账号或者密码输入错误请重新输入");
                result.setError_description("账号或者密码输入错误请重新输入");
                return result;
            }
        } else {
            result.setCode(500);
            result.setError("该用户不存在，请重新输入");
            result.setError_description("该用户不存在，请重新输入");
            return result;
        }

    }


    @Override
    public ResponseResult personMange(PersonMangeParam param) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(param.getIndex(), param.getSize());
        List<PersonMangeDTO> personMangeDTOS = btSysUserMapper.btUserList(param.getUserName(), param.getRoleId());
        PageInfo<PersonMangeDTO> btSysRolePageInfo = new PageInfo<>(personMangeDTOS);
        result.setData(btSysRolePageInfo);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult updateOrInsertUser(PersonParam personParam) {
        ResponseResult result = new ResponseResult();

        if (personParam.getUserId() == null || personParam.getUserId().equals("")) {
            BtSysUserExample example = new BtSysUserExample();
            example.createCriteria().andUsernameEqualTo(personParam.getUserName());
            List<BtSysUser> btSysUsers = btSysUserMapper.selectByExample(example);
            BtSysUserExample example2 = new BtSysUserExample();
            example2.createCriteria().andAccountEqualTo(personParam.getAccount());
            List<BtSysUser> btSysUsers2 = btSysUserMapper.selectByExample(example2);
            BtSysUserExample example3 = new BtSysUserExample();
            example3.createCriteria().andPhoneEqualTo(personParam.getPhone());
            List<BtSysUser> btSysUsers3 = btSysUserMapper.selectByExample(example3);
            if (btSysUsers.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            if (btSysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("登录账户不能重复");
                result.setError_description("登录账户不能重复");
                return result;
            }
            if (btSysUsers3.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            try {
                BtSysUser btSysUser = new BtSysUser();
                String userId = IdUtil.createIdByUUID();
                btSysUser.setUserId(userId);
                btSysUser.setAccount(personParam.getAccount());
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(personParam.getPassword());
                btSysUser.setSalt(salt);
                btSysUser.setPassword(encodedPassword);
                btSysUser.setPhone(personParam.getPhone());
                btSysUser.setUpdateTime(new Date());
                btSysUser.setUsername(personParam.getUserName());
                btSysUser.setStatus(personParam.getStatus());
                btSysUserMapper.insert(btSysUser);
                BtSysUserRole userRole = new BtSysUserRole();
                userRole.setRoleId(personParam.getRoleId());
                userRole.setUserId(userId);
                btSysUserRoleMapper.insert(userRole);
            } catch (Exception e) {
                LOGGER.error("新增人员失败" + e.getMessage(),e);
                result.setCode(500);
                result.setError(e.getMessage());
                result.setError_description("新增人员失败");
                return result;
            }
        } else {
            BtSysUserExample example = new BtSysUserExample();
            example.createCriteria().andUsernameEqualTo(personParam.getUserName()).andUserIdNotEqualTo(personParam.getUserId());
            List<BtSysUser> btSysUsers = btSysUserMapper.selectByExample(example);
            BtSysUserExample example2 = new BtSysUserExample();
            example2.createCriteria().andAccountEqualTo(personParam.getAccount()).andUserIdNotEqualTo(personParam.getUserId());
            List<BtSysUser> btSysUsers2 = btSysUserMapper.selectByExample(example2);
            BtSysUserExample example3 = new BtSysUserExample();
            example3.createCriteria().andPhoneEqualTo(personParam.getPhone()).andUserIdNotEqualTo(personParam.getUserId());
            List<BtSysUser> btSysUsers3 = btSysUserMapper.selectByExample(example3);
            if (btSysUsers.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            if (btSysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("登录账户不能重复");
                result.setError_description("登录账户不能重复");
                return result;
            }
            if (btSysUsers3.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            BtSysUser btSysUser = new BtSysUser();
            btSysUser.setAccount(personParam.getAccount());
            if (BeanUtils.isNotEmpty(personParam.getPassword())) {
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(personParam.getPassword());
                btSysUser.setSalt(salt);
                btSysUser.setPassword(encodedPassword);
            }
            btSysUser.setPhone(personParam.getPhone());
            btSysUser.setUpdateTime(new Date());
            btSysUser.setUsername(personParam.getUserName());
            btSysUser.setUserId(personParam.getUserId());
            btSysUser.setStatus(personParam.getStatus());
            try {
                btSysUserMapper.updateBtSysUserByUserId(btSysUser);
                btSysUserRoleMapper.updateByUserId(personParam.getUserId(), personParam.getRoleId());
            } catch (Exception e) {
                LOGGER.error("编辑人员失败" + e.getMessage(),e);
                result.setCode(500);
                result.setError(e.getMessage());
                result.setError_description("编辑人员失败");
                return result;
            }
        }
        return result;
    }

    @Override
    public ResponseResult resetPassword(String userId) {
        ResponseResult result = new ResponseResult();
        int resetPassword = btSysUserMapper.resetPassword(userId);
        if (resetPassword > 0) {
            return result;
        } else {
            result.setCode(500);
            result.setError("重置密码失败");
            result.setError_description("重置密码失败");
            return result;
        }
    }

    @Override
    public ResponseResult updateUserStatus(String userId, int status) {
        ResponseResult result = new ResponseResult();
        int resetPassword = btSysUserMapper.updateStatus(userId, status);
        if (resetPassword > 0) {
            return result;
        } else {
            result.setCode(500);
            result.setError("修改人员状态失败");
            result.setError_description("修改人员状态失败");
            return result;
        }
    }

}
