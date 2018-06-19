package com.wybb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wybb.filter.CustomFilter;

//@Configuration  
public class FilterConfig {  
    @Bean  
    public FilterRegistrationBean<CustomFilter> filterRegist() {  
        FilterRegistrationBean<CustomFilter> frBean = new FilterRegistrationBean<CustomFilter>();  
        frBean.setFilter(new CustomFilter());  
        frBean.addUrlPatterns("/*");  
        return frBean;  
    }  
}  