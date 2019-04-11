/**
 * Author: 常富文
 * Date:   2019/4/8 11:41
 * Description: 商品列表控制层
 */


package com.ch.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        //String token = req.getHeader("Authorization");
        //Integer userId = TokenUtil.getUserId(token);
        Integer userId = 6;
        try {
            result = viewGoodsListService.findGoodsList(solrDto, userId);
        } catch (Exception e) {
            LOGGER.error("展示商品列表" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示商品列表");
        }
        return result;
    }
}
