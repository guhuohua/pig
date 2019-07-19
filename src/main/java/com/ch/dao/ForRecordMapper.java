package com.ch.dao;


import java.util.List;

import com.ch.entity.ForRecord;
import com.ch.entity.ForRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ForRecordMapper {
    int countByExample(ForRecordExample example);

    int deleteByExample(ForRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ForRecord record);

    int insertSelective(ForRecord record);

    List<ForRecord> selectByExample(ForRecordExample example);

    ForRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForRecord record, @Param("example") ForRecordExample example);

    int updateByExample(@Param("record") ForRecord record, @Param("example") ForRecordExample example);

    int updateByPrimaryKeySelective(ForRecord record);

    int updateByPrimaryKey(ForRecord record);
}