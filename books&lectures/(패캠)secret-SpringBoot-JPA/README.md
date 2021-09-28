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

### Q61) 게시판 타입 추가 API
- 동일한 게시판 제목 체크

### Q63) 게시판 타입 삭제 API
- Entity 기반 데이터베이스 스키마 설정
- 게시글이 존재하면 삭제 X (게시글 : 게시판 = N:1 -- @ManyToOne)
- H2, 파일로 스키마 생성 및 스키마 쿼리 확인 가능(테이블 생성한 스키마)

```java
// application 설정 
ddl-auto: create
generate-ddl: true

// 위 설정으로 스키마 확인 후 데이터베이스 테이블 생성
ddl-auto: none
generate-ddl: false
        
// 게시판 타입에 해당하는 게시글 리스트
List<Board> board = boardRepository.findByBoardType(boardType);

...
```

### Q65) 게시판 타입의 사용여부 설정 API
- 게시판의 사용여부에 따라 표시 & 숨기기
- 필드 추가 후 필드로 true & false 설정

### Q66) 게시판에 작성된 게시글의 개수 리턴 API

### Q67) 게시된 게시글을 최상단에 배치하는 API

### Q68) 게시된 게시글을 최상단에서 해제하는 API

### Q69) 게시글의 게시기간을 시작일과 종료일로 설정하는 API

### Q70) 게시글의 조회수 증가 API, 동일 사용자 게시글 조회수 증가 방지, JWT 인증 통과한 사용자

### Q71) 게시글 좋아요 API

### Q72) 게시글 좋아요 취소 API

### Q73) 게시된 게시글에 대해 문제가 있는 게시글을 신고하는 API

### Q74) 게시글 신고하기 목록 조회 API

### Q75) 게시글 스크랩 추가 API

### Q76) 게시글 스크랩 삭제 API

### Q77) 게시글의 북마크 추가/삭제 API

### Q78) 관심 사용자를 등록하는 API

### Q79) 관심 사용자를 삭제하는 API

### Q80) 내가 작성한 게시글 목록을 리턴하는 API

### Q81) 내가 작성한 게시글의 코멘트 목록을 리턴하는 API

### Q82) 포인트 정보 API
