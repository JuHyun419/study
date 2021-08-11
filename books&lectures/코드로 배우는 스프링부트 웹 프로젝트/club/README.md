# Spring Security를 이용한 로그인 처리

## SpringBoot와 Spring Security 연동
- 스프링 시큐리티를 이용하는 프로젝트 생성
- 스프링 시큐리티 커스터마이징
- 프로젝트를 위한 JPA 처리
- 시큐리티를 위한 UserDetailsService
- Thymeleaf/Controller에서 사용자 정보 출력하기

### 스프링 시큐리티 소셜 로그인 처리
- 구글 로그인 시나리오
- 프로젝트 연동
- 자동 회원 가입의 후처리
- Remember me와 @PreAuthorize


### 학습 내용
- 스프링 시큐리티에서 제공하는 로그인 처리 방식의 이해
- JPA와 연동하는 커스텀 로그인 처리
- Thymeleaf에서 로그인 정보 활용

#### build.gradle DB, 시큐리티(thymeleaf) 의존성 추가
```java가

// https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.7.0'
        
implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-springsecurity5'

implementation group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-java8time'
```


#### 프로젝트 실행
- default로 사용할 수 있는 'user' 계정의 패스워드

![image](https://user-images.githubusercontent.com/50076031/124544516-33abf200-de62-11eb-915d-969256f490d2.png)

- localhost 접근 -> /login: 시큐리티에서 기본으로 제공하는 로그인 페이지

![image](https://user-images.githubusercontent.com/50076031/124544586-4d4d3980-de62-11eb-80d6-9175722e3455.png)
  
#### 시큐리티 설정 클래스
- WebSecurityConfigurerAdapter 클래스 상속

```java
@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/sample/all").permitAll()
                    .antMatchers("/sample/member").hasRole("USER");
                
                ...
    }
}
```

#### 스프링 시큐리티 용어와 흐름
- Principal(접근 주체)
    - 보호된 리소스에 접근하는 사용자
    - [Authentication](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authentication) 으로 추상화 되어있음
    
- Authentication(인증)
    - 현재 사용자가 누구인지 식별
    - 주요 컴포넌트: AuthenticationManager, AuthenticationRrovider
    
- Authorization(인가)
    - 현재 사용자가 보호된 리소스에 권한이 있는지를 검사
    - 주요 컴포넌트: AccessDecisionManager, AccessDecisionVoter
    
- GrantedAuthority(권한)
    - 인증된 사용자의 인증정보(ROLE) 등을 표현
    
- SecurityContext
    - 접근 주체(Authentication)와 인증정보(GrantedAuthority)을 담고있는 Context
    - ThreadLocal에 보관되고 [SecurityContextHolder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/context/SecurityContextHolder.html) 을 통해 접근이 가능

![image](https://user-images.githubusercontent.com/50076031/125254982-f3eb7b80-e335-11eb-8e59-4b994f15ab26.png)

- 핵심 역할은 AuthenticationManager(인증 매니저)를 통해 이루어짐
- AuthenticationProvider는 인증 매니저가 어떻게 동작해야 하는지를 결정
- 최종적으로 실제 인증은 [UserDetailsService](https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/core/userdetails/UserDetailsService.html) 에 의해서 이루어짐
- 스프링 시큐리티에서 가장 핵심 개념은 인증(Authentication)과 인가(Authorization)
- 인증과 인가의 예시: 은행

```html
1. 사용자는 은행에 가서 자신이 어떤 사람인지 자신의 신분증으로 자신을 증명한다. // 인증
2. 은행에서는 사용자의 신분을 확인한다.
3. 은행에서 사용자가 금고를 열어볼 수 있는 사람인지를 판단한다. // 인가
4. 적절한 권한이 있을경우 사용자의 금고를 열어준다.
```


#### [스프링 시큐리티 필터](https://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html)
- 스프링 시큐리티에서 필터(Filter)는 JSP, 서블릿에서 사용하는 필터와 동일한 개념이지만 스프링의 빈과 연동할 수 있음
- 스프링 시큐리티의 내부에는 여러 개의 필터가 Filter Chain이라는 구조로 Request를 처리함

#### 인증을 위한 AuthenticationManager
- 필터의 핵심적인 동작은 AuthenticationManager를 통해 인증(Authentication)이라는 타입의 객체로 작업을 함
- AuthenticationManager가 가진 인증 처리 메소드(authenticate)는 파라미터, 리턴 타입 모두 Authentication

```java
public interface AuthenticationManager {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
```

- 인증이란 '스스로 증명하다'라는 의미 
- ex) 로그인하는 과정에서 전달된 아이디/패스워드로 실제 사용자에 대해 검증하는 행위는 AuthenticationManager(인증매니저)를 통해 이루어짐
- 실제 동작에서 전달되는 파라미터는 UsernamePasswordAuthenticationToken 토큰
- [UsernamePasswordAuthenticationFilter](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/authentication/UsernamePasswordAuthenticationFilter.html) 클래스의 일부 코드

```java
public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    ...

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest); // AuthenticationManager
    }
}
```

- 위 코드에서 request을 이용해 사용자의 아이디와 패스워드를 받아서 UsernamePasswordAuthenticationToken 객체 생성
- Token 객체를 AuthenticationManager의 authenticate() 에 전달
- AuthenticationManager는 다양한 방식으로 인증처리 방법을 제공해야 함(DB, 인메모리 등등)
- AuthenticationManager는 위와 같은 처리를 AuthenticationProvider로 처리함
- AuthenticationProvider는 전달되는 토큰의 타입을 처리할 수 있는 존재인지를 확인하고, 이를 통해 authenticate()를 수행
- AuthenticationProvider는 내부적으로 UserDetailsService를 이용
- UserDetailsService는 실제 인증을 위한 데이터를 가져오는 역할

#### 인가(Authorization)와 권한 제한
- Authentication 인증처리 단계가 끝나면 '사용자의 권한이 적절한가?'에 대한 처리가 필요함
- 인가(Authorization)는 '승인'의 의미

```html
현재 까지의 스프링 시큐리티 과정 간략 정리
1. 사용자는 원하는 URL을 입력
2. 스프링 시큐리티에서는 인증/인가가 필요하다고 판단하고(Filter에서 판단), 사용자가 인증하도록 로그인 화면 호출
3. 사용자는 인증을 위해 user라는 계정에 대한 정보를 입력
4. 사용자의 정보가 전달되면 AuthenticationManager가 적절한 AuthenticationProvider를 찾아서 인증 시도
5. AuthenticationProvider의 실제 동작은 UserDetailsService를 구현한 객체로 처리
6. 만약 올바른 사용자라고 인증되면 사용자의 정보를 Authentication 타입으로 전달(인증)
7. 전달된 객체로 사용자가 적절한 권한이 있는지 확인하는 인가(Authoriztaion) 과정을 거치게 됨
```

### 스프링 시큐리티 커스터마이징

#### 반드시 필요한 PasswordEncoder(패스워드 암호화)
- [BCryptPasswordEncoder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html) 가장 많이 사용하는 클래스
  - BCrypt 해시 함수 이용해서 패스워드 암호화
  - 다시 원래대로 복호화 불가능하고, 매번 암호화된 값도 다르게 썰정됨
  - 그 대신, 특정 문자열이 암호화된 결과인지만을 확인할 수 있음
  
```java
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ...

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
```

#### AuthenticationManager 설정
- AuthenticationManagerBuilder -> 코드를 통해 직접 인증 매니저 설정(InMemory)

```java
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ...

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 사용자 계정은 "user1", 패스워드는 "1234"에 대한 BCrypt~ 인코딩
    auth.inMemoryAuthentication()
            .withUser("user1")
            .password(PASSWORD_ENCODE)
            .roles("USER");
  }
}
```

#### 인가(Authorization)가 필요한 리소스 설정
- SecurityConfig 클래스에서 HttpSecurity를 파라미터로 받는 configure() 메소드 오버라이딩

```java
...
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ...

  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER");
  }
}d
```

- HttpSecurity의 formLogin() 기능은 인증/인가 절차에서 문제가 발생했을 때 로그인 페이지를 보여주도록 지정
- formLogin()을 이용하는 경우 별도의 디자인을 적용하기 위해 추가적인 설정이 필요함
  - loginPage(), loginProcessUrl(), defaultSuccessUrl(), failureUrl() 등등
  - loginPage()를 이용해 별도의 로그인 페이지 이용
- logout() 메소드를 이용하면 로그아웃 처리가 가능함
  - logoutUrl(), logoutSuccessUrl() 등을 이용해 로그아웃관련 설정을 추가할 수 있음
  

### 시큐리티를 위한 **UserDetailsService**
- 스프링 시큐리티에서는 회원이나 계정에 대해 **User** 라는 용어를 사용
- 회원 아이디라는 용어 대신 **username** 이라는 단어를 사용
  - 스프링 시큐리티에서는 **username** 이라는 단어 자체가 회원을 구별할 수 있는 식별 데이터를 의미함
- username과 password를 동시에 사용하지 않음
- 위 과정을 처리하는 가장 핵심적인 부분이 **UserDetailsService**
- **UserDetailsService** 는 loadUserByUsername() 이라는 단 하나의 메소드를 가지고 있음

```java
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
```

#### UserDetails 인터페이스
- loadUserByUsername()은 username이라는 회원 아이디를 통해 회원 정보를 가져옴

<img width="640" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/125447916-bf2d7707-c065-470d-bbba-2fd015d88f21.png">

- getAuthorities() - 사용자가 가지는 권한에 대한 정보
- getPassword() - 인증을 마무리하기 위한 패스워드 정보
- getUsername() - 인증에 필요한 아이디와 같은 정보
- isAccountNonExpired() - 계정이 만료되었는지 알 수 있는 정보
- isAccountNonLocked() - 현재 계정의 잠김 여부
- DTO처럼 클래스를 선언하고, UserDetails 인터페이스를 구현한 클래스(User)를 사용

```java
public class ClubAuthMemberDto extends User {
  private String email;
  private String name;
  private boolean fromSocial;

  public ClubAuthMemberDto(String username,
                           String password,
                           boolean fromSocial,
                           Collection<? extends GrantedAuthority> authorities) {

    super(username, password, authorities);
    this.email = username;
    this.fromSocial = fromSocial;
  }
}
```

- 위와 같이 User 클래스를 구현한 ClubAuthMemberDto 는 DTO 역할을 수행하는 동시에 스프링 시큐리티에서 인증/인가 작업에 사용할 수 있음

#### UserDetailsService 구현
- 스프링 시큐리티에서 인증을 담당하는 AuthenticationManager은 내부적으로 UserDetailsService를 호출해서 사용자의 정보를 가져옴

```java
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    // username -> 실제 로그인하는 email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService username: " + username);

        ClubMember clubMember = clubMemberRepository.findByEmail(username, false)
                .orElseThrow(() -> new UsernameNotFoundException("Check Email or Social"));
        log.info("clubMember: " + clubMember);

        // UserDetails의 구현체인 User를 구현한 Dto
        // ClubAuthMemberDto ---> User ---> UserDetails
        ClubAuthMemberDto clubAuthMemberDto = new ClubAuthMemberDto(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                getAuthority(clubMember.getRoleSet())
        );
        clubAuthMemberDto.setName(clubMember.getName());
        clubAuthMemberDto.setFromSocial(clubMember.isFromSocial());
        return clubAuthMemberDto;
    }

    private Collection<? extends GrantedAuthority> getAuthority(Set<ClubMemberRole> roleSet) {
        return roleSet.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}
```

- username이 실제로는 ClubMember에서는 email을 의미함
- 데이터베이스에 사용자가 존재하지 않을경우 UsernameNotFoundException 예외 처리
- ClubMember을 UserDetails 타입으로 처리하기 위해 ClubAuthMemberDto 타입으로 변환
- ClubMemberRole은 스프링 시큐리티에서 사용하는 SimpleGrantedAuthority로 변환
  - 이 때 'ROLE_' 라는 접두어를 추가해서 사용(스프링 시큐리티)
  - SimpleGrantedAuthority ---> GrantedAuthority
  

### Thymeleaf / Controller 에서 사용자 정보 출력
- 스프링 시큐리티에서는 Authentication이라는 타입을 이용해서 사용자의 정보를 추출할 수 있음
- 현재 프로젝트의 구조에서는 Authentication이라는 존재가 실제로는 ClubAuthMemberDto가 됨
- member.html 파일

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Member Page</h1>

<div sec:authorize="hasRole('USER')">Has USER ROLE</div>
<div sec:authorize="hasRole('MANAGER')">Has MANAGER ROLE</div>
<div sec:authorize="hasRole('ADMIN')">Has ADMIN ROLE</div>

<div sec:authorize="isAuthenticated()">
    Only Authenticated user can see this Text(Member)
</div>

Authenticated username: <div sec:authentication="name"></div>
Authenticated user roles: <div sec:authentication="principal.authorities">

</div>
</body>

</html>
```

- sec:authorize => 인가(Authorization)와 관련된 정보를 알아내거나 제어가 가능함
- Authentication의 principal 변수를 통해 ClubAuthMember의 내용을 이용할 수 있음

#### 컨트롤러에서 출력
- SecurityContextHolder 라는 객체 사용
- 직접 파라미터와 어노테이션을 사용(@AuthenticationPrincipal)

```java
Controller

    // 로그인한 사용자만 접근 가능
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info("member......");
        log.info("Auth Member: " + clubAuthMemberDto);
    }
```


<br><br>

## 스프링 시큐리티 소셜 로그인 처리
- 소셜 로그인: 기존 서비스의 인증을 사용하는 방식
- 기존 서비스를 제공하는 업체들은 공통의 인증 방식을 제공 => OAuth(Open Authorization)
- GCP에 프로젝트 생성
- OAuth 클라이언트 ID 생성
  - 리디렉션 URI 지정
- 프로젝트에 OAuth 라이브러리 추가

```java
implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
```

- 위에서 생성한 클라이언트 ID, 비밀번호 설정 & SecurityConfig 설정

```java
// properties 파일
spring.security.oauth2.client.registration.google.client-id=클라이언트 ID
spring.security.oauth2.client.registration.google.client-secret=클라이언트 PW
spring.security.oauth2.client.registration.google.scope=email


// SecurityConfig
...
http.oauth2login();
```

- DefaultOAuth2UserService(OAuth2UserService의 구현체)를 상속받는 Service 생성

![image](https://user-images.githubusercontent.com/50076031/126033754-ef914926-c218-4672-9b3a-ac267a046222.png)

- DTO(User)의 경우 OAuth2User를 구현
  - OAuth2User의 경우 모든 인증 결과를 attributes라는 이름으로 가지고 있음(Map)
  

```java
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuthUserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository clubMemberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest: " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("cilentName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> log.info(k + ": " + v));
        String email = null;

        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }
        log.info("email: " + email);
        ClubMember member = saveSocialMember(email);
        ClubAuthMemberDto clubAuthMember = new ClubAuthMemberDto(
                member.getEmail(),
                member.getPassword(),
                true,
                getAuthority(member.getRoleSet()),
                oAuth2User.getAttributes()
        );
        clubAuthMember.setName(member.getName());
        return clubAuthMember;
    }

    private ClubMember saveSocialMember(String email) {
        Optional<ClubMember> result = clubMemberRepository.findByEmail(email, true);
        if (result.isPresent()) {
            return result.get();
        }
        ClubMember member = ClubMember.builder()
                .email(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();
        member.addMemberRole(ClubMemberRole.USER);
        clubMemberRepository.save(member);

        return member;
    }

    private Collection<? extends GrantedAuthority> getAuthority(Set<ClubMemberRole> roleSet) {
        return roleSet.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}

```

```java

public class ClubAuthMemberDto extends User implements OAuth2User {

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String , Object> attr;
    
    ...

    // 모든 인증 결과
    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}

```

#### Remember me(자동 로그인)
- SecurityConfig에 설정 추가
- 쿠키(HttpCookie)를 사용
- 소셜 로그인의 경우 remember-me를 사용할 수 없음

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    private final int VALID_COOKIE_TIME = 60 * 60 * 7; // 7일
        
    ...
        
    http.rememberMe().tokenValiditySeconds(VALID_COOKIE_TIME).userDetailsService(userDetailsService);
}
```


#### @PreAuthorize
- 접근 제한이 필요한 컨트롤러의 메서드에 적용

```java
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDto;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    // 로그인을 하지 않은 사용자도 접근 가능
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void all() {
        log.info("all....");
    }

    // 로그인한 사용자만 접근 가능
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info("member......");
        log.info("Auth Member: " + clubAuthMemberDto);
    }

    // 관리자(admin) 권한을 가진 사용자만 접근 가능
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin() {
        log.info("admin........");
    }


    // 특별 정해진 사용자만 해당 메서드를 실행하도록 설정 => "user95@zerock.org" 의 사용자만 해당 메소드 접근 가능
    @PreAuthorize("#clubAuthMemberDto != null && #clubAuthMemberDto.username eq \"user95@zerock.org\"")
    @GetMapping("/only")
    public String memberOnly(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info(clubAuthMemberDto);

        return "/sample/admin";
    }
}

```