package level3;

import java.util.Arrays;

public class 최고의_집합 {
    public static int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};

        if (s % n == 0) {
            int[] result = new int[n];
            Arrays.fill(result, (s / n));
            return result;
        }

        /**
         * ex) n = 4, s = 11 일때 => 집합: {2, 3, 3, 3}
         * 11 / 4 = 2, 11 % 4 = 3
         * 11 % 4의 결과인 3 만큼 큰 숫자(2 + 1)을 넣고, 나머지는 작은 숫자(2)를 넣는다
         */
        int[] answer = new int[n];
        int rest = s % n;
        int number = s / n;

        for (int i = 0; i < rest; i++) {
            answer[i] = number + 1;
        }
        for (int j = rest; j < n; j++) {
            answer[j] = number;
        }
        Arrays.sort(answer);

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int s = 11;

        solution(n, s);
        solution(n, 13);
    }
}
