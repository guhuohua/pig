package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.UserAccountFlowMapper;
import com.ch.entity.User;
import com.ch.entity.UserAccountFlow;
import com.ch.entity.UserAccountFlowExample;
import com.ch.entity.UserInfo;
import com.ch.model.PageParam;
import com.ch.service.UserAccountFlowService;
import com.ch.service.ViewUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountFlowServiceImpl implements UserAccountFlowService {

    @Autowired
    UserAccountFlowMapper userAccountFlowMapper;
    @Autowired
    ViewUserInfoService viewUserInfoService;

    @Override
    public ResponseResult list(String openId) {
        ResponseResult result = new ResponseResult();
        UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
       // PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSzie());
        UserAccountFlowExample example = new UserAccountFlowExample();
        UserAccountFlowExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userInfo.getId());
        List<UserAccountFlow> userAccountFlows = userAccountFlowMapper.selectByExample(example);
        PageInfo<UserAccountFlow> pageInfo = new PageInfo<>(userAccountFlows);
        result.setData(pageInfo);
        return result;
    }
}
