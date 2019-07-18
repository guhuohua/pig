package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.UserAccountFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult list(HttpServletRequest req){
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        try {
            result = userAccountFlowService.list(openId);
        } catch (Exception e) {
            LOGGER.error("列表展示失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("列表展示失败");
        }
        return result;
    }
}
