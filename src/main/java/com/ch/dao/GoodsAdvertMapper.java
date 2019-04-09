package com.ch.dao;

import com.ch.entity.GoodsAdvert;
import com.ch.entity.GoodsAdvertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsAdvertMapper {
    int countByExample(GoodsAdvertExample example);

    int deleteByExample(GoodsAdvertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAdvert record);

    int insertSelective(GoodsAdvert record);

    List<GoodsAdvert> selectByExample(GoodsAdvertExample example);

    GoodsAdvert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAdvert record, @Param("example") GoodsAdvertExample example);

    int updateByExample(@Param("record") GoodsAdvert record, @Param("example") GoodsAdvertExample example);

    int updateByPrimaryKeySelective(GoodsAdvert record);

    int updateByPrimaryKey(GoodsAdvert record);
}