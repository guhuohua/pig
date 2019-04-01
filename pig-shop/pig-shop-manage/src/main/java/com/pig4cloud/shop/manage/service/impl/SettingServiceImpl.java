package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.Setting;
import com.pig4cloud.shop.manage.mapper.SettingMapper;
import com.pig4cloud.shop.manage.service.SettingService;
import org.springframework.stereotype.Service;

/**
 * 多店铺设置
 *
 * @author 球球
 * @date 2019-02-21 16:13:30
 */
@Service("settingService")
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

  /**
   * 多店铺设置简单分页查询
   * @param setting 多店铺设置
   * @return
   */
  @Override
  public IPage<Setting> getSettingPage(Page<Setting> page, Setting setting){
      return baseMapper.getSettingPage(page,setting);
  }

}
