package d3;

import java.util.Scanner;

public class 농작물_수확하기_2805 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            final int N = scanner.nextInt();
            final int[][] array = getCrops(N);
            final int half = N / 2;
            int from = half;
            int to = from;
            int sum = 0;

            for (int i = 0; i < N; i++) {
                for (int j = from; j <= to; j++) {
                    sum += array[i][j];
                }

                if (i < half) { // 위쪽 -> 2개씩 증가
                    from--;
                    to++;
                } else { // 아래쪽 -> 2개씩 감소
                    from++;
                    to--;
                }
            }
            System.out.println("#" + tc + " " + sum);
        }
        scanner.close();
    }

    private static int[][] getCrops(int n) {
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            for (int j = 0; j < n; j++) {
                array[i][j] = str.charAt(j) - '0';
            }
        }
        return array;
    }
}
