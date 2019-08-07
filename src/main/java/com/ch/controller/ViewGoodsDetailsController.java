/**
 * Author: 常富文
 * Date:   2019/4/4 16:16
 * Description: 商品详情
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.ViewGoodsDetailsService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("goods")
@Api(value = "商品详情", description = "商品详情")
public class ViewGoodsDetailsController {


    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsDetailsController.class);
    @Autowired
    ViewGoodsDetailsService viewGoodsDetailsService;

    @ApiOperation("展示商品详情")
    @GetMapping("details")
    public ResponseResult findGoodsDetailsByGoodsId(@RequestParam Integer goodsId, @RequestParam Integer skuId, HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        //Integer userId = 6;
        try {
            result = viewGoodsDetailsService.findGoodsDetailsByGoodsId(goodsId, shopId, skuId, openId);
        } catch (Exception e) {
            LOGGER.error("展示商品详情" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示商品详情");
        }
        return result;
    }
}
