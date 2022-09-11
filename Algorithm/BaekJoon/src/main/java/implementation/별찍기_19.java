package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class 별찍기_19 {

    static char[][] star;


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        N = (N * 4) - 3;
        star = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                star[i][j] = ' ';
            }
        }

        drawStar(N, 0);

        printStart();

        scanner.close();
    }

    private static void drawStar(int line, int s) {
        if (line <= s) return;

        for (int i = s; i < line; i++) {
            star[s][i] = '*'; // 맨 위 가로
            star[i][s] = '*'; // 왼쪽 세로
            star[i][line - 1] = '*'; // 오른쪽 세로
            star[line - 1][i] = '*';// 맨 아래 가로

            drawStar(line - 2, s + 2);
        }
    }

    private static void printStart() {
        for (int i = 0; i < star.length; i++) {
            for (int j = 0; j < star.length; j++) {
                System.out.print(star[i][j]);
            }
            System.out.println();
        }
    }

}
