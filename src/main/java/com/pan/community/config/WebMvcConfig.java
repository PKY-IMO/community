package com.pan.community.config;

import com.pan.community.controller.interceptor.AlphaInterceptor;
import com.pan.community.controller.interceptor.LoginRequiredInterceptor;
import com.pan.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alphaInterceptor)
                .excludePathPatterns("/css/*","/js/*","/img/*")
                .addPathPatterns("/register","/login");

        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/css/*","/js/*","/img/*");

        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/css/*","/js/*","/img/*");
    }
}
