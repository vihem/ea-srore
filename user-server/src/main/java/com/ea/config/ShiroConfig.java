package com.ea.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ����Ҫ���õ���ShiroConfig�࣬Apache Shiro ����ͨ�� Filter ��ʵ�֣��ͺ���SpringMvc ͨ��DispatchServlet ��������һ����
 * ��Ȼ��ʹ�� Filter һ��Ҳ���ܲµ�����ͨ��URL���������й��˺�Ȩ��У�飬����������Ҫ����һϵ�й���URL�Ĺ���ͷ���Ȩ�ޡ�
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(){
        System.out.println(" *1* ShiroConfig.securityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        System.out.println(" *2* ShiroConfig.shiroRealm");
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * ƾ֤ƥ����
     * ���������ǵ�����У�齻��Shiro��SimpleAuthenticationInfo���д����ˣ�
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        System.out.println(" *3* ShiroConfig.hashedCredentialsMatcher");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//ɢ���㷨:����ʹ��MD5�㷨;
        hashedCredentialsMatcher.setHashIterations(2);//ɢ�еĴ���������ɢ�����Σ��൱�� md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     *  ����shiro aopע��֧��.
     *  ʹ�ô���ʽ;������Ҫ��������֧��;
     * @param securityManager securityManager
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        System.out.println(" *4* ShiroConfig.authorizationAttributeSourceAdvisor");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println(" *5* ShiroConfig.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // ������/������ -- ���ò��ᱻ���ص����� ˳���ж�
        // ���������壬��������˳��ִ�У�һ�㽫/**������Ϊ�±� -->:����һ�����أ�һ��С�Ĵ���Ͳ���ʹ��;
        //  1��һ��URL�������ö��Filter��ʹ�ö��ŷָ�
        //  2�������ö��������ʱ��ȫ����֤ͨ��������Ϊͨ��
        //  3�����ֹ�������ָ����������perms��roles
        // authc:����url��������֤ͨ���ſ��Է���; anon:����url����������������; user:���ü�ס�һ���֤ͨ�����Է��� ��
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");//�����˳� ������,���еľ�����˳�����Shiro�Ѿ�������ʵ����
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/login");// ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
        shiroFilterFactoryBean.setSuccessUrl("/index");// ��¼�ɹ���Ҫ��ת������
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");//δ��Ȩ����;

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver(){
        System.out.println(" *6* ShiroConfig.createSimpleMappingExceptionResolver");
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//���ݿ��쳣����
        mappings.setProperty("UnauthorizedException", "403"); //δ����Ȩ�쳣

        resolver.setExceptionMappings(mappings);  // None by default
        resolver.setDefaultErrorView("error");    // No default
        resolver.setExceptionAttribute("ex");     // Default is "exception"
        //resolver.setWarnLogCategory("example.MvcLogger");     // No default
        return resolver;
    }
}
