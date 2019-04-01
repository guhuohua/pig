package com.pig4cloud.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.shop.api.entity.MiniProgram;

/**
 * 微信小程序信息 微信小程序信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:17
 */
public interface MiniProgramService extends IService<MiniProgram> {

  /**
   * 微信小程序信息 微信小程序信息简单分页查询
   * @param miniProgram 微信小程序信息 微信小程序信息
   * @return
   */
  IPage<MiniProgram> getMiniProgramPage(Page<MiniProgram> page, MiniProgram miniProgram);


}
