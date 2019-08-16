/**
 * Author: 常富文
 * Date:   2019/7/30 15:52
 * Description:
 */

package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsOrderMapper;
import com.ch.dao.OrderItemMapper;
import com.ch.dao.UserInfoMapper;
import com.ch.entity.GoodsOrder;
import com.ch.entity.UserInfo;
import com.ch.service.ForRecordService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.FlowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("integral")
@Api(value = "积分支付", description = "积分支付")
public class IntegralPayController {

    @Autowired
    ViewUserInfoService viewUserInfoService;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    FlowUtil flowUtil;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ForRecordService forRecordService;
    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("pay")
    @ApiOperation("积分支付")
    public ResponseResult pay(HttpServletRequest req, @RequestParam String orderId) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);

        try {
            goodsOrder.setOrderStatus(3);
            goodsOrder.setUserId(userInfo.getId());
            goodsOrderMapper.updateByPrimaryKey(goodsOrder);
            userInfo.setUseIntegral(userInfo.getUseIntegral() - goodsOrder.getIntegral());
            userInfoMapper.updateByPrimaryKey(userInfo);
            flowUtil.addFlowTel(goodsOrder.getIntegral(), "INTEGRAL", "INTEGRAL", 1, userInfo.getId());
            forRecordService.add(orderId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }
}
