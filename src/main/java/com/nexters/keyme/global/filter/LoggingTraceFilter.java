package com.nexters.keyme.global.filter;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class LoggingTraceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("traceId", UUID.randomUUID().toString());
        MDC.put("requestUri", request.getRequestURI());
        MDC.put("requestStartTime", String.valueOf(System.currentTimeMillis()));

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
