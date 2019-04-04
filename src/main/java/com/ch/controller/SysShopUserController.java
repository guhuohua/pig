package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.ShopUserParam;
import com.ch.model.SysUserParam;
import com.ch.service.SysUserMangeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopUser")
public class SysShopUserController {

    @Autowired
    SysUserMangeService sysUserMangeService;

    @PostMapping("list")
    @ApiOperation(value="获取店铺下所有人员列表")
    @ApiImplicitParam(name = "shopUserParam", value = "传入参数", required = true, dataType = "ShopUserParam")
    public ResponseResult list(@RequestBody ShopUserParam shopUserParam) {
        return sysUserMangeService.findByShopId(shopUserParam);
    }

    @PostMapping("user_mange")
    @ApiOperation(value="管理店铺下的人员新增删除")
    @ApiImplicitParam(name = "sysUserParam", value = "传入参数", required = true, dataType = "SysUserParam")
    public ResponseResult userMange(@RequestBody SysUserParam sysUserParam) {
        return sysUserMangeService.insertUser(sysUserParam);
    }

    @GetMapping("user_mange")
    @ApiOperation(value="删除人员")
    public ResponseResult deleteUser(@RequestParam Integer userId, @RequestParam Integer shopId) {
        return sysUserMangeService.deleteUser(userId, shopId);
    }
}
