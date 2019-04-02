package com.ch.dao;

import com.ch.entity.OrderAttr;
import com.ch.entity.OrderAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderAttrMapper {
    int countByExample(OrderAttrExample example);

    int deleteByExample(OrderAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderAttr record);

    int insertSelective(OrderAttr record);

    List<OrderAttr> selectByExample(OrderAttrExample example);

    OrderAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderAttr record, @Param("example") OrderAttrExample example);

    int updateByExample(@Param("record") OrderAttr record, @Param("example") OrderAttrExample example);

    int updateByPrimaryKeySelective(OrderAttr record);

    int updateByPrimaryKey(OrderAttr record);
}