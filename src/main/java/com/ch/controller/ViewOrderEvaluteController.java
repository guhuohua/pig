/**
 * Author: 常富文
 * Date:   2019/4/26 10:39
 * Description: 商品评价
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsEvaluation;
import com.ch.service.ViewOrderEvaluteService;
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
@Api(value = "商品评价",description = "商品评价")
public class ViewOrderEvaluteController {

    private static final Logger LOGGER = LogManager.getLogger(ViewOrderEvaluteController.class);
    @Autowired
    ViewOrderEvaluteService viewOrderEvaluteService;

    @PostMapping("evalution")
    @ApiOperation("商品评价")
    public ResponseResult evalute(HttpServletRequest req, @RequestBody GoodsEvaluation goodsEvaluation){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.addEvalute(goodsEvaluation,shopId,openId);
        } catch (Exception e) {
            LOGGER.error("商品评价失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("商品评价失败");
        }
        return result;
    }


    @GetMapping("showGoodEvluate")
    @ApiOperation("展示好评")
    public ResponseResult showGoodEvluate(HttpServletRequest req, @RequestParam Integer goodsId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.showGoodEvluate(goodsId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示好评失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示好评失败");
        }
        return result;
    }

    @GetMapping("showBadEvluate")
    @ApiOperation("展示差评")
    public ResponseResult showBadEvluate(HttpServletRequest req, @RequestParam Integer goodsId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.showBadEvluate(goodsId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示差评失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示差评失败");
        }
        return result;
    }


    @GetMapping("showMediumEvluate")
    @ApiOperation("展示中评")
    public ResponseResult showMediumEvluate(HttpServletRequest req, @RequestParam Integer goodsId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.showMediumEvluate(goodsId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示中评失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示中评失败");
        }
        return result;
    }


    @GetMapping("showAllEvluate")
    @ApiOperation("展示全部评价")
    public ResponseResult showAllEvluate(HttpServletRequest req,@RequestParam Integer goodsId ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.showAllEvluate(goodsId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示全部评价失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示全部评价失败");
        }
        return result;
    }



    @GetMapping("showMyEvluate")
    @ApiOperation("展示我的评价")
    public ResponseResult showMyEvluate(HttpServletRequest req ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderEvaluteService.showMyEvluate(shopId,openId);
        } catch (Exception e) {
            LOGGER.error("展示我的评价失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示我的评价失败");
        }
        return result;
    }
}
