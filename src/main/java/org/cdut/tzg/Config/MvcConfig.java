package org.cdut.tzg.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.cdut.tzg.utils.ImageUtils.UPLOAD_PATH;
import static org.cdut.tzg.utils.ImageUtils.URL_PATH;

/**
 * @author anlan
 * @date 2019/6/27 8:58
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    //上传文件资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(URL_PATH+"**").addResourceLocations("file:"+UPLOAD_PATH);
        super.addResourceHandlers(registry);
    }
}
