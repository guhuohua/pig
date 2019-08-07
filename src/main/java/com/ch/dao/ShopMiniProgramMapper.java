package com.ch.dao;

import com.ch.entity.ShopMiniProgram;
import com.ch.entity.ShopMiniProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
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


    @Select("select * from shop_mini_program where app_id = #{appid}")
    ShopMiniProgram findByAppid(@Param("appid") String appid);
}