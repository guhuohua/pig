package com.pig4cloud.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.shop.api.entity.Account;

/**
 * 商铺账户信息 商铺账户信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:11
 */
public interface AccountService extends IService<Account> {

  /**
   * 商铺账户信息 商铺账户信息简单分页查询
   * @param account 商铺账户信息 商铺账户信息
   * @return
   */
  IPage<Account> getAccountPage(Page<Account> page, Account account);


}
