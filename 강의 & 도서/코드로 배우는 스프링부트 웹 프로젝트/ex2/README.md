## Spring Data JPA
### ORM과 JPA
  - ORM(Object Relational Mapping): 객체지향 패러다임을 관계형 데이터베이스 보존하는 기술
  - 객체지향 패러다임을 관계형 패러다임으로 매핑(mapping)해 주는 개념
  - 관계형 데이터베이스는 테이블 사이의 관계를 통해 구조적인 데이터를 표현,
  - 객체지향에서는 '참조(Reference)'를 통해 어떤 객체가 다른 객체들과 어떤 관계를 맺고있는지 표현
  - '객체지향을 자동으로 관계형 데이터베이스 맞게' 처리해주는 기법 -> ORM의 시작
  - 즉, ORM은 '객체지향'과 '관계형'사이의 변환 기법을 의미
  - JPA(Java Persistence API)는 ORM을 Java 언어에 맞게 사용하는 '스펙'
  - JPA는 단순한 스펙이기 때문에 해당 스펙을 구현하는 구현체마다 회사의 이름이나 프레임워크의 이름이 다름

![image](https://user-images.githubusercontent.com/50076031/123749621-96e0d600-d8f0-11eb-91f4-8d4bf7383142.png)

  - 스프링 부트는 JPA의 구현체 중 'Hibernate'라는 구현체를 이용(대부분 사용)

<br>

![image](https://user-images.githubusercontent.com/50076031/123749914-e9ba8d80-d8f0-11eb-89d2-872c42ebb424.png)
스프링에서 DB까지

  - JpaRepository<T, ID> 를 가장 많이 사용함
  - 일반적인 기능 -> CrudRepository
  - 모든 JPA 관련 기능 -> JpaRepository

```html
JpaRepository -> PagingAndSortRepository -> CrudRepository -> Repository
```

![image](https://user-images.githubusercontent.com/50076031/123750294-664d6c00-d8f1-11eb-9856-d48d07c14363.png)


```html
public interface MemoRepository extends JpaRepository<Memo, Long> {
  
}
 
// insert: save(Entity)
// select: findById(Key Type)
// update: save(Entity)
// delete: deleteById(Key), delete(Entity)


```

<br>

### 페이징/정렬 처리하기
  - 페이징 처리, 정렬 -> findAll() 메소드 사용
  - findAll() 는 PagingAndSortRepository(JpaRepository의 상위 인터페이스) 메소드로 파라미터로 전달되는 Pageable 타입의 객체에 의해 실행되는 쿼리 결정
  - 리턴 타입이 Page<T> 인 경우 반드시 파라미터를 Pageable 타입이용
  
  ![image](https://user-images.githubusercontent.com/50076031/123751260-816cab80-d8f2-11eb-8de6-1b5926409272.png)

  
#### Pageable 인터페이스
  - 실제 객체를 생성할 때는 구현체인 org.springframework.data.domain.PageRequest 클래스 사용
  - PageRequest 클래스의 생성자는 protected이므로 팩토리 메소드인 of() 를 통해 객체를 생성한다.
  
  ![image](https://user-images.githubusercontent.com/50076031/123751429-b4af3a80-d8f2-11eb-94d9-c028dd08abe9.png)

  ![image](https://user-images.githubusercontent.com/50076031/123751475-bda00c00-d8f2-11eb-95b5-c75befe28889.png)


```java
    // 페이징 처리 예시  
    @Test
    void page_Default() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);
        final int pages = result.getTotalPages();

        // then
        System.out.println(result);
        assertThat(pages).isEqualTo(10);
    }

    @Test
    void page_method() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);

        // then
        System.out.println(result);
        System.out.println("=================================");
        System.out.println("Total Pages: " + result.getTotalPages()); // 전체 개수
        System.out.println("Page Number: " + result.getNumber());     // 현재 페이지 번호 0부터 시작
        System.out.println("Page Size: " + result.getSize());         // 페이지당 데이터 개수
        System.out.println("has next page? " + result.hasNext());     // 다음 페이지 존재 여부
        System.out.println("first page? " + result.isFirst());         // 시작 페이지(0) 여부
    }

    @Test
    @DisplayName("페이징 처리를 정렬과 함께 한다.")
    void page_with_sort() {
        // given
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);

        // then
        result.get().forEach(System.out::println);
    }
```

<br>
  
### 쿼리 메서드(Query Methods) 기능과 @Query
  - JPQL(Java Persistence Query Language): 객체 지향 쿼리
  - 쿼리 메서드: 메서드의 이름 자체가 쿼리의 구문으로 처리되는 기능
  - @Query: SQL과 유사하게 엔티티 클래스의 정보를 이용해서 쿼리를 작성하는 기능(JPQL)
  - Querydsl: 동적 처리 기능
  
#### 쿼리 메서드(Query Methods)
  - 메소드의 이름이 'findBy~' 나 'getBy~' 로 시작함
  - SELECT를 하는 조회의 경우 List 타입이나 배열을 이용할 수 있음
  - 파라미터에 Pageable 타입을 넣는 경우에는 무조건 Page<E> 타입 리턴
  
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
  
![image](https://user-images.githubusercontent.com/50076031/123752168-85e59400-d8f3-11eb-969a-2abfac817223.png)

  
#### @Query 어노테이션
  - 메소드의 이름과 상관없이 메소드에 추가한 어노테이션을 통해 원하는 처리가 가능
  - @Query의 value는 JPQL(Java Persistence Query Language)로 작성하는데 흔히 '객체지향 쿼리'라고 함
  - @Query를 통해 다음과 같은 작업을 실행할 수 있음
    - 필요한 데이터만 선별적으로 추출하는 기능이 가능
    - 데이터베이스에 맞는 순수한 SQL(Native SQL)을 사용하는 기능
    - insert, update, delete와 같은 select가 아닌 DML 등을 처리하는 기능(@Modifying과 함께 사용)
  
```sql
// 'id의 역순으로 정렬'
@Query("select m from Member m order by m.id desc")
List<Member> getListDesc();
```

  - JPQL은 데이터베이스의 테이블 대신 엔티티 클래스와 멤버 변수를 이용해서 SQL과 비슷한 JPQL을 작성
  
#### @Query와 페이징 처리
  - @Query를 이용하는 경우도 Pageable 타입의 파라미터를 적용하면 페이징 처리, 정렬 부분 생략 가능
  - 리턴 타입이 Page<Entity>인 경우 count를 계산할 수 있는 쿼리가 필수
  - 따라서 @Query를 이용할 때는 별도의 countQuery라는 속성을 적용
  
```sql
@Query(value = "select m from Member m where m.id > :id",
  countQuery = "select count(m) from Member m where m.id > :id")
Page<Member> getListWithQuery(@Param("id") Long id, Pageable pageable);
```

#### Object[] 리턴
  - @Query 장점 중 하나는 쿼리 메소드의 경우 엔티티 타입의 데이터만을 추출하지만, @Query를 이용하면 현재 필요한 데이터만 Object[] 형태로 추출할 수 있음
  
```sql
@Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno",
  countQuery = "select count(m) from Memo m where m.mno > :mno")
Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);
  
// Object[0] = Long mno, Object[1] = String memoText, Object[2] = LocalDateTime currentDate
```
  
#### Native SQL 처리
  - @Query는 데이터베이스 고유의 SQL 구문을 그대로 활용할 수 있음
  
```sql
@Query(value = "select * from memo where mno > 0", nativeQuery = true)
List<Object[]> getNativeQuery();
```
  
  
