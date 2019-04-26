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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
