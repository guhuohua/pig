package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.PersonMangeParam;

public interface ShopService {

    /**
     * 店铺下的人员管理列表
     * @param param
     * @return
     */
    ResponseResult PersonMangeList(PersonMangeParam param, Integer userId);


    /**
     * 店铺下的人员管理增或编辑
     * @param param
     * @return
     */
    ResponseResult PersonMange(PersonMangeParam param, Integer userId);

    /**
     * 重置密码
     * @param userId
     * @param tokenUserId
     * @return
     */
    ResponseResult resetPassword(Integer userId, Integer tokenUserId);
}
