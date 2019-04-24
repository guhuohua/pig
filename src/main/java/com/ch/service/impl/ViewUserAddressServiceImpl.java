/**
 * Author: 常富文
 * Date:   2019/4/19 15:54
 * Description: 地址管理
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.UserAddressMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.UserAddress;
import com.ch.entity.UserInfo;
import com.ch.entity.UserInfoExample;
import com.ch.service.ViewUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewUserAddressServiceImpl implements ViewUserAddressService {
    @Autowired
    UserAddressMapper userAddressMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ResponseResult deleteByPrimaryKey(Integer id) {

        userAddressMapper.deleteByPrimaryKey(id);

       ResponseResult result = new ResponseResult();

        return result;
    }

    @Override
    public ResponseResult insert(UserAddress record,String openId,Integer shopId) {

            record.setShopId(shopId);
            UserInfoExample example = new UserInfoExample();
            UserInfoExample.Criteria criteria = example.createCriteria();
            criteria.andOpenIdEqualTo(openId);
            List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
            UserInfo userInfo = null;
            if (userInfos.size() > 0) {
                userInfo = userInfos.get(0);
            }
            record.setUserId(userInfo.getId());
            userAddressMapper.insert(record);

        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult updateByPrimaryKey(UserAddress record,String openId,Integer shopId) {

           record.setShopId(shopId);
           UserInfoExample example = new UserInfoExample();
           UserInfoExample.Criteria criteria = example.createCriteria();
           criteria.andOpenIdEqualTo(openId);
           List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
           UserInfo userInfo = null;
           if (userInfos.size() > 0) {
               userInfo = userInfos.get(0);
           }
           record.setUserId(userInfo.getId());
           userAddressMapper.updateByPrimaryKey(record);
        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult findById(Integer id) {
        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(id);
        ResponseResult result = new ResponseResult();
        result.setData(userAddress);
        return  result;
    }
}
