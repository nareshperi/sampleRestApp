package com.webservice.crest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.webservice.crest.authentication.interceptors.LoginInterceptor;

/**
 * WebConfig
 *
 * @Author: Naresh Peri
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Custom interceptor, add intercept path and exclude intercept path
        registry.addInterceptor(new LoginInterceptor());//loginInterceptor).addPathPatterns("/**");
    }
}
