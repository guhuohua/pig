package com.ch.dao;

import com.ch.entity.ShopSetting;
import com.ch.entity.ShopSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopSettingMapper {
    int countByExample(ShopSettingExample example);

    int deleteByExample(ShopSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopSetting record);

    int insertSelective(ShopSetting record);

    List<ShopSetting> selectByExample(ShopSettingExample example);

    ShopSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopSetting record, @Param("example") ShopSettingExample example);

    int updateByExample(@Param("record") ShopSetting record, @Param("example") ShopSettingExample example);

    int updateByPrimaryKeySelective(ShopSetting record);

    int updateByPrimaryKey(ShopSetting record);
}