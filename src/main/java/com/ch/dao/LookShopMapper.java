package com.ch.dao;


import java.util.List;

import com.ch.entity.LookShop;
import com.ch.entity.LookShopExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LookShopMapper {
    int countByExample(LookShopExample example);

    int deleteByExample(LookShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LookShop record);

    int insertSelective(LookShop record);

    List<LookShop> selectByExample(LookShopExample example);

    LookShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LookShop record, @Param("example") LookShopExample example);

    int updateByExample(@Param("record") LookShop record, @Param("example") LookShopExample example);

    int updateByPrimaryKeySelective(LookShop record);

    int updateByPrimaryKey(LookShop record);
}