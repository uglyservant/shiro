package com.example.shiro.shiro;

import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uglys
 * Create on 2019/12/31 at 16:04
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principalCollection 规则集合
     * @return 授权信息对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 资源授权字符串
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        simpleAuthorizationInfo.addStringPermission(userService.findPermsByUserId(user.getId()));

        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken 认证Token
     * @return 认证信息对象
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("执行认证逻辑");

        // 编写Shiro判断逻辑，判断用户名和密码
        // 1.判断用户名

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByUsername(token.getUsername());
        if (user == null) {
            // 用户名不存在
            return null;
        }

        // 2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
