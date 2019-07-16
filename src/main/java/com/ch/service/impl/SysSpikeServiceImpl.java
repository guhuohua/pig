package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsSkuMapper;
import com.ch.dao.SpikeGoodsMapper;
import com.ch.dto.SysSpikeListDTO;
import com.ch.entity.SpikeGoods;
import com.ch.model.SysSpikeGoodsModel;
import com.ch.service.SysSpikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSpikeServiceImpl implements SysSpikeService {

    @Autowired
    SpikeGoodsMapper spikeGoodsMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseResult mange(SysSpikeGoodsModel model) {
        ResponseResult result = new ResponseResult();
        SpikeGoods spikeGoods = new SpikeGoods();
        modelMapper.map(model, spikeGoods);
        if (BeanUtils.isNotEmpty(spikeGoods.getId())) {
            spikeGoodsMapper.updateByPrimaryKey(spikeGoods);
        } else {
            spikeGoodsMapper.insert(spikeGoods);
        }
        return result;
    }

    @Override
    public ResponseResult list(String sn, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(currentPage, pageSize);
        List<SysSpikeListDTO> list = spikeGoodsMapper.list(sn);
        PageInfo<SysSpikeListDTO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }
}
