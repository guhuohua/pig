package com.ch.dao;


import java.util.List;

import com.ch.dto.SysSpikeListDTO;
import com.ch.entity.SpikeGoods;
import com.ch.entity.SpikeGoodsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SpikeGoodsMapper {
    int countByExample(SpikeGoodsExample example);

    int deleteByExample(SpikeGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpikeGoods record);

    int insertSelective(SpikeGoods record);

    List<SpikeGoods> selectByExample(SpikeGoodsExample example);

    SpikeGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);

    int updateByExample(@Param("record") SpikeGoods record, @Param("example") SpikeGoodsExample example);

    int updateByPrimaryKeySelective(SpikeGoods record);

    int updateByPrimaryKey(SpikeGoods record);

    @Select("select gs.id, gs.sn, gs.title, unix_timestamp(sk.begin_date) as beginDate, unix_timestamp(sk.end_date) as endDate," +
            "       gs.present_price, sk.spike_price, sk.spike_num, gs.inventory, sk.max_num from spike_goods sk left join goods gs on sk.goods_id = gs.id where gs.sn = #{sn}")
    List<SysSpikeListDTO> list(@Param("sn") String sn);
}