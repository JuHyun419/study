package com.juhyun.springbootjwttutorial.config;

import com.juhyun.springbootjwttutorial.jwt.JwtAccessDeniedHandler;
import com.juhyun.springbootjwttutorial.jwt.JwtAuthenticationEntryPoint;
import com.juhyun.springbootjwttutorial.jwt.JwtSecurityConfig;
import com.juhyun.springbootjwttutorial.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메소드단위로 추가하기 위해서 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* h2-console, 파비콘 관련 요청은 Spring Security 로직을 수행하지 않도록 설정 */
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico");
    }


    /**
     * 1) token 사용 => csrf disable
     * 2) exception 핸들링 => 커스텀 핸들링(401, 403)
     * 3) h2-console 을 위한 설정
     * 4) 세션을 사용하지 않기 때문에 세션 설정 => STATELESS
     * 5) 로그인 API, 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll() 설정
     * 6) JwtFilter 등록한 JwtSecurityConfig 클래스 적용
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 1)

                .exceptionHandling() // 2)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and() // 3)
                .headers()
                .frameOptions()
                .sameOrigin()

                .and() // 4)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and() // 5)
                .authorizeRequests()    // HttpServletRequest 를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .anyRequest().authenticated() // 위 요청을 제외한 나머지 요청들은 인증을 받아야함

                .and() // 6)
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
