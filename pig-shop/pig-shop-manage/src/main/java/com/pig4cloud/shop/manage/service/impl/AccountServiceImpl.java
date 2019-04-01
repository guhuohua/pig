package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.Account;
import com.pig4cloud.shop.manage.mapper.AccountMapper;
import com.pig4cloud.shop.manage.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * 商铺账户信息 商铺账户信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:11
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

  /**
   * 商铺账户信息 商铺账户信息简单分页查询
   * @param account 商铺账户信息 商铺账户信息
   * @return
   */
  @Override
  public IPage<Account> getAccountPage(Page<Account> page, Account account){
      return baseMapper.getAccountPage(page,account);
  }

}
