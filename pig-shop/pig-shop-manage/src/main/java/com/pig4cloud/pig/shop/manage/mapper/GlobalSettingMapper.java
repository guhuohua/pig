package com.pig4cloud.pig.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.shop.api.entity.GlobalSetting;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺全局设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:48
 */
public interface GlobalSettingMapper extends BaseMapper<GlobalSetting> {
  /**
    * 店铺全局设置简单分页查询
    * @param globalSetting 店铺全局设置
    * @return
    */
  IPage<GlobalSetting> getGlobalSettingPage(Page page, @Param("globalSetting") GlobalSetting globalSetting);


}
