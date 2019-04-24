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
@Api(value = "商品类目",description = "商品类目")
public class ViewGoodsTypeController {
    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsAdvertController.class);

    @Autowired
    ViewGoodsTypeService viewGoodsTypeService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("findTree")
    @ApiOperation("展示商品类目")
    public ResponseResult findTree(HttpServletRequest req){
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
       //Integer userId = 6;
        try {
            //User user = userMapper.selectByPrimaryKey(shopId);
            result = viewGoodsTypeService.findTree(shopId);
        } catch (Exception e) {
            LOGGER.error("展示类目失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示类目失败");
        }
        return result;
     }


}
