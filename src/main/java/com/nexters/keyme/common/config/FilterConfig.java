package com.nexters.keyme.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.common.filter.LoggingFilter;
import com.nexters.keyme.common.filter.LoggingTraceFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final ObjectMapper objectMapper;

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
        filter.setFilter(new LoggingFilter(objectMapper));
        filter.setOrder(2);
        filter.setUrlPatterns(Arrays.asList("/*"));
        return filter;
    }
}
