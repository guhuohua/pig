package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.SysGoodsSkuParam;
import com.ch.service.SysGoodsSkuService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_sku")
@Api(value = "规格管理")
public class SysGoodsSkuController {

    private static final Logger LOGGER = LogManager.getLogger(SysGoodsCategoryController.class);

    @Autowired
    SysGoodsSkuService sysGoodsSkuService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, @RequestParam String name, @RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsSkuService.list(name, userId, currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取商品规格失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("mange")
    public ResponseResult mange(HttpServletRequest req, @RequestBody SysGoodsSkuParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsSkuService.mange(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取商品规格失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("delete")
    public ResponseResult delete(HttpServletRequest req, @RequestBody Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysGoodsSkuService.delete(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取商品规格失败，请稍后重试");
        }
        return result;
    }
}
