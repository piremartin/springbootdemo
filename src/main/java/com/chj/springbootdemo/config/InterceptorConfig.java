package com.chj.springbootdemo.config;

import com.chj.springbootdemo.intercepter.LogCostInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chehaojie
 * @date 2019/04/23 16:02
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LogCostInterceptor logCostInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logCostInterceptor).addPathPatterns("/**");
    }

}
