package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BtSysTrafficStatisticsMapper;
import com.ch.entity.BtSysTrafficStatistics;
import com.ch.model.TrafficStatisticsDTO;
import com.ch.service.BtSysTrafficStatisticsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class BtSysTrafficStatisticsServiceImpl implements BtSysTrafficStatisticsService {

    @Resource
    BtSysTrafficStatisticsMapper btSysTrafficStatisticsMapper;

    @Override
    public void saveTrafficStatistics(String ip, String address) {
        BtSysTrafficStatistics hour = btSysTrafficStatisticsMapper.findHour(ip);
        if (hour == null) {
            BtSysTrafficStatistics btSysTrafficStatistics = new BtSysTrafficStatistics();
            btSysTrafficStatistics.setIp(ip);
            btSysTrafficStatistics.setRegion(address);
            btSysTrafficStatistics.setCreateDate(new Date());
            btSysTrafficStatisticsMapper.insert(btSysTrafficStatistics);
        }
    }

    @Override
    public TrafficStatisticsDTO count() {
        TrafficStatisticsDTO dto = new TrafficStatisticsDTO();
        dto.setCount(btSysTrafficStatisticsMapper.countAll());
        dto.setSame(btSysTrafficStatisticsMapper.countNow());
        dto.setYesterday(btSysTrafficStatisticsMapper.countYesterday());
        dto.setMonth(btSysTrafficStatisticsMapper.countMonth());
        return dto;
    }

    @Override
    public ResponseResult list(int index, int size) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(index, size);
        List<BtSysTrafficStatistics> btSysTrafficStatistics = btSysTrafficStatisticsMapper.findAll();
        PageInfo<BtSysTrafficStatistics> pageInfo = new PageInfo<>(btSysTrafficStatistics);
        result.setData(pageInfo);
        return result;
    }
}
