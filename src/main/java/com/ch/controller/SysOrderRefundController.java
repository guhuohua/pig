package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.SysOrderRefundParam;
import com.ch.model.SysRefundThroughParam;
import com.ch.service.SysOrderRefundService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_order_refund")
@Api(value = "后台订单管理")
public class SysOrderRefundController {

    private static final Logger LOGGER = LogManager.getLogger(SysOrderRefundController.class);

    @Autowired
    SysOrderRefundService sysOrderRefundService;

    @GetMapping("list")
    @ApiOperation("售后列表")
    public ResponseResult list(HttpServletRequest req, SysOrderRefundParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysOrderRefundService.list(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取售后订单列表失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("handle")
    @ApiOperation("处理售后")
    public ResponseResult handle(HttpServletRequest req, @RequestBody SysRefundThroughParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysOrderRefundService.refundHandle(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取售后订单列表失败，请稍后重试");
        }
        return result;
    }
}
