package com.ch.controller;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.entity.UserInfo;
import com.ch.model.RedPacketParam;
import com.ch.service.ViewRedPacketService;
import com.ch.service.ViewUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("view_red_packet")
@Api("小程序红包接口")
@Slf4j
public class ViewRedPacketServiceController {

    @Autowired
    ViewRedPacketService viewRedPacketService;

    @Autowired
    ViewUserInfoService viewUserInfoService;

    @GetMapping("judgeRedPacket")
    @ApiOperation("首页判断是否存在红包")
    public ResponseResult judgeRedPacket(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            if (BeanUtils.isEmpty(openId)) {
                result.setCode(600);
                result.setError("请重新登录");
                result.setError_description("请重新登录");
                return result;
            }
            UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
            result = viewRedPacketService.judgeRedPacket(userInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("获取红包失败");
            return result;
        }
        return result;
    }

    @GetMapping("redPacketList")
    @ApiOperation("小程序红包列表")
    public ResponseResult redPacketList(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            if (BeanUtils.isEmpty(openId)) {
                result.setCode(600);
                result.setError("请重新登录");
                result.setError_description("请重新登录");
                return result;
            }
            UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
            result = viewRedPacketService.redPacketList(userInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("获取小程序红包列表 失败");
            return result;
        }
        return result;
    }

    @ApiOperation("小程序领取红包")
    @GetMapping("receiveRedPacket")
    public ResponseResult receiveRedPacket(HttpServletRequest req, @RequestParam Integer redPacketId) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            if (BeanUtils.isEmpty(openId)) {
                result.setCode(600);
                result.setError("请重新登录");
                result.setError_description("请重新登录");
                return result;
            }
            UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
            result = viewRedPacketService.receiveRedPacket(userInfo.getId(), redPacketId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("领取红包失败，请稍后重试");
            return result;
        }
        return result;
    }

    @ApiOperation("我的红包列表")
    @GetMapping("myRedPacketList")
    public ResponseResult myRedPacketList(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            if (BeanUtils.isEmpty(openId)) {
                result.setCode(600);
                result.setError("请重新登录");
                result.setError_description("请重新登录");
                return result;
            }
            UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
            result = viewRedPacketService.myRedPacketList(userInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("获取我的红包列表，请稍后重试");
            return result;
        }
        return result;
    }
}
