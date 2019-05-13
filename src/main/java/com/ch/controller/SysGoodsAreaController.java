package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.GoodsAreaParam;
import com.ch.model.SysRecommendParam;
import com.ch.service.SysRecommendService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_goods_recommend")
@Api(value = "商品推荐")
public class SysGoodsAreaController {

    private static final Logger LOGGER = LogManager.getLogger(SysGoodsAreaController.class);

    @Autowired
    SysRecommendService sysRecommendService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, SysRecommendParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysRecommendService.list(param, userId);
        } catch (Exception e) {
            LOGGER.error("商品推荐列表获取失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("商品推荐列表获取失败");
        }
        return result;
    }

    @PostMapping("mange")
    public ResponseResult mange(HttpServletRequest req, @RequestBody GoodsAreaParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysRecommendService.mange(param, userId);
        } catch (Exception e) {
            LOGGER.error("商品推荐列表管理失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("商品推荐列表管理失败");
        }
        return result;
    }

    @PostMapping("status")
    public ResponseResult status(HttpServletRequest req, @RequestBody GoodsAreaParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysRecommendService.status(param, userId);
        } catch (Exception e) {
            LOGGER.error("改变状态失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("改变状态失败");
        }
        return result;
    }


    @PostMapping("delete")
    public ResponseResult delete(HttpServletRequest req, @RequestBody GoodsAreaParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysRecommendService.delete(param, userId);
        } catch (Exception e) {
            LOGGER.error("删除推荐失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除推荐失败");
        }
        return result;
    }
}
