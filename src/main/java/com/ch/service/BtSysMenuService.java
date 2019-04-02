package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.BtSysMenu;

public interface BtSysMenuService {

    ResponseResult findTree(String userId);
    ResponseResult findTree();
    ResponseResult add(BtSysMenu btSysMenu);
    ResponseResult update(BtSysMenu btSysMenu);
    ResponseResult dele(Integer id);
}
