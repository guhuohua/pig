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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(model.getId());
            goodsSku.setSpikeGoods(model.getSpikeGoods());
            goodsSkuMapper.updateByPrimaryKey(goodsSku);
            if (0 == model.getSpikeGoods()) {
                SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
                spikeGoodsExample.createCriteria().andGoodsIdEqualTo(sysSpikeGoodsModel.getGoodsId()).andSkuIdEqualTo(model.getId());
                spikeGoodsMapper.deleteByExample(spikeGoodsExample);
            }
            if (1 == model.getSpikeGoods()) {
                Goods goods = goodsMapper.selectByPrimaryKey(sysSpikeGoodsModel.getGoodsId());
                if (GoodsTypeEnum.INTEGRAL.name().equals(goods.getGoodsType())) {
                    result.setCode(600);
                    result.setError("该商品为积分兑换商品，不允许设置秒杀");
                    result.setError_description("该商品为积分兑换商品，");
                    return result;
                }
                goods.setGoodsType(GoodsTypeEnum.SPIKE.name());
                //goodsMapper.updateByPrimaryKey(goods);
                SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
                spikeGoodsExample.createCriteria().andGoodsIdEqualTo(sysSpikeGoodsModel.getGoodsId()).andSkuIdEqualTo(model.getId());
                List<SpikeGoods> spikeGoods = spikeGoodsMapper.selectByExample(spikeGoodsExample);
                if (spikeGoods.size() > 0) {
                    SpikeGoods spikeGoods1 = spikeGoods.get(0);
                    BigDecimal subtract = model.getSpikePrice().multiply(new BigDecimal("100.00"));
                    spikeGoods1.setSpikePrice(subtract.longValue());
                    spikeGoods1.setSpikeNum(model.getSpikeNum());
                    //spikeGoods1.setMaxNum(model.g);
                    spikeGoods1.setBeginDate(new Date(model.getBeginDate()));
                    spikeGoods1.setEndDate(new Date(model.getEndDate()));
                    spikeGoodsMapper.updateByPrimaryKey(spikeGoods1);
                } else {
                    SpikeGoods spikeGoods2 = new SpikeGoods();
                    spikeGoods2.setGoodsId(sysSpikeGoodsModel.getGoodsId());
                    spikeGoods2.setBeginDate(new Date(model.getBeginDate()));
                    spikeGoods2.setEndDate(new Date(model.getEndDate()));
                    spikeGoods2.setSkuId(model.getId());
                    BigDecimal subtract = model.getSpikePrice().multiply(new BigDecimal("100.00"));
                    spikeGoods2.setSpikePrice(subtract.longValue());
                    spikeGoods2.setSpikeNum(model.getSpikeNum());
                    spikeGoods2.setMaxNum(sysSpikeGoodsModel.getMaxNum());
                    spikeGoodsMapper.insert(spikeGoods2);
                }
            }
        }
        return result;
    }

    @Override
    public ResponseResult list(String sn, Integer currentPage, Integer pageSize) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(currentPage, pageSize);
        List<SysSpikeListDTO> list = spikeGoodsMapper.list(sn);
        for (SysSpikeListDTO sysSpikeListDTO:list) {
            sysSpikeListDTO.setBeginDate(sysSpikeListDTO.getBeginDate() * 1000);
            sysSpikeListDTO.setEndDate(sysSpikeListDTO.getEndDate() * 1000);
        }
        PageInfo<SysSpikeListDTO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }

    @Override
    @Transactional
    public ResponseResult delete(Integer id) {
        ResponseResult result = new ResponseResult();
        SpikeGoods spikeGoods = spikeGoodsMapper.selectByPrimaryKey(id);
        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(spikeGoods.getSkuId());
        goodsSku.setSpikeGoods(0);
        goodsSkuMapper.updateByPrimaryKey(goodsSku);
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
