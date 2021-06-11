package easy3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Roman_to_Integer_13 {
    public static int romanToInt(String s) {
        Map<String, Integer> roman = getRomanNumerals();
        int result = 0;
        int i = 0;

        for (i = 0; i < s.length() - 1; i++) {
            String str = s.substring(i, i + 2);
            if (roman.containsKey(str)) {
                result += roman.get(str);
                i++;
            } else {
                result += roman.get(s.substring(i, i + 1));
            }
        }

        if (i == s.length() - 1) {
            result += roman.get(s.substring(i, i + 1));
        }

        return result;
    }

    private static Map<String, Integer> getRomanNumerals() {
        Map<String, Integer> roman = new HashMap<>();
        // default
        roman.put("I", 1);
        roman.put("V", 5);
        roman.put("X", 10);
        roman.put("L", 50);
        roman.put("C", 100);
        roman.put("D", 500);
        roman.put("M", 1000);

        // six instance subtraction
        roman.put("IV", 4);
        roman.put("IX", 9);
        roman.put("XL", 40);
        roman.put("XC", 90);
        roman.put("CD", 400);
        roman.put("CM", 900);

        return roman;
    }

    public int romanToInt2(String s) {
        return Arrays.stream(s
                .replace("IV", "4 ")
                .replace("IX", "9 ")
                .replace("XL", "40 ")
                .replace("XC", "90 ")
                .replace("CD", "400 ")
                .replace("CM", "900 ")
                .replace("I", "1 ")
                .replace("V", "5 ")
                .replace("X", "10 ")
                .replace("L", "50 ")
                .replace("C", "100 ")
                .replace("D", "500 ")
                .replace("M", "1000 ")
                .trim()
                .split(" "))
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        String str = "LVIII";
        System.out.println(romanToInt(str));
    }

}
