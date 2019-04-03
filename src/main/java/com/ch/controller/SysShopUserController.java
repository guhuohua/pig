package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.ShopUserParam;
import com.ch.service.SysUserMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopUser")
public class SysShopUserController {

    @Autowired
    SysUserMangeService sysUserMangeService;

    @PostMapping("list")
    public ResponseResult list(@RequestBody ShopUserParam shopUserParam) {
        return sysUserMangeService.findByShopId(shopUserParam);
    }
}
