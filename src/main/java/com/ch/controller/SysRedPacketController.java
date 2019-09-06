package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.RedPacketDTO;
import com.ch.model.GrantListParam;
import com.ch.model.RedPacketParam;
import com.ch.service.SysRedPacketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys_red_packet")
@Slf4j
@Api("后台红包接口")
public class SysRedPacketController {

    @Autowired
    SysRedPacketService sysRedPacketService;

    @PostMapping("save")
    @ApiOperation("后台保存红包")
    public ResponseResult save(HttpServletRequest req, @RequestBody RedPacketDTO redPacketDTO) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysRedPacketService.save(redPacketDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新增红包失败");
            return result;
        }
        return result;
    }

    @GetMapping("list")
    @ApiOperation("后台红包列表")
    public ResponseResult list(HttpServletRequest req, @RequestParam RedPacketParam redPacketParam) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysRedPacketService.list(redPacketParam);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取红包列表失败");
            return result;
        }
        return result;
    }

    @GetMapping("info")
    @ApiOperation("后台红包详情")
    public ResponseResult info(HttpServletRequest req, @RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysRedPacketService.info(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取红包列表失败");
            return result;
        }
        return result;
    }

    @GetMapping("grantList")
    @ApiOperation("后台已发放红包列表")
    public ResponseResult grantList(HttpServletRequest req, @RequestParam GrantListParam param) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysRedPacketService.grantList(param);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取发放红包列表失败");
            return result;
        }
        return result;
    }
}
