package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.model.TrafficStatisticsDTO;

public interface BtSysTrafficStatisticsService {

    /**
     * 保存流量统计信息
     * @param ip
     * @param address
     */
    void saveTrafficStatistics(String ip, String address);

    /**
     * 流量统计
     * @return
     */
    TrafficStatisticsDTO count();

    /**
     * 流量统计列表查询
     * @param index
     * @param size
     * @return
     */
    ResponseResult list(int index, int size);
}
