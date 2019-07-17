package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.SysSpikeGoodsModel;
import com.ch.service.SysSpikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sysSpike")
@Slf4j
public class SysSpikeController {

    @Autowired
    SysSpikeService sysSpikeService;

    @PostMapping("mange")
    public ResponseResult manege(@RequestBody SysSpikeGoodsModel model) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysSpikeService.mange(model);
        } catch (Exception e) {
            log.error("管理秒杀商品失败", e);
            result.setCode(600);
            result.setError("管理秒杀商品失败");
            result.setError_description("管理秒杀商品失败");
        }
        return result;
    }

    @GetMapping("list")
    public ResponseResult list(String sn, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysSpikeService.list(sn, currentPage, pageSize);
        } catch (Exception e) {
            log.error("秒杀列表获取失败", e);
            result.setCode(600);
            result.setError("秒杀列表获取失败");
            result.setError_description("秒杀列表获取失败");
        }
        return result;
    }

    @GetMapping("delete")
    public ResponseResult delete(@RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysSpikeService.delete(id);
        } catch (Exception e) {
            log.error("删除秒杀商品数失败", e);
            result.setCode(600);
            result.setError("删除秒杀商品数失败");
            result.setError_description("删除秒杀商品数失败");
        }
        return result;
    }
}
