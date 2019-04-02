package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BtViewNewsCategoryMapper;
import com.ch.entity.BtViewNews;
import com.ch.entity.BtViewNewsCategory;
import com.ch.service.BtSysNewsCategoryService;
import com.ch.util.IdUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Service
@Transactional
public class BtSysNewsCategoryServiceImpl implements BtSysNewsCategoryService {
    @Autowired
    private BtViewNewsCategoryMapper btViewNewsCategoryMapper;
    ResponseResult result = new ResponseResult();
    @Override
    public ResponseResult insert(BtViewNewsCategory record) {

        btViewNewsCategoryMapper.insert(record);
        return result;
    }

    @Override
    public ResponseResult updateByPrimaryKey(BtViewNewsCategory record) {
        btViewNewsCategoryMapper.updateByPrimaryKey(record);
        return result;
    }

    @Override
    public ResponseResult delete(Integer id) {
        btViewNewsCategoryMapper.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public ResponseResult delete(List<Integer> ids) {
        for (Integer id : ids){
            btViewNewsCategoryMapper.deleteByPrimaryKey(id);
        }
        return result;
    }

    @Override
    public ResponseResult findPage(int pageNum, int pageSize) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(pageNum,pageSize);
        List<BtViewNewsCategory> btViewNewsCategoryList = btViewNewsCategoryMapper.selectByExample(null);
        PageInfo<BtViewNewsCategory> page = new PageInfo<>(btViewNewsCategoryList);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult findAll() {
        List<BtViewNewsCategory> btViewNewsCategoryList = btViewNewsCategoryMapper.selectByExample(null);
        result.setData(btViewNewsCategoryList);
        return result;
    }
}
