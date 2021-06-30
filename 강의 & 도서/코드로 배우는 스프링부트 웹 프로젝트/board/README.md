## N:1(다대일) 연관관계

### 학습 내용
  - 회원, 게시글, 댓글 연관관계 적용
  - 연관관계를 분석하고 @ManyToOne을 이용한 연관관계 설정하는 방법
  - 연관관계가 없는 상황에서 '(left outer) join' 처리 방법
  - 즉시(Eager) 로딩과 지연(Lazy) 로딩의 차이와 효율적인 처리 방법

#### 연관관계와 관계형 데이터베이스 설계
  - 관계형 데이터베이스에서는 개체(Entity) 간의 관계(Relation)라는 것에 대해 고민하게 됨
  - 관계에서 가장 중요한 것이 PK(Primary Key)와 FK(Foreign Key)를 어떻게 설정해서 사용하는가에 대한 설정이다.
  - 테이블간의 관계는 특정 PK가 다른 곳에서 몇 번 FK로 사용되었는지가 중요함
    - **우선 어떤 PK를 기준으로 할 것인지를 고민하고, 해당 PK가 다른 곳에서 몇 번 사용되었는지를 세어보는 방식으로 찾는다.**

![image](https://user-images.githubusercontent.com/50076031/123914168-12f32080-d9ba-11eb-8f1c-b1f83cc9c9ab.png)

  - 회원(Member) 데이터 쪽이 '일(One)' 이고, 게시글(Board) 데이터는 동일 회원의 아이디(PK)가 여러 번 나오므로 '다(Many)'로 판단
  - 회원과 게시글의 명제는 다음과 같이 해석되어야 함
    - 한 명의 회원(One)은 여러 개의 게시글(Many)을 작성할 수 있다.
  - 게시글과 댓글의 관계는 다음과 같음
    - 하나의 게시글(One)은 여러 개의 댓글(Many)을 가질 수 있다.
    
#### **PK로 설계, FK로 연관관계 해석**
  - JPA를 이용해서 연관관계를 해석할 때는 PK를 기준으로 잡고, 데이터베이스를 모델링하는 방식으로 구성
  - PK로 설계, FK로 연관관계 해석 -> 회원(Member), 게시글(Board), 댓글(Reply) 관계

#### FK를 기준으로 관계를 해석하면 다음과 같음

```html
게시물은 회원과 다대일(N:1) 관계

댓글은 게시물과 다대일(N:1) 관계
```

#### 회원(Member), 게시글(Board), 댓글(Reply) Entity 설정
  - 회원 엔티티가 게시물 엔티티들을 참조하게 설정해야 하는가? (X)
  - 게시물 엔티티에서 회원 엔티티를 참조하게 설정해야 하는가? (O) @ManyToOne
  - 회원, 게시물 엔티티 객체 양쪽에서 서로를 참조하게 설정해야 하는가? (X) 필요할 때

```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    private String email;

    @Column
    private String password;

    @Column
    private String name;
}



import ...

@Entity
@Getter
@ToString(exclude = "member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column
    private String title;

    @Column
    private String content;

    // 영속성 전이(Cascade)
    @ManyToOne
    private Member member;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}



import ...

@Entity
@Getter
@ToString(exclude = "board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column
    private String text;

    @Column
    private String replyer;

    @ManyToOne
    Board board;
}

```

<br>

### @ManyToOne과 Eager/Lazy Loading
- 두 개 이상의 엔티티 간의 연관관계를 맺고 나면 쿼리를 실행하는 데이터베이스 입장에서는 고민이 생김
- 엔티티 클래스들이 실제 데이터베이스상에서는 두 개 이상의 테이블로 생성되기 때문에 연관관계를 맺고 있는것 -> 조인이 필요함
- @ManyToOne의 경우 FK(Board)의 엔티티를 가져올 때 PK(Member)의 엔티티도 같이 가져옴
- Reply와 Board도 @ManyToOne의 관계이므로 자동으로 조인이 처리됨

```java
    @Test
    @DisplayName("")
    void readReply1() {
        /* given */
        Optional<Reply> result = replyRepository.findById(10L);

        /* when */
        Reply reply = result.get();

        /* then */
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }


Hibernate:
    select
        reply0_.rno as rno1_2_0_,
        reply0_.moddate as moddate2_2_0_,
        reply0_.regdate as regdate3_2_0_,
        reply0_.board_bno as board_bn6_2_0_,
        reply0_.replyer as replyer4_2_0_,
        reply0_.text as text5_2_0_,
        board1_.bno as bno1_0_1_,
        board1_.moddate as moddate2_0_1_,
        board1_.regdate as regdate3_0_1_,
        board1_.content as content4_0_1_,
        board1_.member_email as member_e6_0_1_,
        board1_.title as title5_0_1_,
        member2_.email as email1_1_2_,
        member2_.moddate as moddate2_1_2_,
        member2_.regdate as regdate3_1_2_,
        member2_.name as name4_1_2_,
        member2_.password as password5_1_2_
    from
        reply reply0_
    left outer join
        board board1_
    on reply0_.board_bno=board1_.bno
    left outer join
        member member2_
    on board1_.member_email=member2_.email
    where
        reply0_.rno=?
        
Reply(rno=10, text=Reply...10, replyer=guest)
Board(bno=42, title=Title...42, content=Content...42)
```

  - Reply를 가져올 때 매번 Board, Member까지 조인해서 가져올 필요가 많지 않음(성능 문제도 존재)

### [fetch는 Lazy Loading을 권장](https://zzang9ha.tistory.com/347?category=990401)
  - Eager(즉시) 로딩: 한 번에 연관관계가 있는 모든 엔티티를 가져온다는 장점이 있음
  - 하지만, 여러 연관관계를 맺고 있거나 연관관계가 복잡할수록 조인으로 인한 성능 저하를 피할 수 없음
  - JPA에서 연관관계의 데이터를 어떻게 가져올 것인가를 fetch(패치)라고 함
  - 연관 관계의 어노테이션의 속성으로 'fetch'모드 지정
  - 'Lazy loading(지연 로딩)'을 권장
  - 각 연관관계의 default 속성은 다음과 같음
    - @OneToMany: LAZY
    - @ManyToOne: EAGER
    - @ManyToMany: LAZY
    - @OneToOne: EAGER
    
```java
Reply 엔티티

@ManyToOne(fetch = FetchType.LAZY)
Board board


// 아래와 같이 reply의 Board를 조회하고자 할 때, 메서드 상단부에 @Transactional 어노테이션 추가
System.out.println(reply);
System.out.println(reply.getBoard());
```

#### '지연 로딩을 기본으로 사용하고, 상황에 맞게 필요한 방법을 찾자'

