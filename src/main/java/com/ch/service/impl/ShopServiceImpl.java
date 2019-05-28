package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.SysShopInfoDTO;
import com.ch.entity.*;
import com.ch.model.PersonMangeParam;
import com.ch.model.SysGoodAvdModel;
import com.ch.model.SysShopInfoParam;
import com.ch.model.UserDto;
import com.ch.service.ShopService;
import com.ch.service.SolrService;
import com.ch.service.SysUserService;
import com.ch.util.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;

    @Autowired
    SysUserService sysUserService;


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
            sysUserRoleExample.createCriteria().andUserIdEqualTo(user.getId()).andShopIdEqualTo(user.getShopId());
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
    @Transactional
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
            br.setStatus(param.getStatus());
            if (BeanUtils.isNotEmpty(param.getPassword())) {
                String salt = UUID.randomUUID().toString();
                PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
                String encodedPassword = encoderMd5.encode(param.getPassword());
                br.setSalt(salt);
                br.setPassword(encodedPassword);
            }
            sysUserMapper.updateByPrimaryKey(br);
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
            user.setStatus(param.getStatus());
            sysUserMapper.insert(user);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(4);
            sysUserRole.setShopId(sysUser.getShopId());
            sysUserRole.setUserId(user.getId());
            int insert = sysUserRoleMapper.insert(sysUserRole);
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
    public ResponseResult shopInfo(Integer userId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        UserDto dto = sysUserService.findById(userId);
        Set<String> roles = dto.getRoles();
        boolean flg = false;
        for (String role : roles) {
            if ("超级管理员".equals(role)) {
                flg = true;
            }
        }
        Shop shop = new Shop();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysShopInfoDTO sysShopInfoDTO = new SysShopInfoDTO();
        if (BeanUtils.isNotEmpty(shopId)) {
            shop = shopMapper.selectByPrimaryKey(shopId);
        } else {
            shop = shopMapper.selectByPrimaryKey(sysUser.getShopId());
        }
        if (BeanUtils.isNotEmpty(shop)) {
            sysShopInfoDTO.setShopId(shop.getId());
            sysShopInfoDTO.setShopName(shop.getTitle());
            sysShopInfoDTO.setShopStatus(shop.getStatus());
            sysShopInfoDTO.setShopTel(shop.getTel());
            sysShopInfoDTO.setShopServiceBeginDate(shop.getStartTime().getTime());
            sysShopInfoDTO.setShopServiceEndDate(shop.getEndTime().getTime());

            SysUser admin = sysUserMapper.selectByPrimaryKey(shop.getShopAccountId());
            sysShopInfoDTO.setAdminName(admin.getUsername());
            sysShopInfoDTO.setAdminStatus(admin.getStatus());
            sysShopInfoDTO.setAdminTel(admin.getPhone());


            ShopMiniProgramExample shopMiniProgramExample = new ShopMiniProgramExample();
            ShopMiniProgramExample.Criteria criteria = shopMiniProgramExample.createCriteria();
            if (BeanUtils.isNotEmpty(shopId)) {
                criteria.andShopIdEqualTo(shopId);
            } else {
                criteria.andShopIdEqualTo(sysUser.getShopId());
            }
            List<ShopMiniProgram> shopMiniPrograms = shopMiniProgramMapper.selectByExample(shopMiniProgramExample);
            if (shopMiniPrograms.stream().findFirst().isPresent() && flg) {
                ShopMiniProgram shopMiniProgram = shopMiniPrograms.stream().findFirst().get();
                sysShopInfoDTO.setAppId(shopMiniProgram.getAppId());
                sysShopInfoDTO.setSecret(shopMiniProgram.getSecret());
                sysShopInfoDTO.setMchId(shopMiniProgram.getMchIdd());
                sysShopInfoDTO.setKey(shopMiniProgram.getAppKey());
                sysShopInfoDTO.setBackUrl(shopMiniProgram.getBackUrl());
            }

            List<SysGoodAvdModel> sysGoodAvdModels = new ArrayList<>();
            GoodsAdvertExample goodsAdvertExample = new GoodsAdvertExample();
            GoodsAdvertExample.Criteria criteria1 = goodsAdvertExample.createCriteria();
            if (BeanUtils.isNotEmpty(shopId)) {
                criteria1.andShopIdEqualTo(shopId);
            } else {
                criteria1.andShopIdEqualTo(sysUser.getShopId());
            }
            List<GoodsAdvert> goodsAdverts = goodsAdvertMapper.selectByExample(goodsAdvertExample);
            goodsAdverts.stream().sorted(Comparator.comparing(GoodsAdvert::getSortOrder)).collect(Collectors.toList());
            for (int i = 0; i < goodsAdverts.size(); i++) {
                SysGoodAvdModel sysGoodAvdModel = new SysGoodAvdModel();
                sysGoodAvdModel.setId(goodsAdverts.get(i).getId());
                sysGoodAvdModel.setPictureUrl(goodsAdverts.get(i).getPictureUrl());
                sysGoodAvdModel.setGoodsId(goodsAdverts.get(i).getGoodsId());
                sysGoodAvdModels.add(sysGoodAvdModel);
            }
            sysShopInfoDTO.setSysGoodAvdModels(sysGoodAvdModels);
        }
        result.setData(sysShopInfoDTO);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult mange(SysShopInfoDTO sysShopInfoDTO, Integer userId) {
        ResponseResult result = new ResponseResult();
        UserDto dto = sysUserService.findById(userId);
        Set<String> roles = dto.getRoles();
        boolean flg = false;
        for (String role : roles) {
            if ("超级管理员".equals(role)) {
                flg = true;
            }
        }
        if (flg && BeanUtils.isNotEmpty(sysShopInfoDTO.getShopId())) {
            Shop shop = shopMapper.selectByPrimaryKey(sysShopInfoDTO.getShopId());
            shop.setTitle(sysShopInfoDTO.getShopName());
            shop.setTel(sysShopInfoDTO.getShopTel());
            shop.setStartTime(new Date(sysShopInfoDTO.getShopServiceBeginDate()));
            shop.setEndTime(new Date(sysShopInfoDTO.getShopServiceEndDate()));
            shop.setStatus(sysShopInfoDTO.getShopStatus());
            shopMapper.updateByPrimaryKey(shop);

            SysUser admin = sysUserMapper.selectByPrimaryKey(shop.getShopAccountId());
            admin.setUsername(sysShopInfoDTO.getAdminName());
            admin.setPhone(sysShopInfoDTO.getAdminTel());
            admin.setStatus(sysShopInfoDTO.getAdminStatus());
            sysUserMapper.updateByPrimaryKey(admin);

            ShopMiniProgramExample shopMiniProgramExample = new ShopMiniProgramExample();
            shopMiniProgramExample.createCriteria().andShopIdEqualTo(sysShopInfoDTO.getShopId());
            List<ShopMiniProgram> shopMiniPrograms = shopMiniProgramMapper.selectByExample(shopMiniProgramExample);
            if (shopMiniPrograms.stream().findFirst().isPresent()) {
                ShopMiniProgram shopMiniProgram = shopMiniPrograms.stream().findFirst().get();
                shopMiniProgram.setShopId(sysShopInfoDTO.getShopId());
                shopMiniProgram.setAppId(sysShopInfoDTO.getAppId());
                shopMiniProgram.setSecret(sysShopInfoDTO.getSecret());
                shopMiniProgram.setMchIdd(sysShopInfoDTO.getMchId());
                shopMiniProgram.setAppKey(sysShopInfoDTO.getKey());
                shopMiniProgram.setBackUrl(sysShopInfoDTO.getBackUrl());
                shopMiniProgramMapper.updateByPrimaryKey(shopMiniProgram);
            }

            GoodsAdvertExample goodsAdvertExample = new GoodsAdvertExample();
            goodsAdvertExample.createCriteria().andShopIdEqualTo(sysShopInfoDTO.getShopId());
            goodsAdvertMapper.deleteByExample(goodsAdvertExample);

            List<SysGoodAvdModel> sysGoodAvdModels = sysShopInfoDTO.getSysGoodAvdModels();
            for (int i = 0; i < sysGoodAvdModels.size(); i++) {
                GoodsAdvert goodsAdvert = new GoodsAdvert();
                goodsAdvert.setCreateTime(new Date());
                goodsAdvert.setShopId(sysShopInfoDTO.getShopId());
                goodsAdvert.setStatus(0);
                goodsAdvert.setSortOrder(i);
                goodsAdvert.setGoodsId(sysGoodAvdModels.get(i).getGoodsId());
                goodsAdvert.setPictureUrl(sysGoodAvdModels.get(i).getPictureUrl());
                goodsAdvert.setPath("");
                goodsAdvertMapper.insert(goodsAdvert);
            }

        }
        if (flg && BeanUtils.isEmpty(sysShopInfoDTO.getShopId())) {

            SysUser sysUser = new SysUser();
            sysUser.setUsername(sysShopInfoDTO.getAdminName());
            sysUser.setStatus(sysShopInfoDTO.getAdminStatus());
            sysUser.setPhone(sysShopInfoDTO.getAdminTel());
            sysUser.setCreateTime(new Date());
            String salt = UUID.randomUUID().toString();
            PasswordUtil encoderMd5 = new PasswordUtil(salt, "sha-256");
            String encodedPassword = encoderMd5.encode(sysShopInfoDTO.getAdminPassword());
            sysUser.setSalt(salt);
            sysUser.setPassword(encodedPassword);
            sysUserMapper.insert(sysUser);

            Shop shop = new Shop();
            shop.setTitle(sysShopInfoDTO.getShopName());
            shop.setTel(sysShopInfoDTO.getShopTel());
            shop.setStartTime(new Date(sysShopInfoDTO.getShopServiceBeginDate()));
            shop.setEndTime(new Date(sysShopInfoDTO.getShopServiceEndDate()));
            shop.setStatus(sysShopInfoDTO.getShopStatus());
            shop.setShopAccountId(sysUser.getId());
            shopMapper.insert(shop);

            sysUser.setShopId(shop.getId());
            sysUserMapper.updateByPrimaryKey(sysUser);

            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(2);
            sysUserRole.setShopId(shop.getId());
            sysUserRoleMapper.insert(sysUserRole);

            ShopMiniProgram shopMiniProgram = new ShopMiniProgram();
            shopMiniProgram.setShopId(shop.getId());
            shopMiniProgram.setStatus(0);
            shopMiniProgram.setAppId(sysShopInfoDTO.getAppId());
            shopMiniProgram.setSecret(sysShopInfoDTO.getSecret());
            shopMiniProgram.setMchIdd(sysShopInfoDTO.getMchId());
            shopMiniProgram.setBackUrl(sysShopInfoDTO.getBackUrl());
            shopMiniProgram.setAppKey(sysShopInfoDTO.getKey());
            shopMiniProgramMapper.insert(shopMiniProgram);

            List<SysGoodAvdModel> sysGoodAvdModels = sysShopInfoDTO.getSysGoodAvdModels();
            if (BeanUtils.isNotEmpty(sysGoodAvdModels) && sysGoodAvdModels.size() > 0) {
                for (int i = 0; i < sysGoodAvdModels.size(); i++) {
                    GoodsAdvert goodsAdvert = new GoodsAdvert();
                    goodsAdvert.setCreateTime(new Date());
                    goodsAdvert.setShopId(sysShopInfoDTO.getShopId());
                    goodsAdvert.setStatus(0);
                    goodsAdvert.setSortOrder(i);
                    goodsAdvert.setGoodsId(sysGoodAvdModels.get(i).getGoodsId());
                    goodsAdvert.setPictureUrl(sysGoodAvdModels.get(i).getPictureUrl());
                    goodsAdvert.setPath("");
                    goodsAdvertMapper.insert(goodsAdvert);
                }
            }
        }
        if (BeanUtils.isNotEmpty(sysShopInfoDTO.getShopId())) {
            GoodsAdvertExample goodsAdvertExample = new GoodsAdvertExample();
            goodsAdvertExample.createCriteria().andShopIdEqualTo(sysShopInfoDTO.getShopId());
            goodsAdvertMapper.deleteByExample(goodsAdvertExample);

            List<SysGoodAvdModel> sysGoodAvdModels = sysShopInfoDTO.getSysGoodAvdModels();
            for (int i = 0; i < sysGoodAvdModels.size(); i++) {
                GoodsAdvert goodsAdvert = new GoodsAdvert();
                goodsAdvert.setCreateTime(new Date());
                goodsAdvert.setShopId(sysShopInfoDTO.getShopId());
                goodsAdvert.setStatus(0);
                goodsAdvert.setSortOrder(i);
                goodsAdvert.setGoodsId(sysGoodAvdModels.get(i).getGoodsId());
                goodsAdvert.setPictureUrl(sysGoodAvdModels.get(i).getPictureUrl());
                goodsAdvert.setPath("");
                goodsAdvertMapper.insert(goodsAdvert);
            }
        }
        return result;
    }
}
