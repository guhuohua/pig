/**
 * Author: 常富文
 * Date:   2019/4/10 14:18
 * Description: 登录
 */


package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.ResponseResult;
import com.ch.dto.ShopInfo;
import com.ch.dto.UserInfos;
import com.ch.service.ViewShopInfoService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.Msg;
import com.ch.util.TokenUtil;
import com.ch.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("login")
public class ViewLoginController {
    @Autowired
    ViewShopInfoService viewShopInfoService;
    @Autowired
    ViewUserInfoService viewUserInfoService;
    //@GetMapping("userInfo")
   /* public ResponseResult parseUserInfo(String code, String appId){
        ResponseResult result = new ResponseResult();
        ShopInfo shopInfo = viewShopInfoService.findShopInfoByAppId(appId);

        JSONObject res = WXUtil.getUserOpenId(appId, shopInfo.getSecret(), code);
        String openid = res.getString("openid");
        System.out.println(openid);
        try {
            //获取微信openid
            if (openid == null) {
                //未获取到数据
                String errcode = res.getString("errcode");
                Msg.err("错误：" + errcode);
            } else {
                // 获取到唯一标识，查询数据库，获取用户信息
                UserInfos userInfos = viewUserInfoService.findByOpenId(openid);
                String token = TokenUtil.sign(userInfos.getUserId());
                result.setData(token);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return result;
    }*/

    @GetMapping("userInfo")
    public ResponseResult parseUserInfo(String appId){
        ResponseResult result = new ResponseResult();
        ShopInfo shopInfo = viewShopInfoService.findShopInfoByAppId(appId);
        System.out.println(shopInfo.getShopId());
        String token = TokenUtil.sign(shopInfo.getShopId());
       // System.out.println(token);
        result.setData(token);
        return  result;

    }


}








