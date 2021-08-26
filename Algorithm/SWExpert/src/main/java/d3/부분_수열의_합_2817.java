package d3;

import java.util.Scanner;

// TODO:
public class 부분_수열의_합_2817 {

    private static int N;
    private static int K;
    private static int[] numbers;
    private static int ans;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = scanner.nextInt();
            K = scanner.nextInt();
            numbers = new int[N];
            ans = 0;
            for (int i = 0; i < N; i++) {
                numbers[i] = scanner.nextInt();
            }

            dfs(0, 0);
            System.out.println("#" + tc + " " + ans);
        }
        scanner.close();
    }

    private static void dfs(int count, int sum) {
        if (sum >= K || count == N) {
            if (sum == K) {
                ans++;
            }
            return;
        }
        dfs(count + 1, sum + numbers[count]); // 자기 자신 포함
        dfs(count + 1, sum); // 자기 자신 미포함
    }
}
