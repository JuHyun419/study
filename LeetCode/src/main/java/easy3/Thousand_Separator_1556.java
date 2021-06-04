package easy3;

import java.text.DecimalFormat;

public class Thousand_Separator_1556 {
    public static String thousandSeparator(int n) {
        if (n == 0) return "0";
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(n).replaceAll(",", ".");
    }

    public static String thousandSeparator2(int n) {
        if (n == 0) return "0";
        return String.format("%,d", n).replaceAll("\\,", ".");
    }

    public static void main(String[] args) {
        int n = 1234123412;
        System.out.println(thousandSeparator2(n));
    }

}
