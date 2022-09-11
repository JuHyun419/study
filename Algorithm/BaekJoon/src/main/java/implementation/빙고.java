package implementation;

import java.util.Scanner;

public class 빙고 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] bingo = createBingo(scanner);
        int count = 0;

        // 사회자가 부르는 수
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int number = scanner.nextInt();
                count++;

                removeNumber(bingo, number);

                if (checkBingoCount(bingo) >= 3) {
                    System.out.println(count);
                    return;
                }
            }
        }
        scanner.close();
    }

    // 빙고판 생성
    private static int[][] createBingo(Scanner scanner) {
        int[][] bingo = new int[5][5];

        for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo.length; j++) {
                bingo[i][j] = scanner.nextInt();
            }
        }

        return bingo;
    }

    // 사회자가 부른 수 지우기
    private static void removeNumber(int[][] bingo, int number) {
        boolean flag = true;
        for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo.length; j++) {
                if (bingo[i][j] == number) {
                    bingo[i][j] = 0;
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }
    }

    // 빙고 횟수
    private static int checkBingoCount(int[][] bingo) {
        int count = 0;

        // 가로 빙고 체크
        for (int i = 0; i < bingo.length; i++) {
            boolean isBingo = true;
            for (int j = 0; j < bingo.length - 1; j++) {
                if (bingo[i][j] != bingo[i][j + 1]) {
                    isBingo = false;
                    break;
                }
            }
            if (isBingo) count++;
        }

        // 세로 빙고 체크
        for (int i = 0; i < bingo.length; i++) {
            if (bingo[0][i] == bingo[1][i] && bingo[1][i] == bingo[2][i] && bingo[2][i] == bingo[3][i] && bingo[3][i] == bingo[4][i]) {
                count++;
            }
        }

        // 대각선(︎↘︎) 빙고 체크
        if (bingo[0][0] == bingo[1][1] && bingo[1][1] == bingo[2][2] && bingo[2][2] == bingo[3][3] && bingo[3][3] == bingo[4][4]) {
            count++;
        }

        // 대각선(⬋) 빙고 체크
        if (bingo[0][4] == bingo[1][3] && bingo[1][3] == bingo[2][2] && bingo[2][2] == bingo[3][1] && bingo[3][1] == bingo[4][0]) {
            count++;
        }

        return count;
    }

}
