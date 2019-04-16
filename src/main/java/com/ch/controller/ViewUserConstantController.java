/**
 * Author: 常富文
 * Date:   2019/4/15 15:43
 * Description: 微信授权登录
 */


package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.ResponseResult;
import com.ch.dto.UserConstant;
import com.ch.entity.User;
import com.ch.entity.UserInfo;
import com.ch.service.ViewShopInfoService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ViewUserConstantController {
    /*@Autowired
    private UserService userService;*/
    @Autowired
    ViewShopInfoService viewShopInfoService;
    @Autowired
    ViewUserInfoService viewUserInfoService;

    @PostMapping("/me/login")
    public ResponseResult user_login(
            @RequestParam("code") String code,
            @RequestParam("userHead") String userHead,
            @RequestParam("userName") String userName,
            @RequestParam("userGender") String userGender,
            @RequestParam("userCity") String userCity,
            @RequestParam("userProvince") String userProvince
    ){
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstant.WX_LOGIN_APPID);
        param.put("secret", UserConstant.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstant.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpRequestUtil.doGet(UserConstant.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
        User user = viewUserInfoService.findUserByOpenId(open_id);
        if(user != null){
            user.setUpdateTime(new Date());
            viewUserInfoService.updateByPrimaryKey(user);
        }else{
            UserInfo insert_user = viewUserInfoService.findByUserId(user.getUserId());
            //User insert_user = new User();
            insert_user.setUserHead(userHead);
            insert_user.setNickname(userName);
            insert_user.setGender(userGender);
            insert_user.setUpdateTime(new Date());
            insert_user.setUserCity(userCity);
            insert_user.setPovince(userProvince);
            user.setWxOpenid(open_id);
            System.out.println("insert_user:"+insert_user.toString());
            // 添加到数据库
            viewUserInfoService.insert(insert_user);

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


