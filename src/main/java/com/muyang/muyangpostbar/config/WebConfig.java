package com.muyang.muyangpostbar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-24 20:31:16
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 在D:/res/pic下如果有一张 luffy.jpg.jpg的图片，那么：
    // 1 访问：http://localhost:8080/img/luffy.jpg 可以访问到
    // 2 html 中 <img src="http://localhost:8080/img/luffy.jpg">
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:C://Users//lenovo//Pictures//Saved Pictures");
    }

}
