package com.ch.dao;

import com.ch.entity.SysShop;
import com.ch.entity.SysShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysShopMapper {
    int countByExample(SysShopExample example);

    int deleteByExample(SysShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysShop record);

    int insertSelective(SysShop record);

    List<SysShop> selectByExample(SysShopExample example);

    SysShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysShop record, @Param("example") SysShopExample example);

    int updateByExample(@Param("record") SysShop record, @Param("example") SysShopExample example);

    int updateByPrimaryKeySelective(SysShop record);

    int updateByPrimaryKey(SysShop record);
}