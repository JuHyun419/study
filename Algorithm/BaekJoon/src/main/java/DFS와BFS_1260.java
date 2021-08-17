import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS와BFS_1260 {
    static int[][] map;
    static boolean[] visit;
    static int N, M, V;

    public static void dfs(int i) {
        if (i == map.length) return;

        visit[i] = true;
        System.out.print(i + " ");

        for (int j = 1; j <= N; j++) {
            if (map[i][j] == 1 && !visit[j]) {
                dfs(j);
            }
        }
    }

    public static void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visit[i] = true;

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            System.out.print(temp + " ");

            for (int j = 1; j <= N; j++) {
                if (map[temp][j] == 1 && !visit[j]) {
                    queue.offer(j);
                    visit[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt(); // 정점
        M = scan.nextInt(); // 간선
        V = scan.nextInt(); // 탐색 시작할 정점 번호
        map = new int[N + 1][N + 1];
        visit = new boolean[N + 1];

        for (int i = 1; i <= M; i++) { // 정점 연결 상태 1로 표시
            int a = scan.nextInt();
            int b = scan.nextInt();
            map[a][b] = map[b][a] = 1;
        }

        //dfs(V);
        Arrays.fill(visit, false);
        System.out.println();
        bfs(V);
    }
}
