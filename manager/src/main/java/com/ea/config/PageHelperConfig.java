package com.ea.config;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//@Configuration
public class PageHelperConfig {

//    @Autowired
//    private PageHelper pageHelper;

    @Autowired
    private Properties properties;
    //spring容器自动配置
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
