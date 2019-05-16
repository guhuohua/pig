package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.GoodsEvaluationMapper;
import com.ch.dao.SysUserMapper;
import com.ch.dto.BaseParam;
import com.ch.dto.SysEvaluateCountDTO;
import com.ch.dto.SysEvaluateDTO;
import com.ch.entity.GoodsEvaluation;
import com.ch.entity.GoodsEvaluationExample;
import com.ch.entity.SysUser;
import com.ch.service.SysEvaluateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysEvaluateServiceImpl implements SysEvaluateService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    GoodsEvaluationMapper goodsEvaluationMapper;

    @Override
    public ResponseResult list(BaseParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<SysEvaluateDTO> list = goodsEvaluationMapper.list(param.getName(), param.getScore(), param.getStatus(), sysUser.getShopId());
        PageInfo<SysEvaluateDTO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult count(Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        SysEvaluateCountDTO sysEvaluateCountDTO = new SysEvaluateCountDTO();
        sysEvaluateCountDTO.setDifferenceCount(goodsEvaluationMapper.differenceCount(sysUser.getShopId()));
        sysEvaluateCountDTO.setDifferenceMonth(goodsEvaluationMapper.differenceMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setDifferenceWeek(goodsEvaluationMapper.differenceWeek(sysUser.getShopId()));
        sysEvaluateCountDTO.setGoodsCount(goodsEvaluationMapper.goodsCount(sysUser.getShopId()));
        sysEvaluateCountDTO.setGoodsMonth(goodsEvaluationMapper.goodsMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setGoodsWeek(goodsEvaluationMapper.goodsWeek(sysUser.getShopId()));
        sysEvaluateCountDTO.setDifferenceThreeMonth(goodsEvaluationMapper.differenceThreeMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setGoodsThreeMonth(goodsEvaluationMapper.goodsThreeMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setSecondaryThreeMonth(goodsEvaluationMapper.secondaryThreeMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setSecondaryCount(goodsEvaluationMapper.secondaryCount(sysUser.getShopId()));
        sysEvaluateCountDTO.setSecondaryMonth(goodsEvaluationMapper.secondaryMonth(sysUser.getShopId()));
        sysEvaluateCountDTO.setSecondaryWeek(goodsEvaluationMapper.secondaryWeek(sysUser.getShopId()));
        result.setData(sysEvaluateCountDTO);
        return result;
    }

    @Override
    public ResponseResult reply(BaseParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsEvaluationExample goodsEvaluationExample = new GoodsEvaluationExample();
        goodsEvaluationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
        List<GoodsEvaluation> goodsEvaluations = goodsEvaluationMapper.selectByExample(goodsEvaluationExample);
        if (goodsEvaluations.stream().findFirst().isPresent()) {
            GoodsEvaluation goodsEvaluation = goodsEvaluations.stream().findFirst().get();
            goodsEvaluation.setStatus(1);
            goodsEvaluation.setUpdateTime(new Date());
            goodsEvaluation.setReply(param.getContent());
            goodsEvaluationMapper.updateByPrimaryKey(goodsEvaluation);
        }
        return result;
    }

    @Override
    public ResponseResult delete(BaseParam param, Integer userId) {
        ResponseResult result = new ResponseResult();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        GoodsEvaluationExample goodsEvaluationExample = new GoodsEvaluationExample();
        goodsEvaluationExample.createCriteria().andShopIdEqualTo(sysUser.getShopId()).andIdEqualTo(param.getId());
        goodsEvaluationMapper.deleteByExample(goodsEvaluationExample);
        return result;
    }
}
