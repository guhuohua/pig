package com.ch.dao;

import com.ch.entity.GoodsShare;
import com.ch.entity.GoodsShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsShareMapper {
    int countByExample(GoodsShareExample example);

    int deleteByExample(GoodsShareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsShare record);

    int insertSelective(GoodsShare record);

    List<GoodsShare> selectByExample(GoodsShareExample example);

    GoodsShare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsShare record, @Param("example") GoodsShareExample example);

    int updateByExample(@Param("record") GoodsShare record, @Param("example") GoodsShareExample example);

    int updateByPrimaryKeySelective(GoodsShare record);

    int updateByPrimaryKey(GoodsShare record);
}