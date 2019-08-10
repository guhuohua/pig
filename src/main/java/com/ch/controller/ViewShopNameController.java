/**
 * Author: 常富文
 * Date:   2019/4/19 18:22
 * Description: 展示店铺名字
 */


package com.ch.controller;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.service.ViewShopNameService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("viewShop")
public class ViewShopNameController {


    private static final Logger LOGGER = LogManager.getLogger(ViewShopNameController.class);
    @Autowired
    ViewShopNameService viewShopNameService;

    @GetMapping("name")
    public ResponseResult showName(HttpServletRequest req) {
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
            result = viewShopNameService.showShopName(shopId);
        } catch (Exception e) {
            LOGGER.error("展示店铺名失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示店铺名失败");
        }
        return result;
    }
}
