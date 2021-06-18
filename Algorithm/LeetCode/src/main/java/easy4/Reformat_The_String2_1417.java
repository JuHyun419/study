package easy4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// 24 ms -> 12 ms
public class Reformat_The_String2_1417 {
    public static String reformat(String s) {
        StringBuilder sb = new StringBuilder();
        if (s.length() == 1) return s;
        if (isAlpha(s) || isNumeric(s)) return "";
        List<Integer> numbers = new ArrayList<>();
        List<Character> alphas = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                alphas.add(s.charAt(i));
            } else {
                numbers.add(s.charAt(i) - '0');
            }
        }
        // numbers = 1 2 , alphas = a b ==> sb = 1 a 2 b
        for (int i = 0; i < alphas.size() && i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            sb.append(alphas.get(i));
        }

        if (alphas.size() == numbers.size()) {
            return sb.toString();
        }

        // sb = 1 a 2 b 숫자가 더 많으면 가장 뒤에, 문자가 더 많으면 가장 앞에 삽입
        if (numbers.size() > alphas.size()) {
            sb.append(numbers.get(numbers.size() - 1));
        } else {
            sb.insert(0, alphas.get(alphas.size() - 1));
        }
        return sb.toString();
    }

    private static boolean isNumeric(String s) {
        final Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(s).matches();
    }

    private static boolean isAlpha(String s) {
        final String regex = "^[a-zA-Z]*$";
        return s.matches(regex);
    }

    public static void main(String[] args) {
        String str1 = "covid2019";
        String str2 = "ab123";
        System.out.println(reformat(str1));
        System.out.println(reformat(str2));
    }
}
