package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.DeliverGoodsParam;
import com.ch.dto.SysOrderParam;
import com.ch.handler.ActiveMQHandler;
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

    @Autowired
    ActiveMQHandler activeMQHandler;

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

    @GetMapping("detail")
    public ResponseResult detail(HttpServletRequest req, String orderId) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result =  sysOrderService.detail(orderId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取订单详情失败，请稍后重试");
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

    @GetMapping("cancel_order")
    public ResponseResult cancelOrder(String orderId) {
        ResponseResult result = new ResponseResult();
        try {
            activeMQHandler.cancelOrder("cancelOrder", orderId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("发送关闭订单MQ失败", e);
            result.setCode(700);
            result.setError(e.getMessage());
            result.setError_description("发送关闭订单MQ失败");
        }
        return result;
    }

}
