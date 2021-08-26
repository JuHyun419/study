# 코드로배우는 스프링부트 웹 프로젝트 소스코드
http://www.yes24.com/Product/Goods/96051853

## :cat: 해당 도서는 카페가 존재합니다.
[`카페 이동`](https://cafe.naver.com/gugucoding)

## :blue_book: 목차

### [Part1 스프링 부트 도전하기](https://github.com/JuHyun419/study/tree/master/baooks%26lectures/%EC%BD%94%EB%93%9C%EB%A1%9C%20%EB%B0%B0%EC%9A%B0%EB%8A%94%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%EC%9B%B9%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/ex2)
  - 프로젝트를 위한 준비
    - 개발 도구의 준비
    - Springinitializr를 이용한 프로젝트 생성
    - 스프링 프로젝트 실행해 보기
    - 스프링 부트를 단독으로 실행 가능한 파일로 만들기
  - Maria 데이터베이스와 Spring Data JPA
    - MariaDB의 설치와 데이터베이스 생성
    - Spring Data JPA를 이용하는 프로젝트의 생성
    - Spring Data JPA 소개
    - 엔티티 클래스와 JpaRepository
    - 페이징/정렬 처리하기
    - 쿼리 메서드(Query Methods) 기능과 @Query
  - 스프링 MVC와 Thymeleaf
    - Thymeleaf를 사용하는 프로젝트 생성
    - Thymeleaf의 기본 사용법
    - Thymeleaf의 기본 객체와 LocalDateTime
    - Thymeleaf의 레이아웃

### [Part2 Spring MVC/JPA/Thymeleaf 연습](https://github.com/JuHyun419/study/tree/master/books%26lectures/%EC%BD%94%EB%93%9C%EB%A1%9C%20%EB%B0%B0%EC%9A%B0%EB%8A%94%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%EC%9B%B9%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/guestbook)
  - 프로젝트 구조 만들기
    - 프로젝트의 와이어프레임
    - 자동으로 처리되는 날짜/시간 설정
    - 엔티티 클래스와 Querydsl 설정
    - 서비스 계층과 DTO
    - 목록 처리
    - 컨트롤러와 화면에서의 목록 처리
    - 등록 페이지와 등록 처리
    - 방명록의 조회 처리
    - 방명록의 수정/삭제 처리
    - 검색 처리

### [Part3 N:1(다대일) 연간관계 처리하기](https://github.com/JuHyun419/study/tree/master/books%26lectures/%EC%BD%94%EB%93%9C%EB%A1%9C%20%EB%B0%B0%EC%9A%B0%EB%8A%94%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%EC%9B%B9%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/board)
  - N:1(다대일) 연관관계
    - 연관관계와 관계형 데이터베이스 설계
    - 연관관계 테스트
    - 프로젝트 적용하기
    - 컨트롤러와 화면 처리
    - JPQL로 검색
  - @RestController와 JSON 처리
    - JSON과 Ajax로 댓글 처리
    - ReplyDTO와 ReplyService / ReplyController
    - 조회 화면에서 처리

### [Part4 M:N(다대다) 관계와 파일 업로드 처리](https://github.com/JuHyun419/study/tree/master/books%26lectures/%EC%BD%94%EB%93%9C%EB%A1%9C%20%EB%B0%B0%EC%9A%B0%EB%8A%94%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%EC%9B%B9%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/mreview)
  - M:N(다대다) 관계의 설계와 구현
    - M:N(다대다) 관계의 특징
    - 예제 프로젝트 생성
    - M:N(다대다) Repository와 테스트    
  - 파일 업로드 처리
    - 파일 업로드를 위한 설정
  - 영화/리뷰 프로젝트 적용하기
    - 영화(Movie) 등록 처리
    - 목록 처리와 평균 평점
    - 조회 페이지와 영화 리뷰
    - Ajax로 영화 리뷰 처리

### [Part5 Spring Security를 이용한 로그인 처리](https://github.com/JuHyun419/study/tree/master/books%26lectures/%EC%BD%94%EB%93%9C%EB%A1%9C%20%EB%B0%B0%EC%9A%B0%EB%8A%94%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%EC%9B%B9%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/club)
  - Spring Boot와 Spring Security 연동
    - 스프링 시큐리티를 이용하는 프로젝트 생성
    - 스프링 시큐리티 커스터마이징
    - 프로젝트를 위한 JPA 처리
    - 시큐리티를 위한 UserDetailsService
    - Thymeleaf/Controller에서 사용자 정보 출력하기
  - 스프링 시큐리티 소셜 로그인 처리
    - 구글 로그인 시나리오
    - 현재 프로젝트와의 연동
    - 자동 회원 가입의 후처리
    - Remember me와 @PreAuthorize
  - API 서비스 만들기
    - API 서버를 위한 구성
    - API 서버를 위한 필터
    - API를 위한 인증처리

### Appendix
  - 양방향과 @OneToMany
    - 데이터베이스 설계와 양방향
    - 양방향 설정도 시작은 FK로
    - 양방향 참조는 상위 엔티티를 기준으로


