package easy4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Reformat_The_String_1417 {
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

        if (numbers.size() > alphas.size()) {
            for (int i = 0; i < alphas.size(); i++) {
                sb.append(numbers.get(i));
                sb.append(alphas.get(i));
            }
            sb.append(numbers.get(numbers.size() - 1));
        } else if (numbers.size() < alphas.size()){
            for (int i = 0; i < numbers.size(); i++) {
                sb.append(alphas.get(i));
                sb.append(numbers.get(i));
            }
            sb.append(alphas.get(alphas.size() - 1));
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                sb.append(alphas.get(i));
                sb.append(numbers.get(i));
            }
        }
        return sb.toString();
    }

    private static boolean isNumeric(String s) {
        final Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(s).matches();
    }

    private static boolean isNumeric2(String s) {
        try {
            Long number = Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
