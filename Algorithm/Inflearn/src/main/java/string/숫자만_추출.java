package string;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 숫자만_추출 {

    public static void main(String[] args) {
        숫자만_추출 T = new 숫자만_추출();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.print(T.solution(str));
    }

    private int solution(String word) {
//        return toInt(removeText(word));
        return toInt(getDigit(word));
    }

    private String removeText(String word) {
        return word.replaceAll("\\D", "");
    }

    private String getDigit(String word) {
        return Arrays.stream(word.split(""))
                .filter(str -> Character.isDigit(str.charAt(0)))
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    private int toInt(String str) {
        return Integer.parseInt(str);
    }
}
