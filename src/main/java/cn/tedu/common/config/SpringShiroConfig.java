package cn.tedu.common.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class SpringShiroConfig {
    @Bean
    public SecurityManager securityManager(Realm realm, CacheManager cacheManager,RememberMeManager rememberManager) {
        DefaultWebSecurityManager sManager=
                new DefaultWebSecurityManager();
        sManager.setRealm(realm);
        sManager.setCacheManager(cacheManager);
        sManager.setRememberMeManager(rememberManager);
        return sManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        sfBean.setLoginUrl("/doLoginUI");
    //定义 map 指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String,String> map= new LinkedHashMap<>();
    //静态资源允许匿名访问:"anon"
        map.put("/bower_components/**","anon");
        map.put("/modules/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        map.put("/user/doLogin","anon");
        map.put("/doLogout","logout");
        /*map.put("/doIndexUI","anon");*/
        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**","user");//authc
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor (
            SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor=
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    @Bean
    protected CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }
    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cManager=
                new CookieRememberMeManager();
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        cookie.setMaxAge(7*24*60*60);
        cManager.setCookie(cookie);
        return cManager;
    }
}
