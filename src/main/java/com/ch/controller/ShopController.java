package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.SysShopInfoDTO;
import com.ch.model.PersonMangeParam;
import com.ch.model.SysShopInfoParam;
import com.ch.service.ShopService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shop")
@Api(value = "店铺管理",description = "店铺管理")
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping("person_list")
    @ApiOperation("人员管理列表")
    public ResponseResult personList(HttpServletRequest req, @RequestBody PersonMangeParam param) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return shopService.PersonMangeList(param, userId);
    }

    @PostMapping("person_mange")
    @ApiOperation("人员新增编辑")
    public ResponseResult personMange(HttpServletRequest req, @RequestBody PersonMangeParam param) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return shopService.PersonMange(param, userId);
    }

    @GetMapping("reset")
    @ApiOperation("重置密码")
    public ResponseResult resetPassword(HttpServletRequest req, @RequestBody PersonMangeParam param) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return shopService.resetPassword(param.getUserId(), userId);
    }

    @GetMapping("shop_info")
    @ApiOperation("店铺详情")
    public ResponseResult shopInfo(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return shopService.shopInfo(userId);
    }

    @PostMapping("shop_mange")
    @ApiOperation("修改店铺信息")
    public ResponseResult shopMange(HttpServletRequest req, @RequestBody SysShopInfoDTO param) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return shopService.mange(param, userId);
    }
}
