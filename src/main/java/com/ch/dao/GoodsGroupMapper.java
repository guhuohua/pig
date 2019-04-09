package com.ch.dao;

import com.ch.entity.GoodsGroup;
import com.ch.entity.GoodsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsGroupMapper {
    int countByExample(GoodsGroupExample example);

    int deleteByExample(GoodsGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsGroup record);

    int insertSelective(GoodsGroup record);

    List<GoodsGroup> selectByExample(GoodsGroupExample example);

    GoodsGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsGroup record, @Param("example") GoodsGroupExample example);

    int updateByExample(@Param("record") GoodsGroup record, @Param("example") GoodsGroupExample example);

    int updateByPrimaryKeySelective(GoodsGroup record);

    int updateByPrimaryKey(GoodsGroup record);
}