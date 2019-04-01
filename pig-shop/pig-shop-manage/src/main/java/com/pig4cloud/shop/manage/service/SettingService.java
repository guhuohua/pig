package com.pig4cloud.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.shop.api.entity.Setting;

/**
 * 多店铺设置
 *
 * @author 球球
 * @date 2019-02-21 16:13:30
 */
public interface SettingService extends IService<Setting> {

  /**
   * 多店铺设置简单分页查询
   * @param setting 多店铺设置
   * @return
   */
  IPage<Setting> getSettingPage(Page<Setting> page, Setting setting);


}
