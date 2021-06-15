package inflearn;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class InflearnCrawling {

    private static final Logger log = LoggerFactory.getLogger(InflearnCrawling.class);
    private static final int FIRST_PAGE_INDEX = 1;
    private static final int LAST_PAGE_INDEX = 32;
    private static final String PLATFORM = "Inflearn";

    public static void insert(InflearnModel model) {
        final String url = "jdbc:mysql://localhost/study?useSSL=false";
        final String sql = "INSERT INTO Lectures VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (java.sql.Connection conn = DriverManager.getConnection(url, "root", "1234");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            pstmt.setString(1, null);
            pstmt.setString(2, model.getImageUrl());
            pstmt.setString(3, model.getTitle());
            pstmt.setInt(4, model.getPrice());
            pstmt.setInt(5, model.getSalePrice());
            pstmt.setFloat(6, model.getRating());
            pstmt.setString(7, model.getInstructor());
            pstmt.setString(8, model.getUrl());
            pstmt.setInt(9, model.getViewCount());
            pstmt.setString(10, model.getPlatform());
            pstmt.setInt(11, model.getSessionCount());
            pstmt.setString(12, model.getCurrency());
            pstmt.setString(13, model.getDescription());
            pstmt.setString(14, model.getSkills());
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            log.error("Driver loading failure");
            e.printStackTrace();
        } catch (SQLException e) {
            log.error("SQL Exception");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // 개발 강의 모든 페이징 순회
            for (int i = FIRST_PAGE_INDEX; i <= LAST_PAGE_INDEX; i++) {
                final String inflearnUrl = "https://www.inflearn.com/courses/it-programming?order=seq&page=" + i;
                System.out.println("url: " + inflearnUrl);
                Connection conn = Jsoup.connect(inflearnUrl);
                Document document = conn.get();

                // 크롤링 항목 필요 리스트
                //   - 썸네일 링크, 강의 제목, 가격(할인가격), 평점, 강의자, 강의 링크, 수강자 수, 플랫폼, 강의 세션 개수 + 시간
                Elements imageUrlElements = document.getElementsByClass("swiper-lazy");
                Elements titleElements = document.select("div.card-content > div.course_title");
                Elements priceElements = document.getElementsByClass("price");
                Elements instructorElements = document.getElementsByClass("instructor");
                Elements linkElements = document.select("a.course_card_front");
                Elements descriptionElements = document.select("p.course_description");
                Elements skillElements = document.select("div.course_skills > span");
                String[] imageUrls = new String[imageUrlElements.size()];

                int setIndex = 0;
                int getIndex = 0;

                for (Element e : imageUrlElements) {
                    imageUrls[setIndex++] = e.attr("abs:src");
                }

                for (int j = 0; j < titleElements.size(); j++) {
                    final String title = titleElements.get(j).text();
                    final String price = priceElements.get(j).text();
                    final String realPrice = getRealPrice(price);
                    final String salePrice = getSalePrice(price);
                    final int realIntPrice = toInt(removeNotNumeric(realPrice));
                    final int saleIntPrice = toInt(removeNotNumeric(salePrice));
                    final String currency = String.valueOf(price.charAt(0));
                    final String instructor = instructorElements.get(j).text();
                    final String url = linkElements.get(j).attr("abs:href");
                    final String description = descriptionElements.get(j).text();
                    final String skills = removeWhiteSpace(skillElements.get(j).text());

                    System.out.println("썸네일: " + imageUrls[j]);
                    System.out.println("강의 제목: " + title);
                    System.out.println("가격: " + realIntPrice);
                    System.out.println("할인 가격: " + saleIntPrice);
                    System.out.println("원화: " + currency);
                    System.out.println("강의자: " + instructor);
                    System.out.println("강의 링크: " + url);
                    System.out.println("강의 설명: " + description);
                    System.out.println("기술 스택: " + skills);

                    /* 강의 링크 내부 */
                    Connection innerConn = Jsoup.connect(url);
                    Document innerDocument = innerConn.get();

                    /* 평점 */
                    Element ratingElement = innerDocument.selectFirst("div.dashboard-star__num");
                    final float rating = Objects.isNull(ratingElement)
                            ? toFloat("0")
                            : toFloat(ratingElement.text());
                    System.out.println("평점: " + rating);

                    /* 수강자 수 */
                    Element listenerElement = innerDocument.selectFirst("div.cd-header__info-cover");
                    final String listener = Objects.isNull(listenerElement)
                            ? innerDocument.selectFirst("span > strong").text()
                            : innerDocument.select("div.cd-header__info-cover > span > strong").get(1).text();
                    System.out.println("수강자 수: " + removeNotNumeric(listener));
                    final int viewCount = Integer.parseInt(removeNotNumeric(listener));
                    System.out.println("플랫폼: " + PLATFORM);

                    /* 강의 세션 개수 */
                    Element sessionElement = innerDocument.selectFirst("span.cd-curriculum__sub-title");
                    final int sessionCount = Objects.isNull(sessionElement)
                            ? 82
                            : toInt(getSessionCount(innerDocument.selectFirst("span.cd-curriculum__sub-title").text()));

                    System.out.println("강의 세션 개수: " + sessionCount);
                    System.out.println();

                    InflearnModel inflearnModel = InflearnModel.builder()
                            .imageUrl(imageUrls[getIndex++])
                            .title(title)
                            .price(realIntPrice)
                            .salePrice(saleIntPrice)
                            .rating(rating)
                            .instructor(instructor)
                            .url(url)
                            .viewCount(viewCount)
                            .platform(PLATFORM)
                            .sessionCount(sessionCount)
                            .currency(currency)
                            .description(description)
                            .skills(skills)
                            .build();

                    insert(inflearnModel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRealPrice(final String price) {
        final String[] pricesArray = price.split(" ");
        return pricesArray[0];
    }

    private static String getSalePrice(final String price) {
        final String[] pricesArray = price.split(" ");
        return (pricesArray.length == 1) ? price : pricesArray[1];
    }

    // html 태그 제거
    private static String stripHtml(final String html) {
        return Jsoup.clean(html, Whitelist.none());
    }

    // 맨 앞, 맨 뒤 소괄호 제거
    private static String removeBracket(final String str) {
        return str.replaceAll("^[(]|[)]$", "");
    }

    private static String getSessionCount(final String course) {
        // ex) course = "총 21개"
        final String endStr = "개";
        return removeNotNumeric(course.substring(0, course.indexOf(endStr)));
    }

    private static String removeNotNumeric(final String str) {
        return str.replaceAll("\\W", "");
    }

    private static String removeWhiteSpace(final String str) {
        return str.replaceAll("\\s", "");
    }

    private static int toInt(final String str) {
        if (str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    private static float toFloat(final String str) {
        return Float.parseFloat(str);
    }

}
