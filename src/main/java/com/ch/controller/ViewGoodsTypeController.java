/**
 * Author: 常富文
 * Date:   2019/4/15 18:33
 * Description: 商品类目
 */


package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dao.UserMapper;
import com.ch.entity.User;
import com.ch.service.ViewGoodsTypeService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("goods")
public class ViewGoodsTypeController {
    private static final Logger LOGGER = LogManager.getLogger(ViewGoodsAdvertController.class);

    @Autowired
    ViewGoodsTypeService viewGoodsTypeService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("findTree")
    public ResponseResult findTree(HttpServletRequest req){
        ResponseResult result = new ResponseResult();
        String token = req.getHeader("Authorization");
        //Integer userId = TokenUtil.getUserId(token);
       Integer userId = 6;
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            result = viewGoodsTypeService.findTree(user.getShopId());
        } catch (Exception e) {
            LOGGER.error("展示类目失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示类目失败");
        }
        return result;
     }


}