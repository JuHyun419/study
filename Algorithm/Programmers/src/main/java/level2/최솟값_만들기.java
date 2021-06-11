package level2;

import java.util.Arrays;

/**
 * A, B 배열에서 (작은값 * 큰값) 의 계산을 하는게 최솟값이 됨
 */
public class 최솟값_만들기 {

    public static int solution(int A[], int B[]) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[A.length - i - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = {1, 4, 2};
        int[] B = {5, 4, 4};

        System.out.println(solution(A, B));
    }

}
