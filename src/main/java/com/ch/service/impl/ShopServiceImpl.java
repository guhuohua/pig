package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.model.PersonMangeParam;
import com.ch.model.SysGoodAvdModel;
import com.ch.model.SysShopInfoParam;
import com.ch.service.ShopService;
import com.ch.service.SolrService;
import com.ch.util.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GoodsAdvertMapper goodsAdvertMapper;


    @Override
    public ResponseResult PersonMangeList(PersonMangeParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andShopIdEqualTo(Long.valueOf(sysUser.getShopId()));
        if (BeanUtils.isNotEmpty(param.getUsername())) {
            criteria.andUsernameLike(param.getUsername());
        }
        if (BeanUtils.isNotEmpty(param.getPhone())) {
            criteria.andPhoneEqualTo(param.getPhone());
        }
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(sysUsers);
        for (SysUser user:sysUserPageInfo.getList()) {
            user.setPassword("");
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(user.getUserId()).andShopIdEqualTo(user.getShopId());
            List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
            for (SysUserRole sysUserRole:sysUserRoles) {
                SysRoleExample sysRoleExample = new SysRoleExample();
                sysRoleExample.createCriteria().andRoleIdEqualTo(sysUserRole.getRoleId()).andShopIdEqualTo(user.getShopId());
                List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
                if (sysRoles.size() > 0) {
                    user.setRoleName(sysRoles.get(0).getRoleName());
                }
            }
        }
        result.setData(sysUserPageInfo);
        return result;
    }

    @Override
    public ResponseResult PersonMange(PersonMangeParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isNotEmpty(param.getUserId())) {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andPhoneEqualTo(param.getPhone()).andUserIdNotEqualTo(param.getUserId());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if (sysUsers.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            SysUserExample sysUserExample2 = new SysUserExample();
            sysUserExample2.createCriteria().andUsernameEqualTo(param.getUsername()).andUserIdNotEqualTo(param.getUserId());
            List<SysUser> sysUsers2 = sysUserMapper.selectByExample(sysUserExample2);
            if (sysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            SysUserExample userExample = new SysUserExample();
            userExample.createCriteria().andShopIdEqualTo(Long.valueOf(sysUser.getShopId())).andUserIdEqualTo(param.getUserId());
            SysUser br = sysUserMapper.selectByExample(userExample).stream().findFirst().get();
            br.setUpdateTime(new Date());
            br.setPhone(param.getPhone());
            br.setUsername(param.getUsername());
            if (BeanUtils.isNotEmpty(param.getPassword())) {
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(param.getPassword());
                br.setSalt(salt);
                br.setPassword(encodedPassword);
            }
            sysUserMapper.insert(br);
        } else {
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andPhoneEqualTo(param.getPhone());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if (sysUsers.size() > 0) {
                result.setCode(500);
                result.setError("手机号不能重复");
                result.setError_description("手机号不能重复");
                return result;
            }
            SysUserExample sysUserExample2 = new SysUserExample();
            sysUserExample2.createCriteria().andUsernameEqualTo(param.getUsername());
            List<SysUser> sysUsers2 = sysUserMapper.selectByExample(sysUserExample2);
            if (sysUsers2.size() > 0) {
                result.setCode(500);
                result.setError("用户名不能重复");
                result.setError_description("用户名不能重复");
                return result;
            }
            SysUser user = new SysUser();
            user.setUsername(param.getUsername());
            user.setShopId(sysUser.getShopId());
            String salt = UUID.randomUUID().toString();
            PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
            String encodedPassword = encoderMd5.encode(param.getPassword());
            user.setSalt(salt);
            user.setPassword(encodedPassword);
            user.setPhone(param.getPhone());
            user.setCreateTime(new Date());
            user.setStatus(0);
            sysUserMapper.insert(user);
        }
        return result;
    }

    @Override
    public ResponseResult resetPassword(Integer userId, Integer tokenUserId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(tokenUserId);
        sysUserMapper.resetPassword(userId, sysUser.getShopId());
        return result;
    }

    @Override
    public ResponseResult shopInfo(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        Shop shop = shopMapper.selectByPrimaryKey(sysUser.getShopId());
        SysShopInfoParam sysShopInfoParam = new SysShopInfoParam();
        modelMapper.map(shop, sysShopInfoParam);
        GoodsAdvertExample goodsAdvertExample = new GoodsAdvertExample();
        goodsAdvertExample.createCriteria().andShopIdEqualTo(sysUser.getShopId());
        List<GoodsAdvert> goodsAdverts = goodsAdvertMapper.selectByExample(goodsAdvertExample);
        List<SysGoodAvdModel> sysGoodAvdModels = new ArrayList<>();
        for (GoodsAdvert goodsAdvert:goodsAdverts) {
            SysGoodAvdModel sysGoodAvdModel = new SysGoodAvdModel();
            modelMapper.map(goodsAdvert, sysGoodAvdModel);
            sysGoodAvdModels.add(sysGoodAvdModel);
        }
        sysShopInfoParam.setSysGoodAvdModels(sysGoodAvdModels);
        result.setData(sysShopInfoParam);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult mange(SysShopInfoParam sysShopInfoParam) {
        ResponseResult result = new ResponseResult();
        Shop shop = new Shop();
        modelMapper.map(sysShopInfoParam, shop);
        shopMapper.updateByPrimaryKey(shop);
        for (SysGoodAvdModel sysGoodAvdModel:sysShopInfoParam.getSysGoodAvdModels()) {
            GoodsAdvert goodsAdvert = new GoodsAdvert();
            modelMapper.map(sysGoodAvdModel, goodsAdvert);
            goodsAdvertMapper.updateByPrimaryKey(goodsAdvert);
        }
        return result;
    }
}
