package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.SysBaseSettingParam;

public interface SysMemberService {


    /**
     * 平台基本设置
     * @param param
     * @return
     */
    ResponseResult baseSetting(SysBaseSettingParam param);


    /**
     * 同步所有人积分等级
     * @return
     */
    ResponseResult synchronizedIntegral(Integer userId);

    /**
     * 查找平台基本设置
     * @return
     */
    ResponseResult findBaseSetting();
}
