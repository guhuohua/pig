package com.ch.dao;


import java.util.List;

import com.ch.entity.Sign;
import com.ch.entity.SignExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SignMapper {
    int countByExample(SignExample example);

    int deleteByExample(SignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    List<Sign> selectByExample(SignExample example);

    Sign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sign record, @Param("example") SignExample example);

    int updateByExample(@Param("record") Sign record, @Param("example") SignExample example);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}