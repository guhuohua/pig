package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.entity.RedPacket;
import com.ch.model.UserUseRedPacketParam;

import java.util.List;

public interface ViewRedPacketService {

    /**
     * 小程序首页判断是否有可领红包
     * @param userId
     * @return
     */
    ResponseResult judgeRedPacket(Integer userId);


    /**
     * 当前用户的红包列表
     * @param userId
     * @return
     */
    ResponseResult redPacketList(Integer userId);


    /**
     * 领取红包
     * @param userId
     * @param redPacketId
     * @return
     */
    ResponseResult receiveRedPacket(Integer userId, Integer redPacketId);

    /**
     * 我的红包列表
     * @param userId
     * @return
     */
    ResponseResult myRedPacketList(Integer userId);

    /**
     * 送红包
     * @param userId
     * @return
     */
    void sendRedPacket(Integer userId, String redPacketType);

    /**
     * 用户下单选择红包
     * @param packetParam
     * @param userId
     * @return
     */
    ResponseResult userUseRedPacket(UserUseRedPacketParam packetParam, Integer userId);

}
