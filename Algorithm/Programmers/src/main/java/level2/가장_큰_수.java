package level2;

import java.util.Arrays;

public class 가장_큰_수 {

    public String solution(int[] numbers) {
        String[] strArray = toStrArray(numbers);
        // 3, 30 ----> 330 vs 303 비교
        Arrays.sort(strArray, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        // 정렬 후 첫 번째 원소가 '0'인 경우 나머지도 '0'이므로 "0"을 리턴
        if (isFirstElementZero(strArray[0].charAt(0))) {
            return "0";
        }
        return maxNumber(strArray);
    }

    private String[] toStrArray(final int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
    }

    private boolean isFirstElementZero(char c) {
        return c == '0';
    }

    private String maxNumber(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str);
        }
        return sb.toString();
    }

}
