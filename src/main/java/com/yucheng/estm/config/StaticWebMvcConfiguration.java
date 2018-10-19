package com.yucheng.estm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 静态资源配置
 *
 * @Author liukw 20191019
 */
@Configuration
public class StaticWebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/admin/**").addResourceLocations("classpath:/static/admin");
        registry.addResourceHandler("/static/webchat/**").addResourceLocations("classpath:/static/wechat/");
        super.addResourceHandlers(registry);
    }
}