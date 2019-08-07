package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.ForRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/forRecord")
@Api(value = "兑换记录", description = "兑换记录")
public class ForRecordController {

    private static final Logger LOGGER = LogManager.getLogger(ForRecordController.class);
    @Autowired
    ForRecordService forRecordService;

    @ApiOperation("兑换记录列表")
    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        /*String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);*/
        //Integer userId = 6;
        try {
            result = forRecordService.list(openId);
        } catch (Exception e) {
            LOGGER.error("兑换记录列表" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("兑换记录列表");
        }
        return result;
    }
}
