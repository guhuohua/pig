package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.service.BtSysTrafficStatisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "statistics")
public class BtSysTrafficStatisticsController {

    private static final Logger LOGGER = LogManager.getLogger(BtSysRoleController.class);

    @Autowired
    BtSysTrafficStatisticsService btSysTrafficStatisticsService;

    @GetMapping(value = "count")
    public ResponseResult count(HttpServletRequest req, HttpServletResponse res) {
        ResponseResult result = new ResponseResult();
        try {
            result.setData(btSysTrafficStatisticsService.count());
        } catch (Exception e) {
            LOGGER.error("获取流量统计信息失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取流量统计信息失败");
        }
        return result;
    }

    @GetMapping(value = "list")
    public ResponseResult list(HttpServletRequest req, HttpServletResponse res, @RequestParam int index, @RequestParam int size) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysTrafficStatisticsService.list(index, size);
        } catch (Exception e) {
            LOGGER.error("获取流量统计列表失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取流量统计列表失败");
        }
        return result;
    }
}
