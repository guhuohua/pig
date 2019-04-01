package com.pig4cloud.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.shop.api.entity.Account;
import com.pig4cloud.shop.manage.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商铺账户信息 商铺账户信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

  private final AccountService accountService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param account 商铺账户信息 商铺账户信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Account>> getAccountPage(Page<Account> page, Account account) {
    return  new R<>(accountService.getAccountPage(page,account));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Account> getById(@PathVariable("id") Integer id){
    return new R<>(accountService.getById(id));
  }

  /**
   * 新增记录
   * @param account
   * @return R
   */
  @SysLog("新增商铺账户信息 商铺账户信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_account_add')")
  public R save(@RequestBody Account account){
    return new R<>(accountService.save(account));
  }

  /**
   * 修改记录
   * @param account
   * @return R
   */
  @SysLog("修改商铺账户信息 商铺账户信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_account_edit')")
  public R update(@RequestBody Account account){
    return new R<>(accountService.updateById(account));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商铺账户信息 商铺账户信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_account_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(accountService.removeById(id));
  }

}
