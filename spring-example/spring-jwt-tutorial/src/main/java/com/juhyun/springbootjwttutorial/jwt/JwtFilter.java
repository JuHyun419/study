package com.juhyun.springbootjwttutorial.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // JWT 토큰의 인증정보를 현재 실행중인 SecurityContext에 저장
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String jwtToken = resolveToken(httpServletRequest);
        final String requestUri = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)) {
            Authentication authentication = tokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",
                    authentication.getName(), requestUri);
        } else {
            log.debug("유효한 JWT 토큰이 존재하지 않습니다, uri: {}", requestUri);
        }
        chain.doFilter(request, response); // 다음 차례의 필터 클래스 객체의 doFilter() 메소드 호출
    }

    // Request Header에서 Token 정보를 꺼내오는 메소드
    private String resolveToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
