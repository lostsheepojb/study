package com.cjj.config;

import com.cjj.interceptor.ServiceProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cjj
 * @date 2022/9/30 14:34
 * @description
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] pat = {"/**"};
        String[] exc = {"/doc.html","classpath:/META-INF/resources/","/webjars/**","classpath:/META-INF/resources/webjars/","/swagger-resources/**"};
        registry.addInterceptor(new ServiceProtectInterceptor())
                .addPathPatterns(pat)
                .excludePathPatterns(exc);
    }
}