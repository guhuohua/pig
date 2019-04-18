package com.ch.dao;

import com.ch.entity.GoodsCar;
import com.ch.entity.GoodsCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsCarMapper {
    int countByExample(GoodsCarExample example);

    int deleteByExample(GoodsCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCar record);

    int insertSelective(GoodsCar record);

    List<GoodsCar> selectByExample(GoodsCarExample example);

    GoodsCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsCar record, @Param("example") GoodsCarExample example);

    int updateByExample(@Param("record") GoodsCar record, @Param("example") GoodsCarExample example);

    int updateByPrimaryKeySelective(GoodsCar record);

    int updateByPrimaryKey(GoodsCar record);
}