package kit.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 모의고사 {
    private static final int[] ONE   = {1, 2, 3, 4, 5};
    private static final int[] TWO   = {2, 1, 2, 3, 2, 4, 2, 5};
    private static final int[] THREE = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    private static final int PEOPLE_NUMBERS = 3;

    public static int[] solution(final int[] answers) {
        final List<Integer> list = new ArrayList<>();
        final int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == ONE[i % 5])   scores[0] ++;
            if (answers[i] == TWO[i % 8])   scores[1] ++;
            if (answers[i] == THREE[i % 10]) scores[2] ++;
        }

        final int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));

        for (int i = 0; i < PEOPLE_NUMBERS; i++) {
            if (maxScore == scores[i]) {
                list.add((i + 1));
            }
        }

        Collections.sort(list);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(final String[] args) {
        final int[] answer = {1, 3, 2, 4, 2};
        System.out.println(Arrays.toString(solution(answer)));
    }
}
