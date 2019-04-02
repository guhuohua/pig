package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.BtViewNewsCategory;
import com.ch.service.BtSysNewsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Api(value = "新闻分类管理",description = "新闻分类管理")
public class BtSysNewsCategoryController {
    @Autowired
    private BtSysNewsCategoryService btSysNewsCategoryService;

    private static final Logger LOGGER = LogManager.getLogger(BtViewNewsController.class);

    /*@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody BtViewNewsCategory record) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysNewsCategoryService.insert(record);
        } catch (Exception e) {
            LOGGER.error("添加分类失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("添加分类失败");
        }
        return result;
    }*/

    @GetMapping(value = "/findPage")
    @ApiOperation("展示新闻分类")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_category_see","sys_news_category"})
    public ResponseResult findPage(@RequestParam int index, @RequestParam int size){
        ResponseResult result = new ResponseResult();
        try {
            result = btSysNewsCategoryService.findPage(index, size);
        } catch (Exception e) {
            LOGGER.error("展示分类失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示分类失败");
        }
        return result;
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    @ApiOperation("新闻分类添加或修改")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_category_insert","sys_news_category_edit","sys_news_category"})
    public ResponseResult updateByPrimaryKey(@RequestBody BtViewNewsCategory record) {
        ResponseResult result = new ResponseResult();
        try {
            if (record.getId()!=null){
                result = btSysNewsCategoryService.updateByPrimaryKey(record);
            }else {
                result = btSysNewsCategoryService.insert(record);
            }

        } catch (Exception e) {
            LOGGER.error("修改分类失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("修改分类失败");
        }
        return result;
    }

    @RequestMapping(value = "/deleByIdsCategory", method = RequestMethod.POST)
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_category_delete","sys_news_category"})
    public ResponseResult deleByIds(@RequestBody List<Integer> ids) {
        ResponseResult result = new ResponseResult();
        try {
            result =  btSysNewsCategoryService.delete(ids);
        } catch (Exception e) {
            LOGGER.error("删除分类失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除分类失败");
        }
        return  result;
    }

    /*@RequestMapping(value = "/deleByIdCategory", method = RequestMethod.GET)
    public ResponseResult deleById(Integer id) {
        return btSysNewsCategoryService.delete(id);
    }*/
    @RequestMapping("/findAll")
    public ResponseResult findAll(){
        ResponseResult result = new ResponseResult();
        try {
            result =  btSysNewsCategoryService.findAll();
        } catch (Exception e) {
            LOGGER.error("展示所有分类失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("展示所有分类失败");
        }
        return  result;
    }

}
