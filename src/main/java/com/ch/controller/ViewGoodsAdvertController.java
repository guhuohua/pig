/**
 * Author: 常富文
 * Date:   2019/4/4 14:21
 * Description: 首页轮播图
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.ViewGoodsAdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("view")
@Api(value = "轮播图管理",description = "轮播图管理")
public class ViewGoodsAdvertController {

    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsAdvertController.class);

    @Autowired
    ViewGoodsAdvertService viewGoodsAdvertService;

    @GetMapping("showAdvert")
    @ApiOperation("轮播图展示")
    public ResponseResult findByCategory(Integer shopId){
        ResponseResult result = new ResponseResult();
        try {
            result = viewGoodsAdvertService.findByShopId(shopId);
        } catch (Exception e) {
            LOGGER.error("展示轮播图失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示轮播图失败");
        }
        return result;
    }
}
