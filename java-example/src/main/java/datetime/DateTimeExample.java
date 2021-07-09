package datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeExample {

    public static void main(String[] args) {
        /* 지금 순간을 기계 시간으로 표현 */
        Instant instant = Instant.now();
        // instant = 2021-07-09T03:31:05.062802Z
        System.out.println("instant = " + instant); // 기준시: UTC, GMT
        // 2021-07-09T03:31:05.062802Z[UTC]
        System.out.println(instant.atZone(ZoneId.of("UTC")));


        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        // zoneId = Asia/Seoul
        System.out.println("zoneId = " + zoneId);
        // zonedDateTime = 2021-07-09T12:31:05.062802+09:00[Asia/Seoul]
        System.out.println("zonedDateTime = " + zonedDateTime);


        /* 인류용 일시 표현 */
        LocalDateTime now = LocalDateTime.now();
        // 2021-07-09T12:31:05.171368
        System.out.println(now);
        LocalDateTime birthDay =
                LocalDateTime.of(2021, Month.APRIL, 19, 0, 0, 0);
        // birthDay = 2021-04-19T00:00
        System.out.println("birthDay = " + birthDay);


        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        // nowInKorea = 2021-07-09T12:31:05.171916+09:00[Asia/Seoul]
        System.out.println("nowInKorea = " + nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        // zonedDateTime1 = 2021-07-09T12:31:05.172089+09:00[Asia/Seoul]
        System.out.println("zonedDateTime1 = " + zonedDateTime1);


        /* 기간 표현 */
        LocalDate today = LocalDate.now();
        LocalDate jhBirthDay = LocalDate.of(1993, Month.APRIL, 19);
        Period period = Period.between(jhBirthDay, today); // inclusive, exclusive
        // period = 28
        System.out.println("period = " + period.getYears());

        Period until = jhBirthDay.until(today);
        // until = 28
        System.out.println("until = " + until.getYears());


        /* 포맷 */
        // 미리 정의해둔 포맷: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime = 2021-07-09T12:31:05.202452
        System.out.println("localDateTime = " + localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // formatter = 2021/07/09
        System.out.println("formatter = " + now.format(formatter));
    }

}
