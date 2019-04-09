package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.PersonMangeParam;
import com.ch.service.ShopService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shop")
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
}
