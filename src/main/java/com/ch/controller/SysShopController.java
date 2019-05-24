/**
 * Author: 常富文
 * Date:   2019/4/2 16:10
 * Description: shop控制层
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.ShopParam;
import com.ch.entity.Shop;
import com.ch.service.SysShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys_shop")
@Api(value = "系统管理下店铺管理",description = "系统管理下店铺管理")
public class SysShopController {
    private static final Logger LOGGER = LogManager.getLogger(SysShopController.class);

    @Autowired
    SysShopService sysShopService;

    @GetMapping("findById")
    public Shop findById(Integer Id) {
        return sysShopService.findShopById(Id);
    }

    @GetMapping("dele")
    @ApiOperation("删除店铺")
    public ResponseResult dele(Integer id) {
        ResponseResult result = new ResponseResult();
        try {
            result = sysShopService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除店铺失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除店铺失败");
        }
        return result;
    }

    @PostMapping("edit")
    @ApiOperation("编辑店铺")
    public ResponseResult edit(@RequestBody Shop record) {
        ResponseResult result = new ResponseResult();
        try {
            if (record.getId() != null) {
                result = sysShopService.updateByPrimaryKey(record);
            } else {
                result = sysShopService.insert(record);
            }
        } catch (Exception e) {
            LOGGER.error("编辑店铺失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("编辑店铺失败");
        }
        return result;
    }

    @PostMapping("search")
    @ApiOperation("查询店铺")
    public ResponseResult search(@RequestBody ShopParam shopParam) {
        ResponseResult result = new ResponseResult();
        try {
            if (shopParam.getId() == null & shopParam.getTitle() == null) {
                result = sysShopService.findAll(shopParam);
            } else {
                result = sysShopService.serach(shopParam);
            }

        } catch (Exception e) {
            LOGGER.error("查询店铺失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询店铺失败");
        }
        return result;
    }

}
