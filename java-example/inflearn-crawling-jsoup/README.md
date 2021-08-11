## 인프런 사이트 크롤링
### [`블로그 정리`](https://zzang9ha.tistory.com/337?category=887593)

```java
// 크롤링할 데이터
public class InflearnModel {

    private Long id;            // PK
    private String imageUrl;    // 썸네일
    private String title;       // 강의 제목
    private int price;          // 정가
    private int salePrice;      // 할인 가격
    private float rating;       // 평점
    private String instructor;  // 강의자
    private String url;         // 강의 페이지 링크
    private int viewCount;      // 수강자 수
    private String platform;    // 플랫폼 이름
    private int sessionCount;   // 강의 세션 개수
    private String currency;    // 원화
    private String description; // 강의 부연설명
    private String skills;      // 강의 스택

}
```

```java
// 크롤링 URL, page: 1 ~ 32 까지 존재
final String url = "https://www.inflearn.com/courses/it-programming?order=seq&page=" + i;
```

<img width="820" alt="캡쳐 2" src="https://user-images.githubusercontent.com/50076031/113498883-ee6ae200-954b-11eb-941b-1349ffed8c46.PNG">


