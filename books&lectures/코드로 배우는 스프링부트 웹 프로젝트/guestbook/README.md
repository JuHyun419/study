## Spring MVC/JPA/Thymeleaf 연습

### 학습 내용
  - 프로젝트의 계층별 구조와 객체들의 구성
  - Querydsl을 이용해서 동적으로 검색 조건을 처리하는 방법
  - Entity와 DTO의 구분
  - 화면에서의 페이징 처리

<br>

### 자동으로 처리되는 날짜/시간 설정(BaseEntity)

```java
package org.zerock.guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 테이블 생성 X
@EntityListeners(value = {AuditingEntityListener.class}) // JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할
@Getter
public class BaseEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate // JPA에서 엔티티의 생성 시간 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @LastModifiedDate // 최종 수정 시간을 자동으로 처리
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

@SpringBootApplication
@EnableJpaAuditing // JPA를 이용하면서 AuditionEntityListener 활성화
public class GuestbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestbookApplication.class, args);
    }

}

```

<br>

### 동적 쿼리 처리를 위한 Querydsl 설정
  - Q도메인 이용
  - Querydsl 라이브러리를 이용해서 엔티티 클래스를 Q도메인 클래스로 변환 -> 추가 설정 필요
  - build.gradle 파일에 아래 내용 처리
    - plugins 항목에 querydsl 관련 부분 추가
    - dependencies 항목에 필요한 라이브러리 추가
    - Gradle에서 사용할 추가적인 task 추가
    
```java
// plugins에 추가
id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10' // Querydsl

        
// dependencies에 추가
implementation 'com.querydsl:querydsl-jpa'


// 그 아래 추가
def querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}

sourceSets {
    main.java.srcDir querydslDir
}

configurations {
    querydsl.extendsFrom compileClasspath
}

compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}
```

  - build.gradle 파일 갱신 후 compileQuerydsl task 실행

![image](https://user-images.githubusercontent.com/50076031/123900326-d61c2f00-d9a3-11eb-8089-9ab70b2aa9b6.png)

![image](https://user-images.githubusercontent.com/50076031/123900461-14195300-d9a4-11eb-96ec-0d7bf7542b33.png)

  - QuerydslPredicateExecutor 인터페이스 추가 상속
```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
        QuerydslPredicateExecutor<Guestbook> {

}

```

<br>

### Querydsl
- 정적 타입을 이용해서 SQL과 같은 쿼리를 생성할 수 있도록 해주는 프레임워크
- Querydsl이 제공하는 Fluent API를 이용해서 쿼리 생성 가능
- 타입에 안전(컴파일 시 문법 오류 확인 가능)
- [`공식 문서(한글)`](http://www.querydsl.com/static/querydsl/3.4.3/reference/ko-KR/html_single/#d0e194)
- [`공식 문서(영어)`](http://www.querydsl.com/static/querydsl/4.4.0/reference/html_single)
- 검색 & 페이징 처리(BooleanBuilder + BooleanExpression)
    - BooleanBuilder : 쿼리의 WHERE 문에 들어가는 조건들을 넣어주는 컨테이너
- Querydsl 사용법
  - BooleanBuilder 생성
  - 조건에 맞는 구문은 Querydsl에서 사용하는 Predicate 타입의 함수를 생성
  - BooleanBuilder에 작성된 Predicate를 추가하고 실행

```java
...

    @Test
    @DisplayName("Querydsl로 title 검색 조건을 처리한다.")
    void test_Querydsl1() {
        /* given */
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "20";
        // BooleanBuilder -> Where 문에 들어가는 조건들을 넣어주는 컨테이너
        BooleanBuilder builder = new BooleanBuilder();

        /* when */
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        /* then */
        result.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("Querydsl로 다중 항목 검색 조건을 처리한다.")
    void test_Querydsl2() {
        /* given */
        Pageable pageable = PageRequest.of(0, 50, Sort.by("gno").ascending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String titleKeyword = "10";
        String contentKeyword = "20";
        BooleanBuilder builder = new BooleanBuilder();

        /* when */
        BooleanExpression exTitle = qGuestbook.title.contains(titleKeyword);
        BooleanExpression exContent = qGuestbook.content.contains(contentKeyword);
        BooleanExpression exAll = exTitle.or(exContent);
        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        /* then */
        result.stream().forEach(System.out::println);
    }
```

![image](https://user-images.githubusercontent.com/50076031/123901476-f3ea9380-d9a5-11eb-81c5-b288b5e0e13f.png)

![image](https://user-images.githubusercontent.com/50076031/123901517-0c5aae00-d9a6-11eb-8269-79393af50212.png)

  - 동적 처리를 위해 Q도메인 클래스를 얻어옴
    - Q도메인 클래스를 이용하면 엔티티 클래스에 선언된 title, content같은 필드들을 변수로 활용할 수 있음
  - BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너
  - 원하는 조건은 필드 값과 같이 결합해서 생성
    - BooleanBuilder 안에 들어가는 값은 com.querydsl.core.types.Predicate 타입이어야 함
  - 만들어진 조건은 where문에 and나 or같은 키워드와 결합
  - BooleanBuilder는 Repository에 추가된 QuerydslPredicateExcutor 인터페이스의 findAll() 메소드 사용


### 목록 화면
![image](https://user-images.githubusercontent.com/50076031/123904899-3ca54b00-d9ac-11eb-8706-23dba09e3834.png)

### 등록 화면
![image](https://user-images.githubusercontent.com/50076031/123904908-4464ef80-d9ac-11eb-93df-9766ba79af28.png)

### 조회 화면
![image](https://user-images.githubusercontent.com/50076031/123904967-5c3c7380-d9ac-11eb-8803-ac12ab3257a2.png)

### 수정 & 삭제 화면
![image](https://user-images.githubusercontent.com/50076031/123904982-6494ae80-d9ac-11eb-889e-c74419f2e1cd.png)


