package com.pig4cloud.pig.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.shop.api.entity.Setting;
import org.apache.ibatis.annotations.Param;

/**
 * 多店铺设置
 *
 * @author 球球
 * @date 2019-02-21 16:13:30
 */
public interface SettingMapper extends BaseMapper<Setting> {
  /**
    * 多店铺设置简单分页查询
    * @param setting 多店铺设置
    * @return
    */
  IPage<Setting> getSettingPage(Page page, @Param("setting") Setting setting);


}
