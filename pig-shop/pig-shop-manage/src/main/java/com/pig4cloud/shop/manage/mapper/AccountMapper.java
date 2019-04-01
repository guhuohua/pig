package com.pig4cloud.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.shop.api.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * 商铺账户信息 商铺账户信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:11
 */
public interface AccountMapper extends BaseMapper<Account> {
  /**
    * 商铺账户信息 商铺账户信息简单分页查询
    * @param account 商铺账户信息 商铺账户信息
    * @return
    */
  IPage<Account> getAccountPage(Page page, @Param("account") Account account);


}
