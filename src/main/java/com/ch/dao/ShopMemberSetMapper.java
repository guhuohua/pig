package com.ch.dao;

import com.ch.entity.ShopMemberSet;
import com.ch.entity.ShopMemberSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMemberSetMapper {
    int countByExample(ShopMemberSetExample example);

    int deleteByExample(ShopMemberSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopMemberSet record);

    int insertSelective(ShopMemberSet record);

    List<ShopMemberSet> selectByExample(ShopMemberSetExample example);

    ShopMemberSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopMemberSet record, @Param("example") ShopMemberSetExample example);

    int updateByExample(@Param("record") ShopMemberSet record, @Param("example") ShopMemberSetExample example);

    int updateByPrimaryKeySelective(ShopMemberSet record);

    int updateByPrimaryKey(ShopMemberSet record);
}