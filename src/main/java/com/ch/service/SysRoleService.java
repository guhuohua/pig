package com.ch.service;

import com.ch.base.PageQuery;
import com.ch.base.ResponseResult;
import com.ch.entity.SysRole;
import com.ch.model.RolePermissionDTO;

import java.util.List;

public interface SysRoleService {

    /**
     * 查找该店铺下下拉角色信息
     * @param userId
     * @return
     */
    ResponseResult findRoleByShopId(Integer userId);

    /**
     * 角色列表
     * @param userId
     * @return
     */
    ResponseResult roleList(PageQuery query, Integer userId);


    /**
     * 角色管理
     * @param sysRole
     * @param userId
     * @return
     */
    ResponseResult roleMange(SysRole sysRole, Integer userId);

    /**
     * 删除角色
     * @param ids
     * @param userId
     * @return
     */
    ResponseResult deleteRole(List<Integer> ids, Integer userId);

    /**
     * 查询该角色下所有权限
     * @param roleId
     * @param userId
     * @return
     */
    ResponseResult findRolePermission(Integer roleId, Integer userId);

    /**
     * 更新权限信息
     * @param rolePermissionDTO
     * @return
     */
    ResponseResult updatePermission(RolePermissionDTO rolePermissionDTO, Integer userId);
}
