/**
 * Author: 常富文
 * Date:   2019/4/16 11:39
 * Description: 购物车
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.UserMapper;
import com.ch.dto.CarDto;
import com.ch.dto.GoodsCarDto;
import com.ch.service.ViewGoodsCarService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult addCar(HttpServletRequest req , @RequestParam Integer skuId,@RequestParam Integer num ) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            String token = req.getHeader("Authorization");
            Integer shopId = TokenUtil.getUserId(token);
            result = viewGoodsCarService.addCar(skuId, num, openId, shopId);
        } catch (Exception e) {
            LOGGER.error("加入购物车失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("加入购物车失败");
        }
        return result;

    }


    @GetMapping("showCar")
    @ApiOperation("购物车")
    public ResponseResult showCar(HttpServletRequest req ) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            String token = req.getHeader("Authorization");
            Integer shopId = TokenUtil.getUserId(token);
            result = viewGoodsCarService.showCar(openId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示购物车失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示购物车失败");
        }
        return result;
    }


    @PostMapping ("updateCar")
    @ApiOperation("修改购物车")
    public ResponseResult updateCar(HttpServletRequest req, @RequestBody GoodsCarDto goodsCarDto) {
        ResponseResult result = new ResponseResult();
        try {
            String openId = req.getHeader("openId");
            String token = req.getHeader("Authorization");
            Integer shopId = TokenUtil.getUserId(token);
            result = viewGoodsCarService.updateCar(goodsCarDto.getCarDto());
        } catch (Exception e) {
            LOGGER.error("修改购物车失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("修改购物车失败");
        }
        return result;

    }


    @PostMapping ("deleCar")
    @ApiOperation("删除购物车")
    public ResponseResult deleCar(HttpServletRequest req, @RequestBody GoodsCarDto goodsCarDto) {
        ResponseResult result = new ResponseResult();
        try {
           /* String openId = req.getHeader("openId");
            String token = req.getHeader("Authorization");
            Integer shopId = TokenUtil.getUserId(token);*/
            result = viewGoodsCarService.deleCar(goodsCarDto.getIds());
        } catch (Exception e) {
            LOGGER.error("删除购物车" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除购物车");
        }
        return result;

    }
}
