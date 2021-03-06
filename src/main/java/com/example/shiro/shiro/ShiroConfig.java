package com.example.shiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author uglys
 * Create on 2019/12/31 at 15:58
 * TODO
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器
        /*
         * Shiro 内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon: 无需认证（登录）可以访问
         *          authc: 必须认证才可以访问
         *          user: 如果使用rememberMe的功能可以直接访问
         *          perms: 该资源必须得到资源权限才可以访问
         *          role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 授权过滤器 写在/user/**前面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 修改默认的跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        return  shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
