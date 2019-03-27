package com.pig4cloud.pig.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.shop.api.entity.Info;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺信息 店铺信息
 *
 * @author 球球
 * @date 2019-02-21 16:06:42
 */
public interface InfoMapper extends BaseMapper<Info> {
  /**
    * 店铺信息 店铺信息简单分页查询
    * @param info 店铺信息 店铺信息
    * @return
    */
  IPage<Info> getInfoPage(Page page, @Param("info") Info info);


}
