package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BtViewMessageMapper;
import com.ch.model.MessageParam;
import com.ch.entity.BtViewMessage;
import com.ch.entity.BtViewMessageExample;
import com.ch.service.BtViewMessageService;
import com.ch.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class BtViewMessageServiceImpl implements BtViewMessageService {
   @Autowired
    private BtViewMessageMapper btViewMessageMapper;

    @Override
    public long countByExample(BtViewMessageExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(BtViewMessageExample example) {
        return 0;
    }

    @Override
    public ResponseResult insert(BtViewMessage record) {
        ResponseResult result =new ResponseResult();
        record.setMessageTime(new Date());
        record.setId(IdUtil.createIdByUUID());
        btViewMessageMapper.insert(record);
        return result;
    }

    @Override
    public int insertSelective(BtViewMessage record) {
        return 0;
    }

    @Override
    public List<BtViewMessage> selectByExampleWithBLOBs(BtViewMessageExample example) {
        return null;
    }

    @Override
    public List<BtViewMessage> selectByExample(BtViewMessageExample example) {
        return null;
    }

    @Override
    public int updateByExampleSelective(BtViewMessage record, BtViewMessageExample example) {
        return 0;
    }

    @Override
    public int updateByExampleWithBLOBs(BtViewMessage record, BtViewMessageExample example) {
        return 0;
    }

    @Override
    public int updateByExample(BtViewMessage record, BtViewMessageExample example) {
        return 0;
    }

    @Override
    public ResponseResult findAll(MessageParam param) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(param.getIndex(), param.getSize());
        List<BtViewMessage> all = btViewMessageMapper.findAll(param.getName(), param.getTel());
        PageInfo<BtViewMessage> btSysRolePageInfo = new PageInfo<>(all);
        result.setData(btSysRolePageInfo);
        return result;
    }
}
