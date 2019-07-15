package com.ch.dao;


import java.util.List;

import com.ch.entity.SpikeGoods;
import com.ch.entity.SpikeGoodsExample;
import org.apache.ibatis.annotations.Param;

public interface SpikeGoodsMapper {
    int countByExample(SpikeGoodsExample example);

    int deleteByExample(SpikeGoodsExample example);

    int insert(SpikeGoods record);

    int insertSelective(SpikeGoods record);

    List<SpikeGoods> selectByExample(SpikeGoodsExample example);

    int updateByExampleSelective(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);

    int updateByExample(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);
}