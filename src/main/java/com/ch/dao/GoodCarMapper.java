package com.ch.dao;

import com.ch.entity.GoodCar;
import com.ch.entity.GoodCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodCarMapper {
    int countByExample(GoodCarExample example);

    int deleteByExample(GoodCarExample example);

    int insert(GoodCar record);

    int insertSelective(GoodCar record);

    List<GoodCar> selectByExample(GoodCarExample example);

    int updateByExampleSelective(@Param("record") GoodCar record, @Param("example") GoodCarExample example);

    int updateByExample(@Param("record") GoodCar record, @Param("example") GoodCarExample example);
}