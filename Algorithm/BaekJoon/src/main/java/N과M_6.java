import java.util.Arrays;
import java.util.Scanner;

public class N과M_6 {

    private static int N;
    private static int M;
    private static int[] numbers;
    private static int[] array;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        numbers = new int[M];
        array = new int[N];
        visited = new boolean[N];

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
            if (isNotAscArray()) {
                return;
            }

            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers[depth] = array[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean isNotAscArray() {
        for (int i = 0; i < M - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
