package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.BtViewNewsEngMapper;
import com.ch.dao.BtViewNewsFanMapper;
import com.ch.dao.BtViewNewsMapper;
import com.ch.entity.*;
import com.ch.service.BtViewNewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BtViewNewsServiceImpl implements BtViewNewsService {

    @Autowired
    private BtViewNewsMapper btViewNewsMapper;

    @Autowired
    BtViewNewsEngMapper btViewNewsEngMapper;

    @Autowired
    BtViewNewsFanMapper btViewNewsFanMapper;


    @Override
    public long countByExample(BtViewNewsExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(BtViewNewsExample example) {
        return 0;
    }

    @Override
    public int insert(BtViewNews record) {
        return 0;
    }

    @Override
    public int insertSelective(BtViewNews record) {
        return 0;
    }

    @Override
    public List<BtViewNews> selectByExampleWithBLOBs(BtViewNewsExample example) {
        return null;
    }

    @Override
    public List<BtViewNews> selectByExample(BtViewNewsExample example) {

        return null;
    }

    @Override
    public int updateByExampleSelective(BtViewNews record, BtViewNewsExample example) {
        return 0;
    }

    @Override
    public int updateByExampleWithBLOBs(BtViewNews record, BtViewNewsExample example) {
        return 0;
    }

    @Override
    public int updateByExample(BtViewNews record, BtViewNewsExample example) {
        return 0;
    }

    @Override
    public ResponseResult findViewNewsByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsExample example = new BtViewNewsExample();
        BtViewNewsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(newId);
        criteria.andStatusEqualTo(1);
        List<BtViewNews> newsList = btViewNewsMapper.selectByExample(example);
        if (newsList.stream().findFirst().isPresent()) {
            BtViewNews btViewNews = newsList.stream().findFirst().get();
            Integer browseNumber = btViewNews.getBrowseNumber() == null ? 0 : btViewNews.getBrowseNumber();
            btViewNews.setBrowseNumber(++browseNumber);
            btViewNewsMapper.updateByPrimaryKey(btViewNews);
            result.setData(btViewNews);
        }
        return result;
    }

    @Override
    public ResponseResult findViewNewsEngByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsEngExample example = new BtViewNewsEngExample();
        BtViewNewsEngExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(newId);
        criteria.andStatusEqualTo(1);
        List<BtViewNewsEng> newsList = btViewNewsEngMapper.selectByExample(example);
        if (newsList.stream().findFirst().isPresent()) {
            BtViewNewsEng btViewNewsEng = newsList.stream().findFirst().get();
            Integer browseNumber = btViewNewsEng.getBrowseNumber() == null ? 0 : btViewNewsEng.getBrowseNumber();
            btViewNewsEng.setBrowseNumber(++browseNumber);
            btViewNewsEngMapper.updateByPrimaryKey(btViewNewsEng);
            result.setData(btViewNewsEng);
        }
        return result;
    }

    @Override
    public ResponseResult findViewNewsFanByNewId(String newId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsFanExample example = new BtViewNewsFanExample();
        BtViewNewsFanExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(newId);
        criteria.andStatusEqualTo(1);
        List<BtViewNewsFan> newsList = btViewNewsFanMapper.selectByExample(example);
        if (newsList.stream().findFirst().isPresent()) {
            BtViewNewsFan btViewNewsFan = newsList.stream().findFirst().get();
            Integer browseNumber = btViewNewsFan.getBrowseNumber() == null ? 0 : btViewNewsFan.getBrowseNumber();
            btViewNewsFan.setBrowseNumber(++browseNumber);
            btViewNewsFanMapper.updateByPrimaryKey(btViewNewsFan);
            result.setData(btViewNewsFan);
        }
        return result;
    }

    @Override
    public ResponseResult findViewNewsByMenId(String menuId, Integer index, Integer size) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(index, size);
        BtViewNewsExample example = new BtViewNewsExample();
        BtViewNewsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andMenuIdEqualTo(menuId);
        List<BtViewNews> btViewNews = btViewNewsMapper.selectByExample(example);
        PageInfo<BtViewNews> page = new PageInfo<>(btViewNews);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult findViewNewsEngByMenId(String menuId, Integer index, Integer size) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(index, size);
        BtViewNewsEngExample exampleEng = new BtViewNewsEngExample();
        BtViewNewsEngExample.Criteria criteria = exampleEng.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andMenuIdEqualTo(menuId);
        List<BtViewNewsEng> btViewNewsEng = btViewNewsEngMapper.selectByExample(exampleEng);
        PageInfo<BtViewNewsEng> page = new PageInfo<>(btViewNewsEng);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult findViewNewsFanByMenId(String menuId, Integer index, Integer size) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(index, size);
        BtViewNewsFanExample exampleFan = new BtViewNewsFanExample();
        BtViewNewsFanExample.Criteria criteria = exampleFan.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andMenuIdEqualTo(menuId);
        List<BtViewNewsFan> btViewNewsFan = btViewNewsFanMapper.selectByExample(exampleFan);
        PageInfo<BtViewNewsFan> page = new PageInfo<>(btViewNewsFan);
        result.setData(page);
        return result;
    }

    @Override
    public ResponseResult findOneByMenId(String menuId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsExample example = new BtViewNewsExample();
        BtViewNewsExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdEqualTo(menuId);
        criteria.andStatusEqualTo(1);
        List<BtViewNews> btViewNews = btViewNewsMapper.selectByExample(example);
        if (btViewNews.size() > 0) {
            BtViewNews viewNews = btViewNews.get(0);
            Integer browseNumber = viewNews.getBrowseNumber() == null ? 0 : viewNews.getBrowseNumber();
            viewNews.setBrowseNumber(++browseNumber);
            btViewNewsMapper.updateByPrimaryKey(viewNews);
            result.setData(viewNews);
        }

        return result;
    }

    @Override
    public ResponseResult findOneEngByMenId(String menuId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsEngExample exampleEng = new BtViewNewsEngExample();
        BtViewNewsEngExample.Criteria criteria = exampleEng.createCriteria();
        criteria.andMenuIdEqualTo(menuId);
        criteria.andStatusEqualTo(1);
        List<BtViewNewsEng> btViewNews = btViewNewsEngMapper.selectByExample(exampleEng);
        if (btViewNews.size() > 0) {
            BtViewNewsEng viewNews = btViewNews.get(0);
            Integer browseNumber = viewNews.getBrowseNumber() == null ? 0 : viewNews.getBrowseNumber();
            viewNews.setBrowseNumber(++browseNumber);
            btViewNewsEngMapper.updateByPrimaryKey(viewNews);
            result.setData(viewNews);
        }

        return result;
    }

    @Override
    public ResponseResult findOneFanByMenId(String menuId) {
        ResponseResult result = new ResponseResult();
        BtViewNewsFanExample exampleFan = new BtViewNewsFanExample();
        BtViewNewsFanExample.Criteria criteria = exampleFan.createCriteria();
        criteria.andMenuIdEqualTo(menuId);
        criteria.andStatusEqualTo(1);
        List<BtViewNewsFan> btViewNews = btViewNewsFanMapper.selectByExample(exampleFan);
        if (btViewNews.size() > 0) {
            BtViewNewsFan viewNews = btViewNews.get(0);
            Integer browseNumber = viewNews.getBrowseNumber() == null ? 0 : viewNews.getBrowseNumber();
            viewNews.setBrowseNumber(++browseNumber);
            btViewNewsFanMapper.updateByPrimaryKey(viewNews);
            result.setData(viewNews);
        }

        return result;
    }
}
