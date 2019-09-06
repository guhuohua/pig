package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.RedPacketDTO;
import com.ch.model.GrantListParam;
import com.ch.model.RedPacketParam;

public interface SysRedPacketService {

    /**
     * 保存红包实体
     * @param redPacketDTO
     * @return
     */
    ResponseResult save(RedPacketDTO redPacketDTO);

    /**
     * 红包列表
     * @param redPacketParam
     * @return
     */
    ResponseResult list(RedPacketParam redPacketParam);

    /**
     * 红包详情
     * @param id
     * @return
     */
    ResponseResult info(Integer id);


    /**
     * 已发放列表
     * @param param
     * @return
     */
    ResponseResult grantList(GrantListParam param);

    /**
     * 修改红包状态
     * @param id
     */
    boolean updateRedPacketStatus(int id);

}
