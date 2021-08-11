## [SpringBoot + JWT(Json Web Token)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard)
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard

- JWT는 Header, Payload, Signature 3개의 부분으로 구성
- JWT는 중앙의 인증서버, 데이터 스토어에 대한 의존성 없음, 시스템 수평 확장이 유리함
- Base64 URL Safe Encoding -> URL, Cookie, Header 어디에서든 사용이 가능함

- Payload의 정보가 많아지면 네트워크 사용량 증, 데이터 설계 고려 필요
- 토큰이 클라이언트에 저장되기 때문에 서버에서 클라이언트의 토큰을 조작할 수 없음

### Header
- Signature를 해싱하기 위한 알고리즘 정보들이 담겨있음

### Payload
- 서버와 클라이언트가 주고받는, 시스템에서 실제로 사용될 정보에 대한 내용들을 담고있음

### Signature
- 토큰의 유효성 검증을 위한 문자열


### Security 설정, JWT 코드 

#### SecurityConfig & application.yml 

```java
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
```

```yaml
application.yml
        
spring:
  h2:
    console:
      enabled: true
      path: /h2-console

  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create-drop # create and then destroy the schema at the end of the session.
    properties:
      hibernate:
        format_sql: true
        show_sql: true
    defer-datasource-initialization: true

logging:
  level:
    me.silvernine: DEBUG

jwt:
  header: Authorization
  #HS512 알고리즘을 사용할 것이기 때문에 512bit, 즉 64byte 이상의 secret key를 사용해야 한다.
  #echo 'juhyun-springboot-jwt-tutorial-secret-zzang9haha-springboot-jwt-tutorial'|base64
  secret: anVoeXVuLXNwcmluZ2Jvb3Qtand0LXR1dG9yaWFsLXNlY3JldC16emFuZzloYWhhLXNwcmluZ2Jvb3Qtand0LXR1dG9yaWFsCg==
  token-validity-in-seconds: 86400
```

#### JwtAuthenticationEntryPoint
- 유효한 자격증명을 제공하지 않고 접근하려 할 때 401 Unauthorized 에러 리턴
#### JwtAccessDeniedHandler
- 필요한 권한이 존재하지 않는 경우 403 Forbidden 에러 리턴

```java
// 401 Unauthorized
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

// 403 Forbidden
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}

```

<br>

#### TokenProvider: 토큰 생성 및 토큰 유효성 검증

```java

import ...

// 토큰 생성 및 토큰 유효성 검증
@Component
@Log4j2
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    // 빈 생성 -> 의존성 주입 -> secret 값을 BASE64 decode 한 뒤 key에 할당
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /* Authentication 객체의 권한정보를 이용해서 토큰 생성 */
    public String createToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        final long now = (new Date()).getTime();
        final Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /* Token으로 Authentication 객체 생성 */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /* Token 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}

```

<br>

#### JwtFilter: Request Header에 Token 정보가 존재하면 SecurityContext에 Authentication(인증정보) 객체 저장

```java
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
        chain.doFilter(request, response);
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

```

<br>

#### JwtSecurityConfig: TokenProvider, JwtFilter를 SecurityConfig에 적용

```java
package com.juhyun.springbootjwttutorial.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TokenProvider, JwtFilter를 SecurityConfig에 적용
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // Security 로직에 JwtFilter 필터 등록
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

```

<br>

#### CustomUserDetailsService: UserDetailsService 인터페이스를 구현
- loadUserByUsername 메소드 구현

```java

public class CustomUserDetailsService implements UserDetailsService {
    ...

    /* 인증 요청(authenticate())할 때 해당 메소드 호출 */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomService loadUserByUsername() username: " + username);
        return userRepository.findOneWithAuthoritiesByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + "에 해당하는 데이터가 존재하지 않습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
        if (!user.isActivated()) {
            throw new RuntimeException(username + " 이 활성화가 되어있지 않습니다.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user)
        );
    }
}
```

<br>

### JWT Token 생성
- "api/authenticate API로 토큰 생성"
- AuthController

```java
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token); // => Custom loadUserByUsername 메소드 실행
        SecurityContextHolder.getContext().setAuthentication(authentication); // 인증객체를 SecurityContext에 저장

        String jwtToken = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwtToken); // Response Header에도 jwt token을 넣는다

        return new ResponseEntity<>(new TokenDto(jwtToken), httpHeaders, HttpStatus.OK);
    }
}
```

![image](https://user-images.githubusercontent.com/50076031/127728528-2f8a9c73-f96a-4b18-ad0e-fde186652810.png)
