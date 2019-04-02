package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.entity.*;
import com.ch.service.BtSysMenuService;
import com.ch.util.BaiduTranslateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BtSysMenuServiceImpl implements BtSysMenuService {
    @Autowired
    private BtSysMenuMapper btSysMenuMapper;

    @Autowired
    private BtSysUserRoleMapper btSysUserRoleMapper;

    @Autowired
    BtSysRolePermissionMapper btSysRolePermissionMapper;

    @Autowired
    BtSysPermissionMapper btSysPermissionMapper;

    @Override
    public ResponseResult findTree(String userId) {

        ResponseResult result = new ResponseResult();
        BtSysUserRoleExample btSysUserRoleExample = new BtSysUserRoleExample();
        btSysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<String> permissionNameList = new ArrayList<>();
        List<BtSysUserRole> btSysUserRoles = btSysUserRoleMapper.selectByExample(btSysUserRoleExample);
        btSysUserRoles.forEach(userRoles -> {
            BtSysRolePermissionExample btSysRolePermissionExample = new BtSysRolePermissionExample();
            btSysRolePermissionExample.createCriteria().andRoleIdEqualTo(userRoles.getRoleId());
            btSysRolePermissionMapper.selectByExample(btSysRolePermissionExample).forEach(rolePermission -> {
                BtSysPermissionExample btSysPermissionExample = new BtSysPermissionExample();
                btSysPermissionExample.createCriteria().andPermissionIdEqualTo(rolePermission.getPermissionId());
                btSysPermissionMapper.selectByExample(btSysPermissionExample).forEach(permission -> {
                    permissionNameList.add(permission.getName());
                    if (BeanUtils.isNotEmpty(permission.getParentId())) {
                        BtSysPermissionExample example = new BtSysPermissionExample();
                        example.createCriteria().andPermissionIdEqualTo(String.valueOf(permission.getParentId()));
                        btSysPermissionMapper.selectByExample(example).forEach(parentPermission -> {
                            permissionNameList.add(parentPermission.getName());
                            if (BeanUtils.isNotEmpty(parentPermission.getParentId())) {
                                BtSysPermissionExample example2 = new BtSysPermissionExample();
                                example2.createCriteria().andPermissionIdEqualTo(String.valueOf(permission.getParentId()));
                                btSysPermissionMapper.selectByExample(example2).forEach(top -> {
                                    permissionNameList.add(top.getName());
                                });
                            }
                        });
                    }
                });
            });
        });
        try {//查询所有菜单

            //btSysUserRoleMapper.selectByExample(example);

            List<BtSysMenu> allMenu = btSysMenuMapper.selectByExample(null);
            List<BtSysMenu> collect = allMenu.stream().filter(item -> permissionNameList.contains(item.getLabel())).collect(Collectors.toList());
            //根节点
            List<BtSysMenu> rootMenu = new ArrayList<BtSysMenu>();
            for (BtSysMenu nav : collect) {
                if (nav.getParentId() == 0) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (BtSysMenu nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<BtSysMenu> childList = getChild(nav.getId(), collect);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }


    }


    public List<BtSysMenu> getChild(Integer id, List<BtSysMenu> allMenu) {
        //子菜单
        List<BtSysMenu> childList = new ArrayList<BtSysMenu>();
        for (BtSysMenu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if ((nav.getParentId() != null) && nav.getParentId().equals(id)) {
                childList.add(nav);
            }
        }
        //递归
        for (BtSysMenu nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        Collections.sort(childList, order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<BtSysMenu>();
        }
        return childList;
    }

    public Comparator<BtSysMenu> order() {
        Comparator<BtSysMenu> comparator = new Comparator<BtSysMenu>() {
            @Override
            public int compare(BtSysMenu o1, BtSysMenu o2) {
                if (o1.getSortOrder() != o2.getSortOrder()) {
                    return o1.getSortOrder() - o2.getSortOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public ResponseResult add(BtSysMenu btSysMenu) {
        ResponseResult result = new ResponseResult();
        btSysMenuMapper.insert(btSysMenu);
        return result;
    }

    @Override
    public ResponseResult update(BtSysMenu btSysMenu) {
        ResponseResult result = new ResponseResult();
        btSysMenuMapper.updateByPrimaryKey(btSysMenu);
        return result;
    }

    @Override
    public ResponseResult dele(Integer id) {
        ResponseResult result = new ResponseResult();

        BtSysMenu btSysMenu = btSysMenuMapper.selectByPrimaryKey(id);
        if (btSysMenu.getParentId() == 0) {

            BtSysMenuExample example = new BtSysMenuExample();
            BtSysMenuExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<BtSysMenu> btViewMenus = btSysMenuMapper.selectByExample(example);
            if (btViewMenus != null) {
                for (BtSysMenu btSysMenu1 : btViewMenus) {
                    // System.out.println(btSysMenu1.getId());
                    BtSysMenuExample example1 = new BtSysMenuExample();
                    BtSysMenuExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andParentIdEqualTo(btSysMenu1.getId());
                    List<BtSysMenu> btSysMenus = btSysMenuMapper.selectByExample(example1);

                    for (BtSysMenu btSysMenu2 : btSysMenus) {
                        btSysMenuMapper.deleteByPrimaryKey(btSysMenu2.getId());
                    }
                    btSysMenuMapper.deleteByPrimaryKey(btSysMenu1.getId());

                   /* BtSysMenuExample example1 = new BtSysMenuExample();
                    BtSysMenuExample.Criteria criteria1 = example.createCriteria();
                    criteria.andParentIdEqualTo(btSysMenu1.getId()+"");
                    List<BtSysMenu> btSysMenus = btSysMenuMapper.selectByExample(example1);
                    for (BtSysMenu btSysMenu2 :btSysMenus ){
                        btSysMenuMapper.deleteByExample(example1);
                    }*/
                }
            } else {
                btSysMenuMapper.deleteByPrimaryKey(id);
            }
            btSysMenuMapper.deleteByPrimaryKey(id);
        } else {
            BtSysMenuExample example = new BtSysMenuExample();
            BtSysMenuExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            List<BtSysMenu> btSysMenus = btSysMenuMapper.selectByExample(example);
            if (btSysMenus != null) {
                for (BtSysMenu btSysMenus1 : btSysMenus) {
                    btSysMenuMapper.deleteByExample(example);
                }
                btSysMenuMapper.deleteByPrimaryKey(id);
            } else {
                btSysMenuMapper.deleteByPrimaryKey(id);
            }
        }

        return result;
    }

    @Override
    public ResponseResult findTree() {
        ResponseResult result = new ResponseResult();

        try {//查询所有菜单


            List<BtSysMenu> allMenu = btSysMenuMapper.selectByExample(null);
            //根节点
            List<BtSysMenu> rootMenu = new ArrayList<BtSysMenu>();
            for (BtSysMenu nav : allMenu) {
                if (nav.getParentId() == 0) {
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (BtSysMenu nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<BtSysMenu> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            result.setCode(0);

            result.setData(rootMenu);
            return result;

        } catch (Exception e) {
            result.setCode(500);
            result.setError(e.getMessage());
            result.setError_description("菜单生成异常");
            return result;
        }
    }

}

