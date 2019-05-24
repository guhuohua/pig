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
import java.util.List;

@RestController
@RequestMapping("sys_goods")
public class SysGoodsController {

    private static final Logger LOGGER = LogManager.getLogger(SysGoodsController.class);

    @Autowired
    SysGoodsService sysGoodsService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, @ModelAttribute SysGoodsParam param) {
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

    @GetMapping("findById")
    public ResponseResult findById(HttpServletRequest req, @RequestParam Integer goodsId) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsService.findById(goodsId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询商品详情失败");
        }
        return result;
    }

    @PostMapping("mange")
    public ResponseResult mange(HttpServletRequest req, @RequestBody SysGoodsModel param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysGoodsService.mange(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("管理商品失败");
        }
        return result;
    }

    @PostMapping("goods_status")
    @ApiOperation("上下架商品")
    public ResponseResult goodsStatus(HttpServletRequest req, @RequestBody GoodsParam param) {
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

    @GetMapping("delete_goods")
    @ApiOperation("删除商品")
    public ResponseResult deleteGoods(HttpServletRequest req, @RequestParam Integer goodsId) {
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

    @GetMapping("delete_sku")
    @ApiOperation("删除商品")
    public ResponseResult deleteSku(HttpServletRequest req, @RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysGoodsService.deleteSku(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除商品");
        }
        return result;
    }

    @GetMapping("sku_list")
    @ApiOperation("发布商品的规格列表")
    public ResponseResult skuList(HttpServletRequest req, @RequestParam List<Integer> categoryIds, @RequestParam Integer goodsId) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsService.skuList(categoryIds, userId, goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取规格列表失败");
        }
        return result;
    }
}
