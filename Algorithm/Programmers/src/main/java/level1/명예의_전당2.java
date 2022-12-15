package level1;

import java.util.PriorityQueue;
import java.util.Queue;

public class 명예의_전당2 {

    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];

        Queue<Integer> honors = new PriorityQueue<>();

        for (int i = 0; i < score.length; i++) {
            honors.add(score[i]);
            if (honors.size() > k) {
                honors.poll();
            }
            answer[i] = honors.peek();
        }

        return answer;
    }

}
