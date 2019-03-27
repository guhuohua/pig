package com.pig4cloud.pig.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.shop.api.entity.GlobalSetting;

/**
 * 店铺全局设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:48
 */
public interface GlobalSettingService extends IService<GlobalSetting> {

  /**
   * 店铺全局设置简单分页查询
   * @param globalSetting 店铺全局设置
   * @return
   */
  IPage<GlobalSetting> getGlobalSettingPage(Page<GlobalSetting> page, GlobalSetting globalSetting);


}
