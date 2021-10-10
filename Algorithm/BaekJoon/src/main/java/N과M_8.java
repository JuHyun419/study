import java.util.Arrays;
import java.util.Scanner;

public class N과M_8 {

    private static int N;
    private static int M;
    private static int[] numbers;
    private static int[] array;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        numbers = new int[M];
        array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }
        Arrays.sort(array);

        dfs(0);
        System.out.println(sb.toString());

        scanner.close();
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    return;
                }
            }
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            numbers[depth] = array[i];
            dfs(depth + 1);
        }
    }
}
