package string;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Reformat_Date_1507 {
    public String reformatDate(String date) {
        final String[] dates = date.split(" ");
        final String year = dates[2];
        final String month = getFormatMonth(dates[1]);
        final String day = getFormatDay(dates[0]);

        return getFormatDate("-", year, month, day);
    }

    /**
     * Convert String month to Integer month
     * @param month: "Jan", "Feb", ..., "Dec"
     * @return: Integer 형식의 month
     */
    private String getFormatMonth(final String month) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        TemporalAccessor accessor = parser.parse(month);
        String formatMonth = String.valueOf(accessor.get(ChronoField.MONTH_OF_YEAR));
        if (formatMonth.length() == 1) {
            return "0" + formatMonth;
        }
        return formatMonth;
    }

    private String getFormatDay(String day) {
        day = day.replaceAll("\\D", ""); // 숫자 제외한 모든 문자 제거
        if (day.length() == 1) {
            return "0" + day;
        }
        return day;
    }

    private String getFormatDate(String delimiter, String year, String month, String day) {
        return String.join(delimiter, year, month, day);
    }

    public static void main(String[] args) {
        Reformat_Date_1507 r = new Reformat_Date_1507();
        System.out.println(r.getFormatMonth("February"));
    }
}
