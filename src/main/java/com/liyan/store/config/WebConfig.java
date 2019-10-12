package com.liyan.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //前者是访问路径，后者是实际路径
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        registry.addResourceHandler("/icon/**").addResourceLocations("file:G:/ideaCode/work/icon/");
        registry.addResourceHandler("/pic/**").addResourceLocations("file:G:/ideaCode/work/pic/");
        registry.addResourceHandler("/default/**").addResourceLocations("file:G:/ideaCode/work/default/");
        registry.addResourceHandler("/other/**").addResourceLocations("file:G:/ideaCode/work/other/");
        super.addResourceHandlers(registry);
    }
}
