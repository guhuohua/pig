package com.ch.dao;

import com.ch.entity.UserTagGroup;
import com.ch.entity.UserTagGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagGroupMapper {
    int countByExample(UserTagGroupExample example);

    int deleteByExample(UserTagGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserTagGroup record);

    int insertSelective(UserTagGroup record);

    List<UserTagGroup> selectByExample(UserTagGroupExample example);

    UserTagGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserTagGroup record, @Param("example") UserTagGroupExample example);

    int updateByExample(@Param("record") UserTagGroup record, @Param("example") UserTagGroupExample example);

    int updateByPrimaryKeySelective(UserTagGroup record);

    int updateByPrimaryKey(UserTagGroup record);
}