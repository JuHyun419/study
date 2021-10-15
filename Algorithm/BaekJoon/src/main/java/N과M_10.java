import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class N과M_10 {

    private static int N;
    private static int M;
    private static int[] numbers;
    private static int[] array;
    private static boolean[] visited;
    private static Set<String> ans = new LinkedHashSet<>();

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
        ans.forEach(System.out::println);

        scanner.close();
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int number : numbers) {
                sb.append(number).append(" ");
            }
            ans.add(sb.toString());
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

}
