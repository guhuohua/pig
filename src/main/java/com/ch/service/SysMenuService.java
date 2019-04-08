package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.SysMenu;

public interface SysMenuService {

    /**
     * 后台菜单列表
     * @return
     */
    ResponseResult findSysMenu(Integer userId);

    /**
     * 新增系统菜单
     * @param sysMenu
     * @return
     */
    ResponseResult addSysMenu(SysMenu sysMenu);


    /**
     * 修改系统菜单
     * @param sysMenu
     * @return
     */
    ResponseResult updateSysMenu(SysMenu sysMenu);

    /**
     * 删除系统菜单
     * @param menuId
     * @return
     */
    ResponseResult deleteSysMenu(Integer menuId);
}
