## [패스트캠퍼스 시크릿코드: SpringBoot/JPA](https://fastcampus.co.kr/) 

### Q23) 게시글 삭제(데이터베이스 삭제 X)
- 삭제 여부, 삭제 일자 컬럼

### Q27) 게시글 작성
- 예외 발생시 에러 컬렉션 형태로 리턴하기

### Q30) 공지사항 등록 중복 체크
- 게시글 & 제목 내용이 동일한지 판단
- 특정 시간(1분)에 작성되었는지 판단

### Q38) 비밀번호 암호화
- BCryptPasswordEncoder

### Q42) 내가 좋아요한 공지사항 조회

### Q43~Q45) 이메일 + 비밀번호를 통해 JWT(Json Web Token) 토큰 발행

### Q46) JWT 토큰 재발행(특정 정보 인증)
- 정상적인 회원에 대해 재발행

### Q49) 사용자 상세 조회를 조회하는 API를 조건에 맞게 구현
- Response 커스텀하기
- ResponseMessage, ResponseMessageHeader

```html
{
    "header": {
        result: true | false,
        resultCode: string,
        message: message,
        status: http status code
    },
    "body": body 내용
}
```

### Q50) 이메일, 이름, 전화번호에 대한 사용자 검색 API
- JPA 쿼리 메소드

```java
메소드명: findByEmailContainsOrPhoneContainsOrUserNameContains(...)
```
### Q53) 사용자의 접속 이력 조회, 관리
- UserLoginHistory 엔티티를 만들고, 따로 관리

### Q54~Q55) 사용자 접속 제한 & 접속 제한 해제
- Boolean 타입의 필드로 관리

### Q56) 회원 전체수와 상태별 회원 수(Status, Enum)에 대한 정보 리턴(통계)
- JPA 쿼리 메소드

```java
메소드명: countByStatus
```

### Q57) 오늘 가입한 사용자의 목록 리턴하는 API
- LocalDateTime, JPQL
- startDate ~ endDate 사이인(Between) 날짜

```java
// Service
LocalDateTime now = LocalDateTime.now();
LocalDateTime startDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
LocalDateTime endDate = startDate.plusDays(1);

List<User> users = userRepository.findTodayUsers(startDate, endDate);

// Repository
@Query("select u from User u where regDate between :startDate and :endDate")
List<User> findTodayUsers(LocalDateTime startDate, LocalDateTime endDate);
```

### Q58) 사용자별 게시글 수를 리턴하는 API
- Custom Repository, EntityManager, nativeQuery 사용
- 사용자 -> id, 이메일, 이름

```java
// custom repository

@RequiredArgsConstructor
@Repository
public class UserCustomRepository {

    private final EntityManager em;

    public List<UserNoticeCount> findUserNoticeCount() {
        final String sql = "select " +
                "u.id, " +
                "u.email, " +
                "u.user_name, " +
                "(select count(*) from notice n where n.user_id = u.id) notice_count " +
                "from User u ";

        return em.createNativeQuery(sql).getResultList();
    }
}
```

### Q60) 좋아요를 가장 많이 한 사용자(n명) 목록을 리턴하는 API
- Custom Repository, EntityManager, nativeQuery