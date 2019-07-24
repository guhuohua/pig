/**
 * Author: 常富文
 * Date:   2019/4/10 14:18
 * Description: 登录
 */


package com.ch.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ch.base.ResponseResult;
import com.ch.dto.ShopInfo;
import com.ch.dto.UserInfos;
import com.ch.model.TelParam;
import com.ch.service.ViewShopInfoService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.Msg;
import com.ch.util.TokenUtil;
import com.ch.util.WXUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login")
public class ViewLoginController {


    private static final Logger LOGGER = LogManager.getLogger(ViewLoginController.class);
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
    public ResponseResult parseUserInfo(String appId) {
        ResponseResult result = new ResponseResult();
        ShopInfo shopInfo = viewShopInfoService.findShopInfoByAppId(appId);
        // System.out.println("shopinfo:"+ JSON.toJSONString(shopInfo));
        String token = TokenUtil.sign(shopInfo.getShopId());
        //System.out.println(token);
        result.setData(token);
        return result;

    }

    @GetMapping("addTel")
    public ResponseResult addTel(HttpServletRequest req, @RequestBody TelParam telParam) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        try {
            result = viewUserInfoService.addTel(openId, telParam);
        } catch (Exception e) {
            LOGGER.error("绑定手机号失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("绑定手机号失败");
        }
        return result;

    }

    @GetMapping("sign")
    public ResponseResult sign(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        try {
            result = viewUserInfoService.sign(openId);
        } catch (Exception e) {
            LOGGER.error("签到失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("签到失败");
        }
        return result;

    }

}








