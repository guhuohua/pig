package com.ch.dao;

import com.ch.entity.Express;
import com.ch.entity.ExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressMapper {
    long countByExample(ExpressExample example);

    int deleteByExample(ExpressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Express record);

    int insertSelective(Express record);

    List<Express> selectByExample(ExpressExample example);

    Express selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Express record, @Param("example") ExpressExample example);

    int updateByExample(@Param("record") Express record, @Param("example") ExpressExample example);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
}