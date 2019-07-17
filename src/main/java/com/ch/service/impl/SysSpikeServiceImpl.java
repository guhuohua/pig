package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.GoodsMapper;
import com.ch.dao.GoodsSkuMapper;
import com.ch.dao.SpikeGoodsMapper;
import com.ch.dto.SysSpikeListDTO;
import com.ch.entity.Goods;
import com.ch.entity.GoodsSku;
import com.ch.entity.SpikeGoods;
import com.ch.entity.SpikeGoodsExample;
import com.ch.enums.GoodsTypeEnum;
import com.ch.model.SysSpikeGoodsModel;
import com.ch.model.SysSpikeSkuModel;
import com.ch.service.SysSpikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public ResponseResult mange(SysSpikeGoodsModel sysSpikeGoodsModel) {
        ResponseResult result = new ResponseResult();
        for (SysSpikeSkuModel model:sysSpikeGoodsModel.getSysSpikeSkuModels()) {
            SpikeGoods spikeGoods = new SpikeGoods();
            Goods goods = goodsMapper.selectByPrimaryKey(sysSpikeGoodsModel.getGoodsId());
            if (GoodsTypeEnum.INTEGRAL.name().equals(goods.getGoodsType())) {
                result.setCode(600);
                result.setError("该商品为积分兑换商品，不允许设置秒杀");
                result.setError_description("该商品为积分兑换商品，");
                return result;
            }
            goods.setGoodsType(GoodsTypeEnum.SPIKE.name());
            goodsMapper.updateByPrimaryKey(goods);
            spikeGoods.setGoodsId(sysSpikeGoodsModel.getGoodsId());
            spikeGoods.setBeginDate(new Date(sysSpikeGoodsModel.getBeginDate()));
            spikeGoods.setEndDate(new Date(sysSpikeGoodsModel.getEndDate()));
            spikeGoods.setSkuId(model.getId());
            spikeGoods.setSpikePrice(model.getSpikePrice());
            spikeGoods.setSpikeNum(model.getSpikeNum());
            spikeGoods.setMaxNum(sysSpikeGoodsModel.getMaxNum());
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(model.getId());
            goodsSku.setSpikeGoods(1);
            goodsSkuMapper.updateByPrimaryKey(goodsSku);

            SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
            spikeGoodsExample.createCriteria().andGoodsIdEqualTo(sysSpikeGoodsModel.getGoodsId()).andSkuIdEqualTo(model.getId());
            List<SpikeGoods> spikeGoods1 = spikeGoodsMapper.selectByExample(spikeGoodsExample);
            if (spikeGoods1.size() > 0) {
                spikeGoodsMapper.updateByPrimaryKey(spikeGoods);
            } else {
                spikeGoodsMapper.insert(spikeGoods);
            }
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

    @Override
    public ResponseResult delete(Integer id) {
        ResponseResult result = new ResponseResult();
        SpikeGoods spikeGoods = spikeGoodsMapper.selectByPrimaryKey(id);
        Integer goodsId = spikeGoods.getGoodsId();
        int i = spikeGoodsMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            goods.setGoodsType(GoodsTypeEnum.ORDINARY.name());
            goodsMapper.updateByPrimaryKey(goods);
        }
        return result;
    }
}
