import java.util.Scanner;

public class N과M_1 {

    private static int[] array;
    private static boolean[] visited;
    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        array = new int[M];
        visited = new boolean[N];

        dfs(0);
        System.out.println(sb.toString());
        scanner.close();
    }

    private static void dfs(int depth) {
        // M개 뽑았을때 => 출력
        if (depth == M) {
            for (int val : array) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                array[depth] = i + 1;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

}
