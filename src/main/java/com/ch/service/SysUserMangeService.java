package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.ShopUserParam;
import com.ch.model.SysUserParam;

public interface SysUserMangeService {


    /**
     * 后台店铺下人员列表
     * @param param
     * @return
     */
    ResponseResult findByShopId(ShopUserParam param);

    /**
     * 后台超级管理员新增店铺人员
     * @param sysUserParam
     * @return
     */
    ResponseResult insertUser(SysUserParam sysUserParam);

    /**
     * 删除该店铺下的人员，管理员不能删除
     * @param userId
     * @param shopId
     * @return
     */
    ResponseResult deleteUser(Integer userId, Integer shopId);
}
