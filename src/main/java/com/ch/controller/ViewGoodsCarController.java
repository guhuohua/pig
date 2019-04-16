/**
 * Author: 常富文
 * Date:   2019/4/16 11:39
 * Description: 购物车
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.UserMapper;
import com.ch.entity.User;
import com.ch.service.ViewGoodsCarService;
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
@RequestMapping("goods")
@Api(value = "购物车", description = "购物车")
public class ViewGoodsCarController {

    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsCarController.class);

    @Autowired
    ViewGoodsCarService viewGoodsCarService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("car")
    @ApiOperation("购物车")
    public ResponseResult addCar(Integer skuAtrId, Integer num, HttpServletRequest req) {
        ResponseResult result = new ResponseResult();

        try {
            String openId = req.getHeader("openId");
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);

            User user = userMapper.selectByPrimaryKey(userId);

            result = viewGoodsCarService.addCar(skuAtrId, num, openId, user.getShopId());
        } catch (Exception e) {
            LOGGER.error("加入购物车失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("加入购物车失败");
        }
        return result;

    }
}
