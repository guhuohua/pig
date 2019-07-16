package com.ch.dao;


import java.util.List;

import com.ch.entity.BaseIntegral;
import com.ch.entity.BaseIntegralExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseIntegralMapper {
    int countByExample(BaseIntegralExample example);

    int deleteByExample(BaseIntegralExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseIntegral record);

    int insertSelective(BaseIntegral record);

    List<BaseIntegral> selectByExample(BaseIntegralExample example);

    BaseIntegral selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseIntegral record, @Param("example") BaseIntegralExample example);

    int updateByExample(@Param("record") BaseIntegral record, @Param("example") BaseIntegralExample example);

    int updateByPrimaryKeySelective(BaseIntegral record);

    int updateByPrimaryKey(BaseIntegral record);
}