package easy4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Relative_Ranks_506 {
    private static final String[] MEDAL = {"Gold Medal", "Silver Medal", "Bronze Medal"};

    public static String[] findRelativeRanks(int[] score) {
        String[] result = new String[score.length];
        int[] temp = Arrays.copyOf(score, score.length);
        Map<Integer, Integer> ranks = new HashMap<>(); // key: score, value: rank
        Arrays.sort(temp);
        int rank = 1;

        for (int i = temp.length - 1; i >= 0; i--) {
            ranks.put(temp[i], rank ++);
        }

        for (int i = 0; i < score.length; i++) {
            int athletesRank = ranks.get(score[i]);
            if (athletesRank == 1) {
                result[i] = MEDAL[0];
            } else if (athletesRank == 2) {
                result[i] = MEDAL[1];
            } else if (athletesRank == 3) {
                result[i] = MEDAL[2];
            } else {
                result[i] = String.valueOf(athletesRank);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] score = {10, 3, 8, 9, 4};
        String[] result = findRelativeRanks(score);
        System.out.println(Arrays.toString(result));
    }

}
