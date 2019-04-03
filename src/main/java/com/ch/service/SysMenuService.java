package com.ch.service;

import com.ch.base.ResponseResult;

public interface SysMenuService {

    /**
     * 后台菜单列表
     * @return
     */
    ResponseResult findSysMenu(Integer userId);
}
