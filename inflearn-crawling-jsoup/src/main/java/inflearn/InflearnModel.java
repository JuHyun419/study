package inflearn;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
