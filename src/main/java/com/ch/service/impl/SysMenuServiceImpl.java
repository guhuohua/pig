package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public ResponseResult findSysMenu(Integer userId) {
        ResponseResult result = new ResponseResult();
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(null);
        List<SysMenu> rootMenu = new ArrayList<SysMenu>();
        for (SysMenu nav : sysMenus) {
            if (nav.getParentId() == 0) {
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (SysMenu nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<SysMenu> childList = getChild(nav.getMenuId(), sysMenus);
            nav.setChildren(childList);//给根节点设置子节点
        }
        result.setData(rootMenu);
        return result;
    }

    public List<SysMenu> getChild(Integer id, List<SysMenu> allMenu) {
        //子菜单
        List<SysMenu> childList = new ArrayList<SysMenu>();
        for (SysMenu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (SysMenu nav : childList) {
            nav.setChildren(getChild(nav.getMenuId(), allMenu));
        }
        Collections.sort(childList, order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<SysMenu>();
        }
        return childList;
    }

    public Comparator<SysMenu> order() {
        Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                if (o1.getSort() != o2.getSort()) {
                    return o1.getSort() - o2.getSort();
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public ResponseResult addSysMenu(SysMenu sysMenu) {
        ResponseResult result = new ResponseResult();
        sysMenu.setCreateTime(new Date());
        sysMenuMapper.insert(sysMenu);
        return result;
    }

    @Override
    public ResponseResult updateSysMenu(SysMenu sysMenu) {
        ResponseResult result = new ResponseResult();
        sysMenu.setUpdateTime(new Date());
        sysMenuMapper.updateByPrimaryKey(sysMenu);
        return result;
    }

    @Override
    public ResponseResult deleteSysMenu(Integer menuId) {
        ResponseResult result = new ResponseResult();
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
        if (sysMenu.getParentId() == 0) {
            SysMenuExample sysMenuExample = new SysMenuExample();
            sysMenuExample.createCriteria().andParentIdEqualTo(menuId);
            List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
            for (SysMenu menu : sysMenus) {
                sysMenuMapper.deleteByPrimaryKey(menu.getMenuId());
            }
        }
        sysMenuMapper.deleteByPrimaryKey(menuId);
        return result;
    }
}
