package com.ch.dao;
import com.ch.entity.GoodsDetails;
import com.ch.entity.GoodsDetailsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDetailsMapper {
    int countByExample(GoodsDetailsExample example);

    int deleteByExample(GoodsDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDetails record);

    int insertSelective(GoodsDetails record);

    List<GoodsDetails> selectByExample(GoodsDetailsExample example);

    GoodsDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsDetails record, @Param("example") GoodsDetailsExample example);

    int updateByExample(@Param("record") GoodsDetails record, @Param("example") GoodsDetailsExample example);

    int updateByPrimaryKeySelective(GoodsDetails record);

    int updateByPrimaryKey(GoodsDetails record);
}