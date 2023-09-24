package com.nexters.keyme.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.global.common.dto.response.CommonResponse;
import com.nexters.keyme.global.common.exceptions.KeymeUnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException | KeymeUnauthorizedException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            log.warn(e.getMessage());
            objectMapper.writeValue(response.getWriter(), new CommonResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }
    }
}
