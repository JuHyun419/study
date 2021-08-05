package d2;

import java.util.Scanner;

public class 파리_퇴치_2001 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int[][] array = createArray(N);
            int max = 0;

            for (int row = 0; row <= N - M; row++) {
                for (int col = 0; col <= N - M; col++) {
                    int sum = 0;
                    for (int j = 0; j < M; j++) {
                        for (int k = 0; k < M; k++) {
                            sum += array[row + j][col + k];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
            System.out.println("#" + i + " " + max);
        }
    }

    private static int[][] createArray(int N) {
        int[][] array = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        return array;
    }
}
