package com.ch.dao;


import java.util.List;

import com.ch.entity.UserAccountFlow;
import com.ch.entity.UserAccountFlowExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountFlowMapper {
    int countByExample(UserAccountFlowExample example);

    int deleteByExample(UserAccountFlowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAccountFlow record);

    int insertSelective(UserAccountFlow record);

    List<UserAccountFlow> selectByExample(UserAccountFlowExample example);

    UserAccountFlow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAccountFlow record, @Param("example") UserAccountFlowExample example);

    int updateByExample(@Param("record") UserAccountFlow record, @Param("example") UserAccountFlowExample example);

    int updateByPrimaryKeySelective(UserAccountFlow record);

    int updateByPrimaryKey(UserAccountFlow record);
}