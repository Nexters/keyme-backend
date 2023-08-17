package com.nexters.keyme.common.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.common.dto.internal.RequestLogInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);
            printRequestLog(cachingRequestWrapper, cachingResponseWrapper);
        } catch (JsonProcessingException e) {
            log.warn("로깅 중 JSON 처리 에러발생");
        } catch(UnsupportedEncodingException e) {
            log.warn("로깅 중 인코딩 에러발생");
        } finally {
            cachingResponseWrapper.copyBodyToResponse();
        }
    }

    private void printRequestLog(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        long executionTime = System.currentTimeMillis() - Long.parseLong(MDC.get("requestStartTime"));

        RequestLogInfo requestLogInfo = RequestLogInfo.builder()
                .uri(request.getRequestURI())
                .method(request.getMethod())
                .param(getParamString(request))
                .body(getBodyString(request))
                .executionTime(executionTime)
                .build();
        String logString = objectMapper.writeValueAsString(requestLogInfo);

        if (executionTime > 1000) {
            log.warn("Request too slow! - {}", logString);
        } else {
            log.info("Request - {}", logString);
        }
    }

    private String getParamString(HttpServletRequest request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request.getParameterMap());
    }

    private String getBodyString(HttpServletRequest request) throws UnsupportedEncodingException {
        String method = request.getMethod();
        if (!(method.equals(HttpMethod.POST.name()) ||
                method.equals(HttpMethod.PUT.name()) ||
                method.equals(HttpMethod.PATCH.name()))
        ) return "";

        if (request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper cachingRequestWrapper = (ContentCachingRequestWrapper) request;
            byte[] content = cachingRequestWrapper.getContentAsByteArray();
            return new String(content, cachingRequestWrapper.getCharacterEncoding());
        }

        return "";
    }
}
