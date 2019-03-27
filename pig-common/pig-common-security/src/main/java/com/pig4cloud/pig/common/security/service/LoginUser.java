package com.pig4cloud.pig.common.security.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @auther: 球球
 * @Date: 2019/2/22 16:26
 * @description:
 */
public class LoginUser extends PigUser {

	private Integer shopId;

	public LoginUser(Integer id, Integer deptId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(id, deptId, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public LoginUser(Integer shopId, Integer id, Integer deptId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(id, deptId, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.shopId = shopId;
	}
}
