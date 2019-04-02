package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.MessageParam;
import com.ch.entity.BtViewMessage;
import com.ch.service.BtViewMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viewMessage")
public class BaViewMessageController {
    @Autowired
    private BtViewMessageService btViewMessageService;
    private static final Logger LOGGER = LogManager.getLogger(BaViewMessageController.class);

    @PostMapping("message")
   // @RequiresPermissions(logical = Logical.OR, value = {"sys_message_mange_see","sys_message_mange"})
    public ResponseResult insert(@RequestBody BtViewMessage record){
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            return btViewMessageService.insert(record);
        } catch (Exception e) {
            LOGGER.error("留言失败"+e.getMessage(),e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("留言失败");
            return result;
        }

    }

    @PostMapping("find_all")
    public ResponseResult insert(@RequestBody MessageParam param){
        return btViewMessageService.findAll(param);

    }
}
