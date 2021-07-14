package d2;

import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq
public class 숫자_배열_회전_1961 {
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        int T = scan.nextInt(); // 테스트 케이스의 개수
        int answer = 1;

        for (int i = 0; i < T; i++) {
            int N = scan.nextInt(); // N * N 행렬
            int[][] matrix = getMatrix(N);
            int[][] rotate90 = rotate(matrix, N);
            int[][] rotate180 = rotate(rotate90, N);
            int[][] rotate270 = rotate(rotate180, N);

            System.out.println("#" + answer);
            printRotateMatrix(rotate90, rotate180, rotate270, N);
            answer++;
        }
        scan.close();
    }

    private static int[][] getMatrix(int n) {
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int number = scan.nextInt();
                array[i][j] = number;
            }
        }
        return array;
    }

    private static int[][] rotate(int[][] matrix, int n) {
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = matrix[n - j - 1][i];
            }
        }
        return array;
    }

    private static void printRotateMatrix(int[][] rotate90, int[][] rotate180, int[][] rotate270, int N) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                System.out.print(rotate90[j][k]);
            }
            System.out.print(" ");

            for (int k = 0; k < N; k++) {
                System.out.print(rotate180[j][k]);
            }
            System.out.print(" ");

            for (int k = 0; k < N; k++) {
                System.out.print(rotate270[j][k]);
            }
            System.out.println();
        }
    }

}
