/**
 * Author: 常富文
 * Date:   2019/4/4 16:16
 * Description: 商品详情
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.ViewGoodsDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goods")
@Api(value = "商品详情",description = "商品详情")
public class ViewGoodsDetailsController {


   @Autowired
   ViewGoodsDetailsService viewGoodsDetailsService;
   private static final Logger LOGGER = LogManager.getLogger(ViewGoodsDetailsController.class);

   @ApiOperation("展示商品详情")
   @GetMapping("details")
   public ResponseResult findGoodsDetailsByGoodsId(Integer goodsId){
       ResponseResult result = new ResponseResult();
       try {
           result = viewGoodsDetailsService.findGoodsDetailsByGoodsId(goodsId);
       } catch (Exception e) {
           LOGGER.error("展示商品详情" + e.getMessage(), e);
           result.setCode(500);
           result.setError(e.getMessage());
           result.setError_description("展示商品详情");
       }
       return result;
   }
}
