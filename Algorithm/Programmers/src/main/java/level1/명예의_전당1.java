package level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 명예의_전당1 {

    public static int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int index = 0;
        List<Integer> honors = new ArrayList<>();

        // k번째 이내이면 무조건 명예의 전당에 목록 올리기
        for (int i = 0; i < k; i++) {
            if (i >= score.length) break;

            honors.add(score[i]);
            Collections.sort(honors);
            answer[index++] = honors.get(0);
        }

        // k ~ 마지막 까지 점수 계산
        for (int i = k; i < score.length; i++) {
            if (score[i] > honors.get(0)) {
                honors.remove(0);
                honors.add(score[i]);
                Collections.sort(honors);
            }
            answer[index++] = honors.get(0);
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] score = {10, 100, 20, 150, 1, 100, 200};

        System.out.println(Arrays.toString(solution(k, score)));
    }

}
