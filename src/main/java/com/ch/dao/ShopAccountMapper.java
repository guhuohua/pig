package com.ch.dao;

import com.ch.entity.ShopAccount;
import com.ch.entity.ShopAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopAccountMapper {
    int countByExample(ShopAccountExample example);

    int deleteByExample(ShopAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAccount record);

    int insertSelective(ShopAccount record);

    List<ShopAccount> selectByExample(ShopAccountExample example);

    ShopAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAccount record, @Param("example") ShopAccountExample example);

    int updateByExample(@Param("record") ShopAccount record, @Param("example") ShopAccountExample example);

    int updateByPrimaryKeySelective(ShopAccount record);

    int updateByPrimaryKey(ShopAccount record);
}