package com.example.demo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.SessionFilter;

@Configuration
public class ConfigurationAce {

    @Bean
    FilterRegistrationBean<SessionFilter> hogeFilter() {
    	
        // FilterをnewしてFilterRegistrationBeanのコンストラクタに渡す
        FilterRegistrationBean<SessionFilter> bean = new FilterRegistrationBean<>(new SessionFilter());
        // Filterのurl-patternを指定（可変長引数なので複数指定可能）
        bean.addUrlPatterns("/admin/*");
        return bean;
    }
}