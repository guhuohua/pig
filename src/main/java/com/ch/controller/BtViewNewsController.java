package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.BtViewNews;
import com.ch.service.BtViewNewsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viewNews")
public class BtViewNewsController {

    @Autowired
    private BtViewNewsService btViewNewsService;
    private static final Logger LOGGER = LogManager.getLogger(BtViewNewsController.class);

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ResponseResult findViewNewsByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsByNewId(newId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }

    @RequestMapping(value = "/news_eng", method = RequestMethod.GET)
    public ResponseResult findViewNewsEngByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsEngByNewId(newId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }


    @RequestMapping(value = "/news_fan", method = RequestMethod.GET)
    public ResponseResult findViewNewsFanByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsFanByNewId(newId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }
    @RequestMapping(value = "/newsByMenuId", method = RequestMethod.GET)
    public ResponseResult findViewNewsByMenuId(@RequestParam String menuId,@RequestParam Integer index, @RequestParam Integer size) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsByMenId(menuId,index ,size);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }


    @RequestMapping(value = "/newsEngByMenuId", method = RequestMethod.GET)
    public ResponseResult findViewNewsFanByNewId(@RequestParam String menuId, @RequestParam Integer index, @RequestParam Integer size) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsEngByMenId(menuId,index,size);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }


    @RequestMapping(value = "/newsFanByMenuId", method = RequestMethod.GET)
    public ResponseResult findViewNewsFanByMenuId(@RequestParam String menuId,  @RequestParam Integer index, @RequestParam Integer size) {
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findViewNewsFanByMenId(menuId,index,size);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }

    @RequestMapping(value = "/findOneByMenId",method = RequestMethod.GET)
    public ResponseResult findOneByMenId(@RequestParam String MenuId){
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findOneByMenId(MenuId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }
    @RequestMapping(value = "/findOneEngByMenId",method = RequestMethod.GET)
    public ResponseResult findOneEngByMenId(@RequestParam String MenuId){
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findOneEngByMenId(MenuId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }
    @RequestMapping(value = "/findOneFanByMenId",method = RequestMethod.GET)
    public ResponseResult findOneFanByMenId(@RequestParam String MenuId){
        ResponseResult result = new ResponseResult();
        try {
            return  btViewNewsService.findOneFanByMenId(MenuId);
        } catch (Exception e) {
            LOGGER.error("新闻展示失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新闻展示失败");
            return result;
        }
    }

}
