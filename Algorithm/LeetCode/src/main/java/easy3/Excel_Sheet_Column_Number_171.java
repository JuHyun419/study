package easy3;

import java.util.HashMap;
import java.util.Map;

public class Excel_Sheet_Column_Number_171 {
    public static int titleToNumber(final String str) {
        final Map<Character, Integer> map = new HashMap<>();
        int number = 1;
        int result = 0;
        int exponent = str.length() - 1;

        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c, number++);
        }

        if (str.length() == 1) {
            return map.get(str.charAt(0));
        }

        // ABC ==> str1 = "AB", str2 = "C"
        final String str1 = str.substring(0, str.length() - 1);
        final String str2 = str.substring(str.length() - 1);

        for (int i = 0; i < str1.length(); i++) {
            result += Math.pow(26, exponent) * map.get(str1.charAt(i));
            exponent -= 1;
        }
        result += map.get(str2.charAt(0));
        return result;
    }

    public static int titleToNumber2(final String str) {
        int sum = 0;
        final int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            sum += Math.pow(26, length - i - 1) * (((int) str.charAt(i)) - 64);
        }
        return sum;
    }

    public static void main(final String[] args) {
        final String str1 = "A";
        final String str2 = "AB";
        final String str3 = "ZY";
        final String str4 = "AAA";

        System.out.println(titleToNumber(str1));
        System.out.println(titleToNumber(str2));
        System.out.println(titleToNumber(str3));
        System.out.println(titleToNumber(str4));
    }
}
