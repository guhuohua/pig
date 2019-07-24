/**
 * Author: 常富文
 * Date:   2019/4/15 15:43
 * Description: 微信授权登录
 */


package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.ResponseResult;
import com.ch.dao.MemberLevelMapper;
import com.ch.dao.SignMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.dao.UserMapper;
import com.ch.dto.LoginDTO;
import com.ch.dto.ShopInfo;
import com.ch.dto.UserConstant;
import com.ch.dto.UserDto;
import com.ch.entity.*;
import com.ch.enums.MemberEnum;
import com.ch.service.ViewShopInfoService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.HttpRequestUtil;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

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
    @Autowired
    SignMapper signMapper;
    @Autowired
    MemberLevelMapper memberLevelMapper;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping("loginInfo1")
    public ResponseResult user_login(HttpServletRequest req, @RequestBody UserDto userDto) {
        ResponseResult result = new ResponseResult();
        LoginDTO loginDTO = new LoginDTO();
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        //User user = userMapper.selectByPrimaryKey(userId);
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(userDto.getOpenId());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo =null;
        if (userInfos.size() > 0) {
             userInfo = userInfos.get(0);
            Date endOfDay = getEndOfDay(new Date());
            Date startDay = getStartOfDay(new Date());
            SignExample example1 = new SignExample();
            SignExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andSignDateLessThan(endOfDay);
            criteria1.andSignDateGreaterThan(startDay);
            List<Sign> signs = signMapper.selectByExample(example1);
            if (signs.size()==0){
                userInfo.setSignStatus(0);

            }
            userInfo.setUpdateTime(new Date());
            userInfoMapper.updateByPrimaryKey(userInfo);

        } else {
            userInfo = new UserInfo();
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
            userInfo.setMember("TOURIST");
            userInfo.setSignStatus(0);
            userInfo.setIntegral(0);
            userInfo.setUseIntegral(0);
            userInfoMapper.insert(userInfo);
        }
        MemberLevelExample example1 = new MemberLevelExample();
        MemberLevelExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andNameEqualTo(userInfo.getMember());
        List<MemberLevel> memberLevels = memberLevelMapper.selectByExample(example1);
        MemberLevel memberLevel = memberLevels.get(0);
        modelMapper.map(userInfo, loginDTO);
        loginDTO.setDiscount(memberLevel.getDiscount());
        result.setData(loginDTO);
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

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;

    }



}


