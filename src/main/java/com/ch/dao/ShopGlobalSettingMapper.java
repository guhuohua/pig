package com.ch.dao;

import com.ch.entity.ShopGlobalSetting;
import com.ch.entity.ShopGlobalSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGlobalSettingMapper {
    int countByExample(ShopGlobalSettingExample example);

    int deleteByExample(ShopGlobalSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopGlobalSetting record);

    int insertSelective(ShopGlobalSetting record);

    List<ShopGlobalSetting> selectByExample(ShopGlobalSettingExample example);

    ShopGlobalSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopGlobalSetting record, @Param("example") ShopGlobalSettingExample example);

    int updateByExample(@Param("record") ShopGlobalSetting record, @Param("example") ShopGlobalSettingExample example);

    int updateByPrimaryKeySelective(ShopGlobalSetting record);

    int updateByPrimaryKey(ShopGlobalSetting record);
}