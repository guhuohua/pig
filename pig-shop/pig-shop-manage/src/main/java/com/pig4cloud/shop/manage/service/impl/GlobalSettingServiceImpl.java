package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.GlobalSetting;
import com.pig4cloud.shop.manage.mapper.GlobalSettingMapper;
import com.pig4cloud.shop.manage.service.GlobalSettingService;
import org.springframework.stereotype.Service;

/**
 * 店铺全局设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:48
 */
@Service("globalSettingService")
public class GlobalSettingServiceImpl extends ServiceImpl<GlobalSettingMapper, GlobalSetting> implements GlobalSettingService {

  /**
   * 店铺全局设置简单分页查询
   * @param globalSetting 店铺全局设置
   * @return
   */
  @Override
  public IPage<GlobalSetting> getGlobalSettingPage(Page<GlobalSetting> page, GlobalSetting globalSetting){
      return baseMapper.getGlobalSettingPage(page,globalSetting);
  }

}
