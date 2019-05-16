/**
 * Author: 常富文
 * Date:   2019/4/2 16:08
 * Description: shop实现类
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.ShopMapper;
import com.ch.dao.SysShopMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.ShopParam;
import com.ch.entity.*;
import com.ch.service.SysShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysShopServiceImpl implements SysShopService {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    SysShopMapper sysShopMapper;

    @Autowired
    SysUserMapper sysUserMapper;




    public void init(){
    }


    @Override
    public Shop findShopById(Integer Id) {
        return shopMapper.selectByPrimaryKey(Id);
    }

    @Override
    public ResponseResult findAll(ShopParam shopParam) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(shopParam.getCurrentPage(), shopParam.getPageSize());
        List<Shop> shops = shopMapper.selectByExample(null);
        for (Shop shop : shops) {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(shop.getShopAccountId());
            shop.setUsername(sysUser.getUsername());
        }
        PageInfo<Shop> page = new PageInfo<>(shops);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult updateByPrimaryKey(Shop record) {
        ResponseResult result = new ResponseResult();
        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(record.getTitle());
        List<Shop> shops = shopMapper.selectByExample(example);
        if (shops.size()>0){
            result.setCode(500);
            result.setError("店铺名不能重复");
            result.setError_description("店铺名不能重复");
            return  result;
        }
        ShopExample example1 = new ShopExample();
        ShopExample.Criteria criteria1 = example.createCriteria();
        criteria.andTelEqualTo(record.getTel());
        List<Shop> shops1 = shopMapper.selectByExample(example);
        if (shops1.size()>0){
            result.setCode(500);
            result.setError("手机好不能重复");
            result.setError_description("手机号不能重复");
            return  result;
        }
        shopMapper.updateByPrimaryKey(record);
        return result;
    }

    @Override
    public ResponseResult deleteByPrimaryKey(Integer id) {
        ResponseResult result = new ResponseResult();
        Shop shop = shopMapper.selectByPrimaryKey(id);
        SysShopExample example = new SysShopExample();
        SysShopExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shop.getId());
        List<SysShop> sysShops = sysShopMapper.selectByExample(example);
        if(sysShops.size()>0){
            for (SysShop sysShop : sysShops){
                sysUserMapper.deleteByPrimaryKey(sysShop.getUserId());
            }
        }
        shopMapper.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public ResponseResult insert(Shop record) {
        ResponseResult result = new ResponseResult();
        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        System.out.println(record.getTitle());
        criteria.andTitleEqualTo(record.getTitle());
        List<Shop> shops = shopMapper.selectByExample(example);

        if (shops.size()>0){
            result.setCode(500);
            result.setError("店铺名不能重复");
            result.setError_description("店铺名不能重复");
            return  result;
        }
        ShopExample example1 = new ShopExample();
        ShopExample.Criteria criteria1 = example.createCriteria();
        criteria.andTelEqualTo(record.getTel());
        List<Shop> shops1 = shopMapper.selectByExample(example);
        if (shops1.size()>0){
            result.setCode(500);
            result.setError("手机好不能重复");
            result.setError_description("手机号不能重复");
            return  result;
        }

        shopMapper.insert(record);
        return result;
    }

    @Override
    public ResponseResult serach(ShopParam shopParam) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(shopParam.getCurrentPage(), shopParam.getPageSize());
        ShopExample example = new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        if (shopParam!=null){
            if (shopParam.getTitle()!=null && shopParam.getTitle().length()>0 ){
                criteria.andTitleLike("%"+shopParam.getTitle()+"%");
            }
            if (shopParam.getTel()!=null && shopParam.getTel().length()>0){
                criteria.andTelEqualTo(shopParam.getTel());
            }
        }
        List<Shop> shops = shopMapper.selectByExample(example);
        for (Shop shop : shops) {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(shop.getShopAccountId());
            shop.setUsername(sysUser.getUsername());
        }
        PageInfo<Shop> page = new PageInfo<>(shops);
        result.setData(page);
        return result;
    }


}
