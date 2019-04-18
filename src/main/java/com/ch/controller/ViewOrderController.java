/**
 * Author: 常富文
 * Date:   2019/4/16 17:38
 * Description: 订单
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.UserMapper;
import com.ch.dto.OrderDto;
import com.ch.entity.User;
import com.ch.service.ViewOrderService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("order")
public class ViewOrderController {




    private static final Logger LOGGER = LogManager.getLogger(ViewOrderController.class);
    @Autowired
    ViewOrderService viewOrderService;



    @PostMapping("addOrder")
    public ResponseResult addOrder( HttpServletRequest req, OrderDto[] orderDtoList ){
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
}
