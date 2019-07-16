/**
 * Author: 常富文
 * Date:   2019/4/15 15:43
 * Description: 微信授权登录
 */


package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.ResponseResult;
import com.ch.dao.UserInfoMapper;
import com.ch.dao.UserMapper;
import com.ch.dto.ShopInfo;
import com.ch.dto.UserConstant;
import com.ch.dto.UserDto;
import com.ch.entity.UserInfo;
import com.ch.entity.UserInfoExample;
import com.ch.enums.MemberEnum;
import com.ch.service.ViewShopInfoService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.HttpRequestUtil;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("weixin")
@Api(value = "微信授权登录")
public class ViewUserConstantController {

    @Autowired
    ViewShopInfoService viewShopInfoService;
    @Autowired
    ViewUserInfoService viewUserInfoService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @PostMapping("loginInfo1")
    public ResponseResult user_login(HttpServletRequest req, @RequestBody UserDto userDto) {

        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        //User user = userMapper.selectByPrimaryKey(userId);
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(userDto.getOpenId());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        if (userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            /*userInfo.setUserHead(userDto.getUserHead());
            userInfo.setNickname(userDto.getUserName());
            userInfo.setPovince(userDto.getUserProvince());
            userInfo.setGender(userDto.getUserGender());
            userInfo.setUserCity(userDto.getUserCity());*/
            userInfo.setUpdateTime(new Date());
           /* userInfo.setShopId(shopId);
            userInfo.setOpenId(userDto.getOpenId());*/
            userInfoMapper.updateByPrimaryKey(userInfo);

        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserHead(userDto.getUserHead());
            userInfo.setNickname(userDto.getUserName());
            userInfo.setPovince(userDto.getUserProvince());
            userInfo.setGender(userDto.getUserGender());
            userInfo.setUserCity(userDto.getUserCity());
            userInfo.setCreateTime(new Date());
            //userInfo.setUpdateTime(new Date());
            userInfo.setShopId(shopId);
            userInfo.setOpenId(userDto.getOpenId());
            userInfo.setIntegral(0);
            userInfo.setMember("BRONZE");
            userInfoMapper.insert(userInfo);
        }
        ResponseResult result = new ResponseResult();
        return result;
    }

    @GetMapping("login")
    public ResponseResult user_login(String code, String appId) {
        // 配置请求参数
        ShopInfo shopInfo = viewShopInfoService.findShopInfoByAppId(appId);
        Map<String, String> param = new HashMap<>();
        param.put("appid", appId);
        param.put("secret", shopInfo.getSecret());
        param.put("js_code", code);
        param.put("grant_type", UserConstant.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpRequestUtil.doGet(UserConstant.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        //System.out.println(jsonObject);
        String session_key = null;
        String open_id = null;
        if (jsonObject != null) {
            // 获取参数返回的
            session_key = jsonObject.get("session_key").toString();
           // System.out.println(session_key);

            open_id = jsonObject.get("openid").toString();
           // System.out.println(open_id);
           /* UserInfo userInfo = new UserInfo();
           userInfo.setOpenId(open_id);
           userInfo.setCreateTime(new Date());
           userInfoMapper.insert(userInfo);*/
        }
        // 封装返回小程序
        Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", open_id);
        ResponseResult result1 = new ResponseResult();
        result1.setData(result);
        return result1;

    }

}


