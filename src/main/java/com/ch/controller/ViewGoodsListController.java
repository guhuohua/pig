/**
 * Author: 常富文
 * Date:   2019/4/8 11:41
 * Description: 商品列表控制层
 */


package com.ch.controller;

import com.alibaba.fastjson.JSON;
import com.ch.base.ResponseResult;
import com.ch.dto.GoodsDto;
import com.ch.dto.SolrDto;
import com.ch.service.ViewGoodsListService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "展示商品列表",description = "展示商品列表")
@RequestMapping("goods")
public class ViewGoodsListController {

    @Autowired
    ViewGoodsListService viewGoodsListService;

    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsListController.class);

    @PostMapping("viewList")
    @ApiOperation("展示商品列表")
    public ResponseResult findGoodsList(@RequestBody SolrDto solrDto, HttpServletRequest req){
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
       // System.out.println("token:" + token);
       // System.out.println("SolrDto:" + JSON.toJSONString(solrDto));
        Integer shopId = TokenUtil.getUserId(token);
       // System.out.println("shopId" + shopId);
       // Integer shopId = 1;
        try {
            result = viewGoodsListService.findGoodsList(solrDto, shopId);
        } catch (Exception e) {
            LOGGER.error("展示商品列表" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示商品列表");
        }
        return result;
    }



    @GetMapping("showCondition")
    @ApiOperation("展示搜索项")
    public ResponseResult showCondition( HttpServletRequest req,@RequestParam String condition){
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        //Integer shopId = 1;
        try {
            result = viewGoodsListService.shouCondition(condition,shopId);
        } catch (Exception e) {
            LOGGER.error("展示搜索项失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示搜索项失败");
        }
        return result;
    }

    @GetMapping("spikeGoodsList")
    @ApiOperation("秒杀列表")
    public ResponseResult spikeGoodsList( HttpServletRequest req, Integer pageNum, Integer pageSize){
        ResponseResult result = new ResponseResult();
        try {
            result = viewGoodsListService.spikeGoodsList(pageNum,pageSize);
        } catch (Exception e) {
            LOGGER.error("展示搜索项失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示搜索项失败");
        }
        return result;
    }
}
