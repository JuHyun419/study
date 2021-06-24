package devmatching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 로또의_최고_순위와_최저_순위 {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int lottoCount = 0;
        int zeroCount = 0;
        Map<Integer, Integer> ranks = new HashMap<>();

        // key: 당첨 내용, value: 순위
        ranks.put(6, 1);
        ranks.put(5, 2);
        ranks.put(4, 3);
        ranks.put(3, 4);
        ranks.put(2, 5);
        ranks.put(1, 6);
        ranks.put(0, 6);

        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
                continue;
            }

            for (int win_num : win_nums) {
                if (lotto == win_num) {
                    lottoCount++;
                }
            }
        }
        int highRank = lottoCount + zeroCount;
        answer[0] = ranks.get(highRank);
        answer[1] = ranks.get(lottoCount);

        // wow
        answer[0] = Math.min(7 - highRank, 6);
        answer[1] = Math.min(7 - lottoCount, 6);

        return answer;
    }

    public static void main(String[] args) {
        int[] lottos = {0, 0, 0, 0, 0, 0};
        int[] win = {38, 19, 20, 40, 15, 25};

        System.out.println(Arrays.toString(solution(lottos, win)));
    }
}
