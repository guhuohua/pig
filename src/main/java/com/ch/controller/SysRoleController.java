package com.ch.controller;

import com.ch.base.PageQuery;
import com.ch.base.ResponseResult;
import com.ch.entity.SysRole;
import com.ch.model.RolePermissionDTO;
import com.ch.service.SysRoleService;
import com.ch.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("list")
    public ResponseResult list(HttpServletRequest req, @RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        PageQuery query = new PageQuery();
        query.setCurrentPage(currentPage);
        query.setPageSize(pageSize);
        return sysRoleService.roleList(query, userId);
    }

    @PostMapping("manage")
    public ResponseResult manage(HttpServletRequest req, @RequestBody SysRole role) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysRoleService.roleMange(role, userId);
    }

    @PostMapping("delete")
    public ResponseResult delete(HttpServletRequest req, @RequestBody List<Integer> ids) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysRoleService.deleteRole(ids, userId);
    }

    @GetMapping("permission")
    public ResponseResult permission(HttpServletRequest req, @RequestParam Integer roleId) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysRoleService.findRolePermission(roleId, userId);
    }

    @PostMapping("update_permission")
    public ResponseResult updatePermission(HttpServletRequest req, @RequestBody RolePermissionDTO rolePermissionDTO) {
        String token = req.getHeader("Authorization");
        Integer userId = TokenUtil.getUserId(token);
        return sysRoleService.updatePermission(rolePermissionDTO, userId);
    }
}
