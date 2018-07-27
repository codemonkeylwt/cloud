package com.cloud.user.conf;

import com.cloud.user.security.MyShiroRealm;
import com.cloud.user.util.ExcuteProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Shiro配置类
 *
 * @author lwt
 * @date 2018/7/27 9:51
 */
@Configuration
public class ShiroConf {

    @Bean
    public SecurityManager getSecurityManager(Realm realm, CacheManager cacheManager) {
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(realm);
        defaultSecurityManager.setCacheManager(cacheManager);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/user/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        SecurityUtils.setSecurityManager(securityManager);
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("ShiroFilter.properties"));
            HashMap<String, String> map = new HashMap<String, String>((Map) properties);
            shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }

    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(Integer.valueOf(ExcuteProperties.getPro().getProperty("MD5.ITERATIONS")));
        return hashedCredentialsMatcher;
    }

    @Bean
    public CacheManager getCacheManager() {
        return new EhCacheManager();
    }
}
