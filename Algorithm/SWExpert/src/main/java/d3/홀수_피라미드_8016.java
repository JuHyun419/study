package d3;

import java.util.Scanner;

public class 홀수_피라미드_8016 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            final long N = scanner.nextLong();
            long left = (N - 1) * (N - 1) * 2 + 1;
            long right = N * N * 2 - 1; // 다음 층 left - 2
            System.out.println("#" + tc + " " + left + " " + right);
        }
        scanner.close();
    }
}
