package com.pig4cloud.pig.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.shop.api.entity.MiniProgram;
import org.apache.ibatis.annotations.Param;

/**
 * 微信小程序信息 微信小程序信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:17
 */
public interface MiniProgramMapper extends BaseMapper<MiniProgram> {
  /**
    * 微信小程序信息 微信小程序信息简单分页查询
    * @param miniProgram 微信小程序信息 微信小程序信息
    * @return
    */
  IPage<MiniProgram> getMiniProgramPage(Page page, @Param("miniProgram") MiniProgram miniProgram);


}
