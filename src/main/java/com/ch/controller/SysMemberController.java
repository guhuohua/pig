package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.SysBaseSettingParam;
import com.ch.service.SysMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("sys_member")
@Api("平台基本设置")
@Slf4j
public class SysMemberController {

    @Autowired
    SysMemberService sysMemberService;

    @GetMapping("findBaseSetting")
    @ApiOperation("获取平台基本设置")
    public ResponseResult list() {
        ResponseResult result = new ResponseResult();
        try {
            result = sysMemberService.findBaseSetting();
        } catch (Exception e) {
            log.error("获取基本设置失败", e);
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("获取基本设置失败");
        }
        return result;
    }

    @PostMapping("baseSetting")
    @ApiOperation("设置积分")
    public ResponseResult list(@RequestBody SysBaseSettingParam param) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysMemberService.baseSetting(param);
        } catch (Exception e) {
            log.error("配置基本设置失败", e);
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("配置基本设置失败");
        }
        return result;
    }

    @PostMapping("acquisitionMethod")
    @ApiOperation("设置积分获取方式")
    public ResponseResult acquisitionMethod(@RequestBody SysBaseSettingParam param) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysMemberService.acquisitionMethod(param);
        } catch (Exception e) {
            log.error("设置积分获取方式失败", e);
            result.setCode(600);
            result.setError(e.getMessage());
            result.setError_description("设置积分获取方式失败");
        }
        return result;
    }
}
