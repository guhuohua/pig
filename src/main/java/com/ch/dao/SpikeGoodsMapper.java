package com.ch.dao;


import java.util.List;

import com.ch.dao.provider.SpikeGoodsProvider;
import com.ch.dto.SysSpikeListDTO;
import com.ch.entity.SpikeGoods;
import com.ch.entity.SpikeGoodsExample;
import com.ch.model.ViewSpikeGoodsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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

    @SelectProvider(type = SpikeGoodsProvider.class, method = "getList")
    List<SysSpikeListDTO> list(@Param("sn") String sn);


    @Select("select sg.goods_id, unix_timestamp(sg.begin_date) as begin_date, unix_timestamp(sg.end_date) as end_date, spike_price,count(distinct sg.goods_id)" +
            "  from spike_goods sg where date_sub(sg.begin_date,interval 1 day)<=now() and sg.end_date > now()" +
            "      and sg.goods_id in (select id from goods where status = 1)  group by sg.goods_id  order by sg.begin_date desc, sg.spike_price desc")
    List<ViewSpikeGoodsDTO> viewList();

}