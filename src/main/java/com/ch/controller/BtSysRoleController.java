package com.ch.controller;

import com.ch.base.ResponseResult;
import com.ch.model.PersonMangeParam;
import com.ch.model.PersonParam;
import com.ch.model.RoleDTO;
import com.ch.model.RolePermissionDTO;
import com.ch.service.BtSysRoleService;
import com.ch.service.BtSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "sys")
@Api(value = "人员角色管理",description = "人员角色管理")
public class BtSysRoleController {

    private static final Logger LOGGER = LogManager.getLogger(BtSysRoleController.class);

    @Autowired
    BtSysRoleService btSysRoleService;

    @Autowired
    BtSysUserService btSysUserService;

    @GetMapping(value = "role_list")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_mange_role_see","sys_mange_role"})
    @ApiOperation("获取角色")
    public ResponseResult roleList(HttpServletRequest req, HttpServletResponse res, @RequestParam int index, @RequestParam int size) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.roleList(index, size);
        } catch (Exception e) {
            LOGGER.error("获取角色列表失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取角色列表失败");
        }
        return result;
    }

    @PostMapping(value = "insert_role")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_mange_role_insert","sys_mange_role"})
    @ApiOperation("增加角色")
    public ResponseResult insertRole(HttpServletRequest req, HttpServletResponse res, @RequestBody RoleDTO roleDTO) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.insertRole(roleDTO);
        } catch (Exception e) {
            LOGGER.error("新增角色失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("新增角色失败");
        }
        return result;
    }

    @PostMapping(value = "delete_roles")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_mange_role_delete","sys_mange_role"})
    @ApiOperation("删除角色")
    public ResponseResult deleteRoles(HttpServletRequest req, HttpServletResponse res, @RequestBody List<String> roleIds) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.deleteRoles(roleIds);
        } catch (Exception e) {
            LOGGER.error("删除角色失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("删除角色失败");
        }
        return result;
    }

    @PostMapping(value = "update_roles")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_mange_role_edit","sys_mange_role"})
    @ApiOperation("编辑角色")
    public ResponseResult updateRoles(HttpServletRequest req, HttpServletResponse res, @RequestBody RoleDTO roleDTO) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.editRole(roleDTO);
        } catch (Exception e) {
            LOGGER.error("编辑角色失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("编辑角色失败");
        }
        return result;
    }

    @GetMapping(value = "find_permission")
   @ApiOperation("查询权限")
    public ResponseResult findPermission(HttpServletRequest req, HttpServletResponse res, @RequestParam String roleId) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.findPermissionByRoleId(roleId);
        } catch (Exception e) {
            LOGGER.error("查询权限失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("查询权限失败");
        }
        return result;
    }

    @PostMapping(value = "save_permission")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_mange_role_setup","sys_mange_role"})
    @ApiOperation("添加权限")
    public ResponseResult savePermission(HttpServletRequest req, HttpServletResponse res, @RequestBody RolePermissionDTO rolePermissionDTO) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.insertPermission(rolePermissionDTO);
        } catch (Exception e) {
            LOGGER.error("添加权限失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("添加权限失败");
        }
        return result;
    }

    @PostMapping(value = "person_mange_list")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_person_mange_see","sys_person_mange_mange"})
    @ApiOperation("展示后台管理人员列表")
    public ResponseResult personMangeList(HttpServletRequest req, HttpServletResponse res, @RequestBody PersonMangeParam param) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysUserService.personMange(param);
        } catch (Exception e) {
            LOGGER.error("后台人员管理列表查询失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("后台人员管理列表查询失败");
        }
        return result;
    }

    @GetMapping(value = "role")
    @ApiOperation("获取角色")
    public ResponseResult role(HttpServletRequest req, HttpServletResponse res) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysRoleService.roleList();
        } catch (Exception e) {
            LOGGER.error("获取角色失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("获取角色失败");
        }
        return result;
    }

    @PostMapping(value = "insert_or_update")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_person_mange_insert","sys_person_mange_edit","sys_person_mange_mange"})
    @ApiOperation("新增编辑人员")
    public ResponseResult insertOrUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody PersonParam personParam) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysUserService.updateOrInsertUser(personParam);
        } catch (Exception e) {
            LOGGER.error("新增或编辑人员失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("服务异常，请稍后重试");
        }
        return result;
    }

    @GetMapping(value = "reset")
    @RequiresPermissions(logical = Logical.OR, value = {"sys_person_mange_reset","sys_person_mange_mange"})
   @ApiOperation("重置密码")
    public ResponseResult reset(HttpServletRequest req, HttpServletResponse res, @RequestParam String userId) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysUserService.resetPassword(userId);
        } catch (Exception e) {
            LOGGER.error("重置密码失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("重置密码失败");
        }
        return result;
    }

    @GetMapping(value = "update_status")
    @ApiOperation("修改人员状态")
    public ResponseResult updateStatus(HttpServletRequest req, HttpServletResponse res, @RequestParam String userId, @RequestParam int status) {
        ResponseResult result = new ResponseResult();
        try {
            result = btSysUserService.updateUserStatus(userId, status);
        } catch (Exception e) {
            LOGGER.error("修改人员状态失败" + e.getMessage(), e);
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("修改人员状态失败");
        }
        return result;
    }

}
