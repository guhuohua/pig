package com.ch.dao;

import com.ch.dao.provider.RedPacketProvider;
import com.ch.dto.GrantRedPacketDTO;
import com.ch.dto.RedPacketListDTO;
import com.ch.entity.RedPacket;
import com.ch.entity.RedPacketExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketMapper {
    long countByExample(RedPacketExample example);

    int deleteByExample(RedPacketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RedPacket record);

    int insertSelective(RedPacket record);

    List<RedPacket> selectByExample(RedPacketExample example);

    RedPacket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RedPacket record, @Param("example") RedPacketExample example);

    int updateByExample(@Param("record") RedPacket record, @Param("example") RedPacketExample example);

    int updateByPrimaryKeySelective(RedPacket record);

    int updateByPrimaryKey(RedPacket record);

    @SelectProvider(type = RedPacketProvider.class, method = "getList")
    List<RedPacketListDTO> list(@Param("name") String name, @Param("useBeginDate") Long useBeginDate,
                                @Param("useEndDate") Long useEndDate,
                                @Param("status") Integer status, @Param("redPacketType") String redPacketType);

    @SelectProvider(type = RedPacketProvider.class, method = "getGrantList")
    List<GrantRedPacketDTO> grantList(@Param("name") String name, @Param("status") Integer status);


    @Update("update red_packet set stock = stock-1 ,version = version+1 where id = #{id} and version = #{version}")
    int decreaseRedPacket(@Param("id") Integer id, @Param("version") Long version);
}