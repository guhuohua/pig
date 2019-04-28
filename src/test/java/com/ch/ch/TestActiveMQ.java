 package com.ch.ch;

import com.ChApplication;
import com.ch.base.ResponseResult;
import com.ch.service.ViewGoodsListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;


 @SpringBootTest(classes = ChApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {
    @Autowired
    ViewGoodsListService viewGoodsListService;



    @Test
    public void testSend() {
        ResponseResult result = viewGoodsListService.shouCondition("苹", 1);
        Set data =(Set) result.getData();
        for (Object s :data){
          String str = (String)  s;
            System.out.println(str);
        }


    }

}
