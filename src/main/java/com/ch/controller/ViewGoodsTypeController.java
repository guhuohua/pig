/**
 * Author: 常富文
 * Date:   2019/4/15 18:33
 * Description: 商品类目
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.UserMapper;
import com.ch.service.ViewGoodsTypeService;
import com.ch.util.TokenUtil;
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
@RequestMapping("goods")
@Api(value = "商品类目", description = "商品类目")
public class ViewGoodsTypeController {
    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsAdvertController.class);

    @Autowired
    ViewGoodsTypeService viewGoodsTypeService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("findTree")
    @ApiOperation("展示商品类目")
    public ResponseResult findTree(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
        Integer shopId = null;
        boolean verify = TokenUtil.verify(token);
        if (verify) {
            shopId = TokenUtil.getUserId(token);
        } else {
            result.setCode(999);
            result.setError("token失效请重新登录");
            result.setError_description("token失效请重新登录");
            return result;
        }
        try {
            result = viewGoodsTypeService.findTree(shopId);
        } catch (Exception e) {
            LOGGER.error("展示类目失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示类目失败");
        }
        return result;
    }


    @GetMapping("findDelFlag")
    @ApiOperation("展示二级类目")
    public ResponseResult findDelFlag(HttpServletRequest req) {
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
        Integer shopId = null;
        boolean verify = TokenUtil.verify(token);
        if (verify) {
            shopId = TokenUtil.getUserId(token);
        } else {
            result.setCode(999);
            result.setError("token失效请重新登录");
            result.setError_description("token失效请重新登录");
            return result;
        }
        try {
            result = viewGoodsTypeService.findDelFlag(shopId);
        } catch (Exception e) {
            LOGGER.error("展示二级类目失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示二级类目失败");
        }
        return result;
    }

}
