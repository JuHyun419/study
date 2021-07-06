## Spring Security를 이용한 로그인 처리

### SpringBoot와 Spring Security 연동
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
  
