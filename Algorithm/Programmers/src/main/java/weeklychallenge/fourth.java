package weeklychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fourth {

    static class Jobs implements Comparable<Jobs> {
        String jobGroup;
        int score;

        public Jobs(String jobGroup, int score) {
            this.jobGroup = jobGroup;
            this.score = score;
        }

        @Override
        public int compareTo(Jobs o) {
            if (this.score == o.score) {
                return this.jobGroup.compareTo(o.jobGroup);
            }
            return Integer.compare(o.score, this.score);
        }
    }

    public String solution(String[] table, String[] languages, int[] preference) {
        Map<String, Integer> scores = new HashMap<>();
        List<Jobs> list = new ArrayList<>();

        for (int i = 0; i < languages.length; i++) {
            scores.put(languages[i], preference[i]);
        }

        for (String s : table) {
            String[] tables = s.split(" ");
            int allScore = 0;
            int score = 5;

            /* 직업군별 점수 총합 구하기 */
            for (int j = 1; j < tables.length; j++) {
                if (scores.containsKey(tables[j])) {
                    allScore += score * scores.get(tables[j]);
                }
                score--;
            }

            list.add(new Jobs(tables[0], allScore));
        }

        Collections.sort(list);
        return list.get(0).jobGroup;
    }
}
