package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.GoodsType;
import com.ch.model.SysCategoryParam;
import com.ch.service.SysGoodsCategoryService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_goods_category")
public class SysGoodsCategoryController {

    private static final Logger LOGGER = LogManager.getLogger(SysGoodsCategoryController.class);

    @Autowired
    SysGoodsCategoryService sysGoodsCategoryService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.list(userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取商品类目失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("mange")
    public ResponseResult mange(HttpServletRequest req, @RequestBody GoodsType goodsType) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.mange(goodsType, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("管理商品类目失败");
        }
        return result;
    }

    @GetMapping("delete")
    public ResponseResult delete(HttpServletRequest req, @RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.delete(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("管理商品类目失败");
        }
        return result;
    }

    @GetMapping("findOneCategory")
    public ResponseResult findOneCategory(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.findOneCategory(userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询一级类目失败");
        }
        return result;
    }

    @GetMapping("findById")
    public ResponseResult findById(HttpServletRequest req, @RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.findById(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询失败");
        }
        return result;
    }

    @PostMapping("updateStatus")
    public ResponseResult updateStatus(HttpServletRequest req, @RequestBody SysCategoryParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsCategoryService.updateStatus(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("管理商品类目失败");
        }
        return result;
    }
}
