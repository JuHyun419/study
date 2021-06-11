package level2;

import java.util.Arrays;

public class 최댓값과_최솟값 {

    public static String solution(String s) {
        int[] arr = strToInt(split(s));
        Arrays.sort(arr);

        return findMinAndMax(arr);
    }

    private static String[] split(final String text) {
        return text.split(" ");
    }

    private static int[] strToInt(final String[] split) {
        int[] result = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    private static String findMinAndMax(final int[] arr) {
        return arr[0] + " " + arr[arr.length - 1];
    }

    public static void main(String[] args) {
        String s = "1 2 3 4";
        String s2 = "-1 -2 -3 -4";

        System.out.println(solution(s));
        System.out.println(solution(s2));

    }
}
