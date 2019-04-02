package com.ch.base;

import com.ch.model.UserDTO;
import com.ch.service.BtSysUserService;
import com.ch.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    private BtSysUserService btSysUserService;

    @Autowired
    public void setBtSysUserService(BtSysUserService btSysUserService) {
        this.btSysUserService = btSysUserService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = TokenUtil.getUserId(principals.toString());
        UserDTO userDTO = btSysUserService.findById(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (userDTO.getRoles().size() > 0) {
            simpleAuthorizationInfo.setRoles(userDTO.getRoles());
        }
        if (userDTO.getPermissions().size() > 0) {
            simpleAuthorizationInfo.setStringPermissions(userDTO.getPermissions());
        }
        return simpleAuthorizationInfo;
    }
    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userId = TokenUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }

        UserDTO userDTO = btSysUserService.findById(userId);
        if (userDTO == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! TokenUtil.verify(token)) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
