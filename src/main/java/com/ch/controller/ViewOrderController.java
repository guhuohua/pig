/**
 * Author: 常富文
 * Date:   2019/4/16 17:38
 * Description: 订单
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.OrderDto;
import com.ch.service.ViewOrderService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("order")
@Api(value = "订单",description = "订单")
public class ViewOrderController {


    private static final Logger LOGGER = LogManager.getLogger(ViewOrderController.class);
    @Autowired
    ViewOrderService viewOrderService;

    @PostMapping("addOrder")
    @ApiOperation("添加订单")
    public ResponseResult addOrder( HttpServletRequest req, @RequestBody OrderDto[] orderDtoList ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.addOrder(orderDtoList,openId,shopId);
        } catch (Exception e) {
            LOGGER.error("展示轮播图失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示轮播图失败");
        }
        return result;
    }


    @GetMapping("showOrder")
    @ApiOperation("展示订单")
    public ResponseResult showOrder( HttpServletRequest req,@RequestParam String orderId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.showOrder(orderId,  openId);
        } catch (Exception e) {
            LOGGER.error("展示订单失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示订单失败");
        }
        return result;
    }


    @PostMapping("updateOrder")
    @ApiOperation("修改订单")
    public ResponseResult updateOrder( HttpServletRequest req,@RequestBody OrderDto orderDto ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.updateOrder(orderDto);
        } catch (Exception e) {
            LOGGER.error("修改失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示修改失败");
        }
        return result;
    }


    @GetMapping("manageOrder")
    @ApiOperation("订单管理查询")
    public ResponseResult manageOrder( HttpServletRequest req,@RequestParam Integer status ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.manageOrder(status,openId,shopId);
        } catch (Exception e) {
            LOGGER.error("订单管理查询失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("订单管理查询失败");
        }
        return result;
    }


    @GetMapping("findAll")
    @ApiOperation("查询所有订单")
    public ResponseResult findAll( HttpServletRequest req,@RequestParam Integer pageNum, @RequestParam Integer pageSize ){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.findAll(openId,shopId);
        } catch (Exception e) {
            LOGGER.error("查询所有订单失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询所有订单失败");
        }
        return result;
    }



    @GetMapping("deleOrderById")
    @ApiOperation("取消订单")
    public ResponseResult deleOrderById( HttpServletRequest req ,@RequestParam String orderId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderService.deleOrderById(orderId);
        } catch (Exception e) {
            LOGGER.error("取消订单失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("取消订单失败");
        }
        return result;
    }


}
