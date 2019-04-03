/**
 * Author: 常富文
 * Date:   2019/4/3 9:07
 * Description: 系统用户
 */


package com.ch.model;

import java.util.Set;

public class UserDto {
    private Integer userId;

    private String username;

    private String password;

    private Long shopId;

    private Set<String> roles;

    private Set<String> premissions;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPremissions() {
        return premissions;
    }

    public void setPremissions(Set<String> premissions) {
        this.premissions = premissions;
    }
}
