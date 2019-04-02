package com.ch.dao;

import com.ch.entity.SysDeptRelationExample;
import com.ch.entity.SysDeptRelationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptRelationMapper {
    int countByExample(SysDeptRelationExample example);

    int deleteByExample(SysDeptRelationExample example);

    int deleteByPrimaryKey(SysDeptRelationKey key);

    int insert(SysDeptRelationKey record);

    int insertSelective(SysDeptRelationKey record);

    List<SysDeptRelationKey> selectByExample(SysDeptRelationExample example);

    int updateByExampleSelective(@Param("record") SysDeptRelationKey record, @Param("example") SysDeptRelationExample example);

    int updateByExample(@Param("record") SysDeptRelationKey record, @Param("example") SysDeptRelationExample example);
}