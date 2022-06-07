package array;

import java.util.Scanner;

public class 임시반장_정하기 {

    private int solution(int[][] arr) {

        for (int i = 1; i <= arr.length; i++) {
            int count = 0;
            for (int j = 1; j <= arr[i].length; j++) {
                for (int k = 1; k <= arr.length; k++) {
                    if (j == k) continue; // 자기 자신
                    if (arr[i][j] == arr[k][j]) {

                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        임시반장_정하기 T = new 임시반장_정하기();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n + 1][6]; // n학생, 5학년
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 5; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.print(T.solution(arr));
    }


}
