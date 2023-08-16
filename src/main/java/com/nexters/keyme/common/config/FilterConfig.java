package com.nexters.keyme.common.config;

import com.nexters.keyme.common.filter.LoggingFilter;
import com.nexters.keyme.common.filter.LoggingTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerLoggingTracerFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LoggingTraceFilter());
        filter.setOrder(1);
        filter.setUrlPatterns(Arrays.asList("/*"));
        return filter;
    }

    @Bean
    public FilterRegistrationBean registerLoggingFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LoggingFilter());
        filter.setOrder(2);
        filter.setUrlPatterns(Arrays.asList("/*"));
        return filter;
    }
}
