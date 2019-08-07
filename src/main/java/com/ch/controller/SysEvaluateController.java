package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.BaseParam;
import com.ch.service.SysEvaluateService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_evaluate")
@Api("评价管理")
public class SysEvaluateController {

    private static final Logger LOGGER = LogManager.getLogger(SysEvaluateController.class);

    @Autowired
    SysEvaluateService sysEvaluateService;

    @GetMapping("list")
    @ApiOperation("评价列表")
    public ResponseResult list(HttpServletRequest req, BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysEvaluateService.list(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取评价列表失败");
        }
        return result;
    }

    @GetMapping("count")
    @ApiOperation("评价统计")
    public ResponseResult count(HttpServletRequest req, BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysEvaluateService.count(userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取评价统计失败");
        }
        return result;
    }

    @PostMapping("reply")
    @ApiOperation("评价回复")
    public ResponseResult reply(HttpServletRequest req, @RequestBody BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysEvaluateService.reply(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("评价回复失败");
        }
        return result;
    }

    @PostMapping("delete")
    @ApiOperation("评价删除")
    public ResponseResult delete(HttpServletRequest req, @RequestBody BaseParam param) {
        ResponseResult result = new ResponseResult();
        try {
            String token = req.getHeader("Authorization");
            Integer userId = TokenUtil.getUserId(token);
            result = sysEvaluateService.delete(param, userId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("评价删除失败");
        }
        return result;
    }
}
