package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.PageQuery;
import com.ch.base.ResponseResult;
import com.ch.dao.SysPermissionMapper;
import com.ch.dao.SysRoleMapper;
import com.ch.dao.SysRolePermissionMapper;
import com.ch.dao.SysUserMapper;
import com.ch.entity.*;
import com.ch.model.DropDownMenu;
import com.ch.model.RolePermissionDTO;
import com.ch.model.RolePermissionModel;
import com.ch.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public ResponseResult findRoleByShopId(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andShopIdEqualTo(sysUser.getShopId());
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<DropDownMenu> list = new ArrayList<>();
        for (SysRole sysRole:sysRoles) {
            DropDownMenu dropDownMenu = new DropDownMenu();
            if (!"管理员".equals(sysRole.getRoleName())) {
                dropDownMenu.setRoleId(sysRole.getRoleId());
                dropDownMenu.setRoleName(sysRole.getRoleName());
            }
            list.add(dropDownMenu);
        }
        result.setData(list);
        return result;
    }

    @Override
    public ResponseResult roleList(PageQuery query, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andShopIdEqualTo(sysUser.getShopId());
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        PageInfo<SysRole> page = new PageInfo<>(sysRoles);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult roleMange(SysRole sysRole, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (BeanUtils.isNotEmpty(sysRole.getRoleId())) {
            sysRole.setUpdateTime(new Date());
            sysRoleMapper.updateByPrimaryKey(sysRole);
        } else {
            sysRole.setCreateTime(new Date());
            sysRole.setShopId(sysUser.getShopId());
            sysRoleMapper.insert(sysRole);
        }
        return result;
    }

    @Override
    public ResponseResult deleteRole(List<Integer> ids, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        for (int id:ids) {
            SysRoleExample sysRoleExample = new SysRoleExample();
            sysRoleExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andRoleIdEqualTo(id);
            sysRoleMapper.deleteByExample(sysRoleExample);
        }
        return result;
    }

    @Override
    public ResponseResult findRolePermission(Integer roleId, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        List<RolePermissionModel> allMenu = sysPermissionMapper.findAll();
        List<RolePermissionModel> rolePermissionModels = sysRolePermissionMapper.findAllByRoleId(roleId, sysUser.getShopId());
        for (RolePermissionModel root: allMenu) {
            root.setRoleId(roleId);
            root.setLabel(root.getPermissionName());
            for (RolePermissionModel children:rolePermissionModels) {
                if (children.getPermissionId().equals(root.getPermissionId())) {
                    root.setChecked(1);
                    root.setLabel(root.getPermissionName());
                }
            }
        }
        //根节点
        List<RolePermissionModel> rootMenu = new ArrayList<RolePermissionModel>();
        for (RolePermissionModel nav : allMenu) {
            if (nav.getParentId() == 0) {
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (RolePermissionModel nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<RolePermissionModel> childList = getChild(nav.getPermissionId(), allMenu);
            nav.setChildren(childList);//给根节点设置子节点
        }
        result.setData(rootMenu);
        return result;
    }

    public Comparator<RolePermissionModel> order() {
        Comparator<RolePermissionModel> comparator = new Comparator<RolePermissionModel>() {
            @Override
            public int compare(RolePermissionModel o1, RolePermissionModel o2) {
                if (o1.getSortOrder() != o2.getSortOrder()) {
                    return o1.getSortOrder() - o2.getSortOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    private List<RolePermissionModel> getChild(Integer id, List<RolePermissionModel> allMenu) {
        //子菜单
        List<RolePermissionModel> childList = new ArrayList<RolePermissionModel>();
        for (RolePermissionModel nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId() == Integer.valueOf(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (RolePermissionModel nav : childList) {
            nav.setChildren(getChild(nav.getPermissionId(), allMenu));
        }
        Collections.sort(childList, order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<RolePermissionModel>();
        }
        return childList;
    }


    @Override
    @Transactional
    public ResponseResult updatePermission(RolePermissionDTO rolePermissionDTO, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
        sysRolePermissionExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andRoleIdEqualTo(rolePermissionDTO.getRoleId());
        sysRolePermissionMapper.deleteByExample(sysRolePermissionExample);
        Integer roleId = rolePermissionDTO.getRoleId();
        rolePermissionDTO.getPermissions().forEach(item -> {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setPermissionId(item);
            sysRolePermission.setRoleId(roleId);
            sysRolePermission.setShopId(sysUser.getShopId());
            sysRolePermissionMapper.insert(sysRolePermission);
        });
        return result;
    }
}
