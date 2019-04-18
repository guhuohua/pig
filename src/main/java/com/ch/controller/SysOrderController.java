package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysOrderParam;
import com.ch.service.SysOrderService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_order")
@Api(value = "后台订单管理")
public class SysOrderController {

    private static final Logger LOGGER = LogManager.getLogger(SysOrderController.class);

    @Autowired
    SysOrderService sysOrderService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, SysOrderParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysOrderService.list(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取订单列表失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("deliverGoods")
    public ResponseResult deliverGoods(HttpServletRequest req, @RequestBody DeliverGoodsParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysOrderService.deliverGoods(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("发货失败");
        }
        return result;
    }
}
