package level1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 숫자_짝꿍 {

    /**
     * 1. X를 number : count로 Map에 저장
     * 2. Y를 Map에서 조회하면서 존재하면 -1 및 정수 추가
     * 3. 추가한 정수가 하나도 없을경우(length == 0) "-1" 리턴
     * 4. 추가한 정수로 가장 큰 정수 만들기(문자열) -> 정렬
     */
    public static String solution(String X, String Y) {
        Map<String, Integer> xNumbers = new HashMap<>();

        // 1.
        for (int i = 0; i < X.length(); i++) {
            String number = String.valueOf(X.charAt(i));
            xNumbers.put(number, xNumbers.getOrDefault(number, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        // 2.
        for (int i = 0; i < Y.length(); i++) {
            String number = String.valueOf(Y.charAt(i));
            if (xNumbers.containsKey(number)) {
                sb.append(number);
                xNumbers.put(number, xNumbers.get(number) - 1);

                if (xNumbers.get(number) == 0) {
                    xNumbers.remove(number);
                }
            }
        }

        // 3.
        if (sb.length() == 0) return "-1";

        // 4.
        final String result = toString(sortReverse(toCharacterArray(sb)));

        return result.startsWith("0") ? "0" : result;
    }

    private static Character[] toCharacterArray(StringBuilder sb) {
        return sb.toString()
                .chars()
                .mapToObj(c -> (char)c).toArray(Character[]::new);
    }

    private static Character[] sortReverse(Character[] array) {
        Arrays.sort(array, Collections.reverseOrder());

        return array;
    }

    private static String toString(Character[] array) {
        StringBuilder sb = new StringBuilder();

        for (Character character : array) {
            sb.append(character);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("12321", "42531"));
    }

}
