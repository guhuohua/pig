/**
 * Author: 常富文
 * Date:   2019/4/2 16:10
 * Description: shop控制层
 */


package com.ch.controller;

import com.ch.entity.Shop;
import com.ch.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {
   @Autowired
    ShopService shopService;


   @RequestMapping("/findById")
    public Shop findById(Integer Id){
        return shopService.findShopById(Id);
    }
}
