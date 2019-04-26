/**
 * Author: 常富文
 * Date:   2019/4/26 11:19
 * Description: 售后
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.OrderRefund;
import com.ch.service.ViewOrderRefundService;
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
@RequestMapping("order")
@Api(value = "售后",description = "售后")
public class ViewOrderRefundController {
    private static final Logger LOGGER = LogManager.getLogger(ViewOrderRefundController.class);
    @Autowired
    ViewOrderRefundService viewOrderRefundService;

    @PostMapping("applyRefund")
    @ApiOperation("售后")
    public ResponseResult refund(HttpServletRequest req, @RequestBody OrderRefund orderRefund){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            //User user = userMapper.selectByPrimaryKey(userId);
            result = viewOrderRefundService.addOrderRefund(orderRefund,openId,shopId);
        } catch (Exception e) {
            LOGGER.error("申请售后失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("申请售后失败");
        }
        return result;
    }
}
