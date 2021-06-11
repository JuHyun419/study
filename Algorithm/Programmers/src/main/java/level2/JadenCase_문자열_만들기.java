package level2;

import java.util.StringJoiner;

public class JadenCase_문자열_만들기 {

/*    public static String solution(String s) {
        String[] sSplit = s.split(" ");
        StringJoiner stringJoiner = new StringJoiner(" ");

        for (String str : sSplit) {
            String firstWordToUpper = str.substring(0, 1).toUpperCase();
            String otherWord = str.substring(1).toLowerCase();
            stringJoiner.add(firstWordToUpper + otherWord);
        }

        return stringJoiner.toString();
    } */

    public static String solution(String s) {
        String answer = "";
        String[] sSplit = s.toLowerCase().split("");
        boolean flag = true;

        for (String str : sSplit) {
            answer += flag ? str.toUpperCase() : str;
            flag = str.equals(" ");
        }
        return answer;
    }

    public static void main(String[] args) {
        String str = "3people unFollowed me";
        System.out.println(solution(str));
    }
}
