package com.nexters.keyme.global.security.filter;

import com.nexters.keyme.domain.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.global.enums.AuthRole;
import com.nexters.keyme.global.security.token.JwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.nexters.keyme.global.config.ConstantConfig.AUTHORIZATION_HEADER;

@Slf4j
public class AnonymousAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader.isEmpty() || authorizationHeader.startsWith("Anonymous")) {
            // 익명 토큰 저장
            GrantedAuthority anonymousRole = new SimpleGrantedAuthority(AuthRole.ANONYMOUS.name());
            Authentication anonymous = new JwtAuthenticationToken(new UserInfo(null), "", Arrays.asList(anonymousRole));
            SecurityContextHolder.getContext().setAuthentication(anonymous);
        }

        filterChain.doFilter(request, response);
    }
}
