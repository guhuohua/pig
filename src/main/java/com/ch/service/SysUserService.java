package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.UserDto;

public interface SysUserService {
    /**
     * 根据主键ID进行查找实体对象
     * @param userId
     * @return
     */
    UserDto findById(Integer userId);

    /**
     * 登录
     * @param userDto
     * @return
     */
    ResponseResult login(UserDto userDto);

}
