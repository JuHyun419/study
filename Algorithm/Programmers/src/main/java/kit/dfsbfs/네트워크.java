package kit.dfsbfs;

// TODO:
public class 네트워크 {
    private static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[computers.length];

        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                answer ++;
                dfs(i, computers);
            }
        }
        return answer;
    }

    private void dfs(int i, int[][] computers) {
        if (visited[i]) {
            return;
        }

        visited[i] = true;
        for (int j = 0; j < computers.length; j++) {
            if (i != j && !visited[j] && computers[i][j] == 1) {
                dfs(j, computers);
            }
        }
    }
}
