import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class N과M_11 {

    private static int N;
    private static int M;
    private static int[] numbers;
    private static int[] array;
    private static Set<String> ans = new LinkedHashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        array = new int[N];
        numbers = new int[M];

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
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }
            //sb.append("\n");
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            numbers[depth] = array[i];
            dfs(depth + 1);
        }
    }

}
