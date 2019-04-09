package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.SysGoodsParam;
import com.ch.service.SysGoodsService;
import com.ch.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_goods")
public class SysGoodsController {

    @Autowired
    SysGoodsService sysGoodsService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, @RequestParam SysGoodsParam param) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysGoodsService.goodsList(param, userId);
    }
}
