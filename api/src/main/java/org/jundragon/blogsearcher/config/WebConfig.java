package org.jundragon.blogsearcher.config;

import org.jundragon.blogsearcher.converter.StringToBlogSearchSortTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToBlogSearchSortTypeConverter());
    }
}
