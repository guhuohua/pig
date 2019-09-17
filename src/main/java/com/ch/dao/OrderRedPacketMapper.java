package com.ch.dao;

import com.ch.entity.OrderRedPacket;
import com.ch.entity.OrderRedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedPacketMapper {
    long countByExample(OrderRedPacketExample example);

    int deleteByExample(OrderRedPacketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRedPacket record);

    int insertSelective(OrderRedPacket record);

    List<OrderRedPacket> selectByExample(OrderRedPacketExample example);

    OrderRedPacket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderRedPacket record, @Param("example") OrderRedPacketExample example);

    int updateByExample(@Param("record") OrderRedPacket record, @Param("example") OrderRedPacketExample example);

    int updateByPrimaryKeySelective(OrderRedPacket record);

    int updateByPrimaryKey(OrderRedPacket record);
}