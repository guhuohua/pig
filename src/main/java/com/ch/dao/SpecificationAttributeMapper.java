package com.ch.dao;

import com.ch.entity.SpecificationAttribute;
import com.ch.entity.SpecificationAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecificationAttributeMapper {
    int countByExample(SpecificationAttributeExample example);

    int deleteByExample(SpecificationAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecificationAttribute record);

    int insertSelective(SpecificationAttribute record);

    List<SpecificationAttribute> selectByExample(SpecificationAttributeExample example);

    SpecificationAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecificationAttribute record, @Param("example") SpecificationAttributeExample example);

    int updateByExample(@Param("record") SpecificationAttribute record, @Param("example") SpecificationAttributeExample example);

    int updateByPrimaryKeySelective(SpecificationAttribute record);

    int updateByPrimaryKey(SpecificationAttribute record);
}