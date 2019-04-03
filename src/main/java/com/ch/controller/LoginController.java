/**
 * Author: 常富文
 * Date:   2019/4/3 11:33
 * Description: 登录控制层
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.UserDto;
import com.ch.service.SysUserService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webShop")
@Api(value = "登录接口")
public class LoginController {
   @Autowired
    SysUserService sysUserService;
    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
   @ApiOperation("登录")
   @RequestMapping("/login")
    public ResponseResult login(@RequestBody UserDto dto){
       ResponseResult result = new ResponseResult();
       try {
           result = sysUserService.login(dto);
           if (result.getCode() == 0) {
               UserDto data = (UserDto) result.getData();
               String token = TokenUtil.sign(data.getUserId());
               result.setData(token);
           }
       } catch (Exception e) {
           result.setCode(404);
           result.setError(e.getMessage());
           result.setError_description("登录失败，请稍后再试");
       }
       return result;
    }
}
