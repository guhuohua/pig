package com.ch.dao;

import com.ch.entity.ShopMiniProgram;
import com.ch.entity.ShopMiniProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMiniProgramMapper {
    int countByExample(ShopMiniProgramExample example);

    int deleteByExample(ShopMiniProgramExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopMiniProgram record);

    int insertSelective(ShopMiniProgram record);

    List<ShopMiniProgram> selectByExample(ShopMiniProgramExample example);

    ShopMiniProgram selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopMiniProgram record, @Param("example") ShopMiniProgramExample example);

    int updateByExample(@Param("record") ShopMiniProgram record, @Param("example") ShopMiniProgramExample example);

    int updateByPrimaryKeySelective(ShopMiniProgram record);

    int updateByPrimaryKey(ShopMiniProgram record);
}