package recursion;

public class Maze {
    private static int N = 8;
    private static int[][] maze = {
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 0, 1, 0, 0}
    };

    private static final int PATHWAY_COLOR = 0;   // 통로
    private static final int WALL_COLOR = 1;      // 벽
    private static final int BLOCKED_COLOR = 2;   // 이미 방문한 벽(막혀있음)
    private static final int PATH_COLOR = 3;      // 방문한 곳이나 막혀있는지 여부는 모름

    public static void main(String[] args) {
        printMaze();
        findMazePath(0, 0);
        printMaze();
    }

    public static boolean findMazePath(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }
        if (maze[x][y] != PATHWAY_COLOR) {
            return false;
        }
        if (x == N - 1 && y == N - 1) {
            maze[x][y] = PATH_COLOR;
            return true;
        }

        maze[x][y] = PATH_COLOR;
        if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
            return true;
        }

        maze[x][y] = BLOCKED_COLOR;
        return false;
    }

    public static void printMaze() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
}
