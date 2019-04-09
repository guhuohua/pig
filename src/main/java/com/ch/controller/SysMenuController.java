package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.entity.SysMenu;
import com.ch.service.SysMenuService;
import com.ch.service.SysRoleService;
import com.ch.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/webMenu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/findTree")
    public ResponseResult findTreeMenu(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysMenuService.findSysMenu(userId);
    }

    @PostMapping("/add")
    public ResponseResult addMenu(HttpServletRequest req, @RequestBody SysMenu sysMenu) {
        return sysMenuService.addSysMenu(sysMenu);
    }

    @PostMapping("/edit")
    public ResponseResult editMenu(HttpServletRequest req, @RequestBody SysMenu sysMenu) {
        return sysMenuService.updateSysMenu(sysMenu);
    }

    @GetMapping("/delete")
    public ResponseResult deleteMenu(HttpServletRequest req, @RequestParam Integer menuId) {
        return sysMenuService.deleteSysMenu(menuId);
    }

    @GetMapping("/drop_menu")
    @ApiOperation("该店铺下的角色下拉菜单")
    public ResponseResult dropMenu(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysRoleService.findRoleByShopId(userId);
    }
}
