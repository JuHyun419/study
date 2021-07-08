package level3;

import java.util.Arrays;

public class 숫자_게임 {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int indexA = 0;
        int indexB = 0;

        // 1 3 5 7
        // 2 2 6 8
        for (int i = 0; i < A.length; i++) {
            if (B[indexB] > A[indexA]) {
                indexA++;
                answer++;
            }
            indexB++;
        }
        return answer;
    }

}
