package kit.sort;

import java.util.Arrays;

public class 가장_큰_수 {
    public static String solution(final int[] n) {
        final String[] numbers = Arrays.stream(n).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(numbers, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        final String result = arrayToString(numbers);
        return result.charAt(0) == '0' ? "0" : result;
    }

    private static String arrayToString(final String[] numbers) {
        final StringBuilder sb = new StringBuilder();
        for (final String str : numbers) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(final String[] args) {
        final int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }

}
