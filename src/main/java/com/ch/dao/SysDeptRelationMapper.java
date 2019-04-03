package com.ch.dao;

import com.ch.entity.SysDeptRelation;
import com.ch.entity.SysDeptRelationExample;
import com.ch.entity.SysDeptRelationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDeptRelationMapper {
    int countByExample(SysDeptRelationExample example);

    int deleteByExample(SysDeptRelationExample example);

    int deleteByPrimaryKey(SysDeptRelationKey key);

    int insert(SysDeptRelation record);

    int insertSelective(SysDeptRelation record);

    List<SysDeptRelation> selectByExample(SysDeptRelationExample example);

    SysDeptRelation selectByPrimaryKey(SysDeptRelationKey key);

    int updateByExampleSelective(@Param("record") SysDeptRelation record, @Param("example") SysDeptRelationExample example);

    int updateByExample(@Param("record") SysDeptRelation record, @Param("example") SysDeptRelationExample example);

    int updateByPrimaryKeySelective(SysDeptRelation record);

    int updateByPrimaryKey(SysDeptRelation record);
}