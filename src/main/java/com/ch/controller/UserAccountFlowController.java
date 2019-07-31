package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.PageParam;
import com.ch.service.UserAccountFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user/account")
@Api(value = "积分明细",description = "积分明细")
public class UserAccountFlowController {
    private static final Logger LOGGER = LogManager.getLogger(UserAccountFlowController.class);
    @Autowired
    UserAccountFlowService userAccountFlowService;


    @PostMapping("list")
    @ApiOperation("列表展示")
    public ResponseResult list(HttpServletRequest req ,@RequestBody PageParam PageParam){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        try {
            PageParam.setOpenId(openId);
            result = userAccountFlowService.list(PageParam);
        } catch (Exception e) {
            LOGGER.error("列表展示失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("列表展示失败");
        }
        return result;
    }


    @GetMapping("addFlowAccount")
    @ApiOperation("添加积分明细")
    public ResponseResult addFlowAccount(HttpServletRequest req,@RequestParam String orderId){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        try {
            result = userAccountFlowService.addAccountFlow(orderId);
        } catch (Exception e) {
            LOGGER.error("添加积分明细失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("添加积分明细 失败");
        }
        return result;
    }




}
