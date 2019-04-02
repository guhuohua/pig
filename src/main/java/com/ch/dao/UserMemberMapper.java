package com.ch.dao;

import com.ch.entity.UserMember;
import com.ch.entity.UserMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMemberMapper {
    int countByExample(UserMemberExample example);

    int deleteByExample(UserMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserMember record);

    int insertSelective(UserMember record);

    List<UserMember> selectByExample(UserMemberExample example);

    UserMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserMember record, @Param("example") UserMemberExample example);

    int updateByExample(@Param("record") UserMember record, @Param("example") UserMemberExample example);

    int updateByPrimaryKeySelective(UserMember record);

    int updateByPrimaryKey(UserMember record);
}