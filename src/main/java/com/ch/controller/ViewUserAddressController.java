/**
 * Author: 常富文
 * Date:   2019/4/19 16:04
 * Description: 地址管理
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.UserAddress;
import com.ch.service.ViewUserAddressService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "地址管理", description = "地址管理")
@RequestMapping("address")
public class ViewUserAddressController {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserAddressController.class);
    @Autowired
    ViewUserAddressService viewUserAddressService;

    @PostMapping("edit")
    @ApiOperation("编辑地址")
    public ResponseResult addAddress(HttpServletRequest req, @RequestBody UserAddress[] records) {
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        ResponseResult result = new ResponseResult();
        try {
            for (UserAddress record : records) {
                if (record.getId() == null) {
                    result = viewUserAddressService.insert(record, openId, shopId);
                } else {
                    result = viewUserAddressService.updateByPrimaryKey(record, openId, shopId);
                }
            }
        } catch (Exception e) {
            LOGGER.error("编辑地址失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("编辑地址失败");
        }
        return result;
    }


    @GetMapping("dele")
    @ApiOperation("删除地址")
    public ResponseResult deleAddress(HttpServletRequest req, @RequestParam Integer id) {
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        ResponseResult result = new ResponseResult();
        try {
            result = viewUserAddressService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除地址失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除地址失败");
        }
        return result;
    }


    @GetMapping("find")
    @ApiOperation("查询地址")
    public ResponseResult findById(@RequestParam Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            result = viewUserAddressService.findById(id);
            System.out.println(result);
        } catch (Exception e) {
            LOGGER.error("查询地址失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询地址失败");
        }
        return result;
    }


    @GetMapping("findAll")
    @ApiOperation("查询全部地址")
    public ResponseResult findAll(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        try {
            result = viewUserAddressService.findAll(openId, shopId);
            System.out.println(result);
        } catch (Exception e) {
            LOGGER.error("查询全部地址失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询全部地址失败");
        }
        return result;
    }
}
