package d2;

import java.util.Scanner;

public class 두_개의_숫자열_1959 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            int N = scan.nextInt();
            int M = scan.nextInt();

            int[] arrayA = new int[N];
            int[] arrayB = new int[M];

            for (int j = 0; j < N; j++) {
                arrayA[j] = scan.nextInt();
            }

            for (int j = 0; j < M; j++) {
                arrayB[j] = scan.nextInt();
            }

            int max = 0;
            int minLength = Math.min(N, M);

            for (int j = 0; j <= Math.abs(M - N); j++) {
                int sum = 0;
                for (int k = 0; k < minLength; k++) {
                    if (N < M) { // 배열 A를 움직인다
                        sum += arrayA[k] * arrayB[j + k];
                    } else { // 배열 B를 움직인다
                        sum += arrayB[k] * arrayA[j + k];
                    }
                }
                max = Math.max(max, sum);
            }
            System.out.println("#" + (i + 1) + " " + max);
        }
    }
}
