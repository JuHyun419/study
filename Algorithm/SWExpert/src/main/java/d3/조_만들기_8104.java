package d3;

import java.util.Arrays;
import java.util.Scanner;

public class 조_만들기_8104 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            final int N = scanner.nextInt();
            final int K = scanner.nextInt();
            int[] ranks = new int[K];
            int rank = 1;
            int result = 1;

            for (int i = 2; i <= N; i++) {
                rank += i % 2 == 0 ? (K * 2) - 1 : 1;
                result += rank;
            }

            Arrays.fill(ranks, result);
            if (N % 2 != 0) {
                for (int i = 0; i < ranks.length; i++) {
                    ranks[i] = result++;
                }
            }
            System.out.print("#" + tc + " ");
            Arrays.stream(ranks).forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
        scanner.close();
    }
}
