package com.c503;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
* @ClassName: ResouceConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author liumm 
* @date 2017年11月16日 下午8:10:50 
*
 */
@Configuration
public class ResouceConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir");
        registry.addResourceHandler("/result/**").addResourceLocations("file:" + path + "/result/");
        super.addResourceHandlers(registry);
    }
}
