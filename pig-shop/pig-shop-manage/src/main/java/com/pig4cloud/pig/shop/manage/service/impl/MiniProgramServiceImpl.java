package com.pig4cloud.pig.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.shop.api.entity.MiniProgram;
import com.pig4cloud.pig.shop.manage.mapper.MiniProgramMapper;
import com.pig4cloud.pig.shop.manage.service.MiniProgramService;
import org.springframework.stereotype.Service;

/**
 * 微信小程序信息 微信小程序信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:17
 */
@Service("miniProgramService")
public class MiniProgramServiceImpl extends ServiceImpl<MiniProgramMapper, MiniProgram> implements MiniProgramService {

  /**
   * 微信小程序信息 微信小程序信息简单分页查询
   * @param miniProgram 微信小程序信息 微信小程序信息
   * @return
   */
  @Override
  public IPage<MiniProgram> getMiniProgramPage(Page<MiniProgram> page, MiniProgram miniProgram){
      return baseMapper.getMiniProgramPage(page,miniProgram);
  }

}
