package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.GoodsParam;
import com.ch.model.SysGoodsModel;
import com.ch.model.SysGoodsParam;
import com.ch.service.SysGoodsService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_goods")
public class SysGoodsController {

    private static final Logger LOGGER = LogManager.getLogger(SysGoodsController.class);

    @Autowired
    SysGoodsService sysGoodsService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, @RequestParam SysGoodsParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsService.goodsList(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取商品列表失败");
        }
        return result;
    }

    @PostMapping("mange/{param}")
    public ResponseResult mange(HttpServletRequest req, @PathVariable("param") SysGoodsModel param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
//            result = sysGoodsService.goodsMange(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("管理商品失败");
        }
        return result;
    }

    @PostMapping("goods_status/{param}")
    @ApiOperation("上下架商品")
    public ResponseResult goodsStatus(HttpServletRequest req, @PathVariable("param") GoodsParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysGoodsService.goodsStatus(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("修改商品状态失败");
        }
        return result;
    }

    @PostMapping("delete_goods/{goodsId}")
    @ApiOperation("删除商品")
    public ResponseResult deleteGoods(HttpServletRequest req, @PathVariable("goodsId") Integer goodsId) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysGoodsService.deleteGoods(goodsId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除商品");
        }
        return result;
    }
}
