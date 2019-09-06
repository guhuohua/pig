package com.ch.dao;

import com.ch.entity.UserRedPacket;
import com.ch.entity.UserRedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketMapper {
    long countByExample(UserRedPacketExample example);

    int deleteByExample(UserRedPacketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRedPacket record);

    int insertSelective(UserRedPacket record);

    List<UserRedPacket> selectByExample(UserRedPacketExample example);

    UserRedPacket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRedPacket record, @Param("example") UserRedPacketExample example);

    int updateByExample(@Param("record") UserRedPacket record, @Param("example") UserRedPacketExample example);

    int updateByPrimaryKeySelective(UserRedPacket record);

    int updateByPrimaryKey(UserRedPacket record);

}