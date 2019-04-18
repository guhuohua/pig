package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.BaseParam;
import com.ch.service.SysShopUserService;
import com.ch.service.SysUserAddressService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_user_mange")
@Api(value = "后台用户管理")
public class SysUserMangeController {

    private static final Logger LOGGER = LogManager.getLogger(SysUserMangeController.class);

    @Autowired
    SysShopUserService sysShopUserService;

    @Autowired
    SysUserAddressService sysUserAddressService;

    @GetMapping("list")
    @ApiOperation("用户列表")
    public ResponseResult list(HttpServletRequest req, BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysShopUserService.list(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取用户列表失败");
        }
        return result;
    }

    @GetMapping("address_list")
    @ApiOperation("收货地址列表")
    public ResponseResult addressList(HttpServletRequest req, BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysUserAddressService.list(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取用户收货地址列表失败");
        }
        return result;
    }
}
