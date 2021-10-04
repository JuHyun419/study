import java.util.Scanner;

public class N과M_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int N = scanner.nextInt();
        final int M = scanner.nextInt();
        int[] numbers = new int[M];
        StringBuilder sb = new StringBuilder();

        dfs(numbers, N, M, 0, sb);

        System.out.println(sb.toString());

        scanner.close();
    }

    private static void dfs(int[] numbers, int N, int M, int depth, StringBuilder sb) {
        if (depth == M) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    return;
                }
            }

            for (int val : numbers) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            numbers[depth] = (i + 1);
            dfs(numbers, N, M, depth + 1, sb);
        }
    }
}
