package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.BtSysMenu;
import com.ch.service.BtSysMenuService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sysMenu")
public class BtSysMenuController {
   @Autowired
   private BtSysMenuService btSysMenuService;
    private static final Logger LOGGER = LogManager.getLogger(BtViewNewsController.class);

   @RequestMapping("/findRoleTree")
    public ResponseResult findRoleTree(HttpServletRequest req){
       String token = req.getHeader("Authorization");
       String userId = TokenUtil.getUserId(token);
        return btSysMenuService.findTree(userId);
    }

    @RequestMapping("/findTree")
    public ResponseResult findTree(HttpServletRequest req){
        return btSysMenuService.findTree();
    }

   @RequestMapping(value = "/addSysMenu",method = RequestMethod.POST)
    public ResponseResult editSysMenu( @RequestBody BtSysMenu btSysMenu){
       ResponseResult result = new ResponseResult();
       try {
           if (btSysMenu.getId()!=null && !StringUtils.isEmpty(btSysMenu.getId())){
              result =  btSysMenuService.update(btSysMenu);
           }else {
              result =  btSysMenuService.add(btSysMenu);
           }
       } catch (Exception e) {
           LOGGER.error("增加修改菜单失败" + e.getMessage(), e);
           result.setCode(500);
           result.setError(e.getMessage());
           result.setError_description("增加修改菜单失败");
       }
       return result;
   }

    @RequestMapping(value = "/deleSysMenu")
   public ResponseResult deleSysMenu(Integer id){
       ResponseResult result = new ResponseResult();
        try {
            result = btSysMenuService.dele(id);
        } catch (Exception e) {
            LOGGER.error("删除菜单失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除菜单失败");
        }
        return result;
    }
}
