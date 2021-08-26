package d3;

import java.util.Scanner;

public class 상원이의_연속_합_7510 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = scanner.nextInt();
            int answer = 1;

            for (int i = 1; i <= N / 2; i++) {
                int sum = 0;
                for (int j = i; ; j++) {
                    sum += j;
                    if (sum == N) {
                        answer ++;
                    }
                    if (sum > N) {
                        break;
                    }
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
        scanner.close();
    }
}
