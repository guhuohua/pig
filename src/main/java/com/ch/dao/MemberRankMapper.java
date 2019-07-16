package com.ch.dao;


import java.util.List;

import com.ch.entity.MemberRank;
import com.ch.entity.MemberRankExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRankMapper {
    int countByExample(MemberRankExample example);

    int deleteByExample(MemberRankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberRank record);

    int insertSelective(MemberRank record);

    List<MemberRank> selectByExample(MemberRankExample example);

    MemberRank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberRank record, @Param("example") MemberRankExample example);

    int updateByExample(@Param("record") MemberRank record, @Param("example") MemberRankExample example);

    int updateByPrimaryKeySelective(MemberRank record);

    int updateByPrimaryKey(MemberRank record);
}