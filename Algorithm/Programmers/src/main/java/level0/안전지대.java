package level0;

public class 안전지대 {

    public static int solution(int[][] board) {
        int length = board.length;

        // 상, 하, 좌, 우, 대각선 범위
        int[][] position = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 1) { // 지뢰
                    // 위, 아래, 좌, 우, 대각선 모두 위험지역 분류
                    for (int k = 0; k < position.length; k++) {
                        int nx = i + position[k][0];
                        int ny = j + position[k][1];
                        System.out.println(nx + " " + ny);
                        if (nx >= 0 && ny >= 0 && nx < length && ny < length && board[nx][ny] != 1) {
                            board[nx][ny] = 2; // 범위에 만족할 경우 위험지역 표시
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        System.out.println(solution(board));
    }

}
