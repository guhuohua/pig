package com.ch.dao;

import com.ch.entity.ShopOrder;
import com.ch.entity.ShopOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderMapper {
    int countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}