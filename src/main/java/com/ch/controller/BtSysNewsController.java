package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.dto.NewsParam;
import com.ch.entity.BtViewNews;
import com.ch.service.BtSysNewsService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/sysNews")
@Api(value = "新闻管理", description = "新闻管理")
public class BtSysNewsController {

    @Autowired
    private BtSysNewsService btSysNewsService;
    private static final Logger LOGGER = LogManager.getLogger(BtSysNewsController.class);


    @RequestMapping(value = "/deleNews", method = RequestMethod.POST)
    @ApiOperation("删除新闻")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_see_delete", "sys_news_release"})
    public ResponseResult deleNews(@RequestBody List<String> ids) {
        ResponseResult result = new ResponseResult();

        try {
            return btSysNewsService.delete(ids);
        } catch (Exception e) {
            LOGGER.error("删除失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除失败");
            return result;
        }
    }

    /*@RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public ResponseResult addNews( @RequestBody BtViewNews record){
        ResponseResult result = new ResponseResult();

        try {
            return btSysNewsService.insert(record);
        } catch (Exception e) {
            LOGGER.error("添加失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("添加失败");
            return result;
        }
    }*/

    @RequestMapping(value = "/searchNews", method = RequestMethod.POST)
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_see", "sys_news_release"})
    public ResponseResult searchNews(@RequestBody NewsParam newsParam) {
        ResponseResult result = new ResponseResult();
        try {
            return btSysNewsService.findPage(newsParam);
        } catch (Exception e) {
            LOGGER.error("查询失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询失败");
            return result;
        }
    }

    @PostMapping(value = "/editNews")
    @ApiOperation("增加或修改新闻")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_see_insert", "sys_news_see_edit", "sys_news_release"})
    public ResponseResult updateNews(@RequestBody BtViewNews record, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        String userId = TokenUtil.getUserId(token);
        ResponseResult result = new ResponseResult();
        try {

            if (record.getId() != null && !StringUtils.isEmpty(record.getId())) {
                return btSysNewsService.updateByPrimaryKey(record);
            } else {
                return btSysNewsService.insert(record, userId);
            }

        } catch (Exception e) {
            LOGGER.error("修改失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("修改失败");
            return result;
        }
    }

    @GetMapping("/updateStatus")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_news_see_insert", "sys_news_see_edit", "sys_news_release"})
    public ResponseResult updateStatus(@RequestParam String id, @RequestParam int status) {
        return btSysNewsService.updateStatus(id, status);


    }

    @GetMapping("/findById/{id}")
    public ResponseResult findById(@PathVariable("id") String id) {
        System.out.println(id);
        return btSysNewsService.findById(id);
    }

    @GetMapping("/findById")
    public ResponseResult findById11111(@RequestParam("id") String id) {
        System.out.println(id);
        return btSysNewsService.findById(id);
    }

    /**
     * xxxxxxx?&tye=1111id=saaaa
     * xxxxxxx/111111/saaaa
     */
}