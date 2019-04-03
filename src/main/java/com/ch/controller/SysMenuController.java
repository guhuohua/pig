package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.SysMenuService;
import com.ch.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/webMenu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/findTree")
    public ResponseResult findTreeMenu(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysMenuService.findSysMenu(userId);
    }
}
