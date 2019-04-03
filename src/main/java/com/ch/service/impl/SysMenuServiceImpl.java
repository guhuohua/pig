package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        List<String> permissionNameList = new ArrayList<>();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId).andShopIdEqualTo(sysUser.getShopId());
        sysUserRoleMapper.selectByExample(example).forEach(userRole -> {
            SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
            sysRolePermissionExample
                    .createCriteria()
                    .andRoleIdEqualTo(userRole.getRoleId())
                    .andShopIdEqualTo(userRole.getShopId());
            sysRolePermissionMapper.selectByExample(sysRolePermissionExample).forEach(rolePermission -> {
                SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
                permissionNameList.add(sysPermission.getDesc());
                if (BeanUtils.isNotEmpty(sysPermission.getParentId())) {
                    SysPermissionExample sysPermissionExample = new SysPermissionExample();
                    sysPermissionExample
                            .createCriteria()
                            .andPermissionIdEqualTo(sysPermission.getParentId());
                    sysPermissionMapper.selectByExample(sysPermissionExample).forEach(parentPermission -> {
                        permissionNameList.add(parentPermission.getDesc());
                        if (BeanUtils.isNotEmpty(parentPermission.getParentId())) {
                            SysPermissionExample sysPermissionExample2 = new SysPermissionExample();
                            sysPermissionExample2
                                    .createCriteria()
                                    .andParentIdEqualTo(parentPermission.getParentId());
                            sysPermissionMapper.selectByExample(sysPermissionExample2).forEach(topPermission -> {
                                permissionNameList.add(topPermission.getDesc());
                            });
                        }
                    });
                }
            });
        });
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(null);
        List<SysMenu> collect = sysMenus.stream().filter(item -> permissionNameList.contains(item.getPermission())).collect(Collectors.toList());
        List<SysMenu> rootMenu = new ArrayList<SysMenu>();
        for (SysMenu nav : collect) {
            if (nav.getParentId() == 0) {
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (SysMenu nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<SysMenu> childList = getChild(nav.getMenuId(), collect);
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
}
