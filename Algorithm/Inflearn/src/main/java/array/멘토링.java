package array;

import java.util.Scanner;

public class 멘토링 {

    /**
     *
     * @param n - 학생 수
     * @param m - m번의 수학성적(테스트 횟수)
     * @param arr
     * @return
     */
    public int solution(int n, int m, int[][] arr) {
        int result = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                int answer = 0; // 멘토가 멘티보다 성적이 높은 횟수(등수는 낮음)
                for (int k = 0; k < m; k++) {
                    int pi = 0, pj = 0;

                    for (int s = 0; s < n; s++) {
                        if (arr[k][s] == i) pi = s; // 멘토 성적
                        if (arr[k][s] == j) pj = s; // 멘티 성적
                    }
                    if (pi < pj) answer++; // 값이 낮은게 등수가 높음
                }
                if (answer == m) { // 모든 테스트에서 멘토의 성적이 멘티의 성적보다 높을경우
                    result++; // 멘토, 멘티 짝 +1
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        멘토링 T = new 멘토링();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.print(T.solution(n, m, arr));
    }

}
