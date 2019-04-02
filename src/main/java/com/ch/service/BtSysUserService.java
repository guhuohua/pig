package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.PersonMangeParam;
import com.ch.model.PersonParam;
import com.ch.model.UserDTO;

public interface BtSysUserService {

    /**
     * 根据主键ID进行查找实体对象
     * @param userId
     * @return
     */
    UserDTO findById(String userId);

    /**
     * 登录
     * @param userDTO
     * @return
     */
    ResponseResult login(UserDTO userDTO);

    /**
     * 人员管理查询列表
     * @param param
     * @return
     */
    ResponseResult personMange(PersonMangeParam param);

    /**
     * 新增或修改人员
     * @param personParam
     * @return
     */
    ResponseResult updateOrInsertUser(PersonParam personParam);

    /**
     * 重置密码
     * @param userId
     * @return
     */
    ResponseResult resetPassword(String userId);

    /**
     * 修改人员状态
     * @param userId
     * @param status
     * @return
     */
    ResponseResult updateUserStatus(String userId, int status);

}
