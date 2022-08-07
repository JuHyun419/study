package stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class 크레인_인형뽑기 {

    public static void main(String[] args) {
        크레인_인형뽑기 T = new 크레인_인형뽑기();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] board = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        int m = kb.nextInt();
        int[] moves = new int[m];
        for (int i = 0; i < m; i++) moves[i] = kb.nextInt();
        System.out.println(T.solution(board, moves));
    }

    private int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for (int move : moves) {
            for (int j = 0; j < board.length; j++) {
                int doll = board[j][move - 1];
                if (doll == 0) continue;

                if (stack.isEmpty()) {
                    stack.push(doll);
                } else {
                    if (stack.peek() == doll) {
                        stack.pop();
                        result += 2;
                    } else {
                        stack.push(doll);
                    }
                }

                // 인형 제거
                board[j][move - 1] = 0;
                break;
            }
        }
        return result;
    }

}
