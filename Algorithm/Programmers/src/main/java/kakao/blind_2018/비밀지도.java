package kakao.blind_2018;

import java.util.Arrays;

public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] str1 = new String[n];
        String[] str2 = new String[n];

        for (int i = 0; i < n; i++) {
            str1[i] = fillZeros(toDecimal(arr1[i], 2), n);
            str2[i] = fillZeros(toDecimal(arr2[i], 2), n);
        }

        for (int i = 0; i < str1.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str1[i].length(); j++) {
                if (str1[i].charAt(j) == '0' && str2[i].charAt(j) == '0') {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }

    private String toDecimal(int number, int decimal) {
        String answer = "";
        while (number > 0) {
            answer = (number % decimal) + answer;
            number /= decimal;
        }
        return answer;
    }

    private String fillZeros(String str, int n) {
        if (str.length() >= n) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n - str.length()) {
            sb.append('0');
        }
        sb.append(str);

        return sb.toString();
    }

    public static void main(String[] args) {
        비밀지도 q = new 비밀지도();
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        System.out.println(Arrays.toString(q.solution(n, arr1, arr2)));
    }
}
