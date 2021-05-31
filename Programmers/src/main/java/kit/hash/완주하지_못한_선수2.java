package kit.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 완주하지_못한_선수2 {
    public String solution(final String[] participant, final String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for (int i = 0; i < completion.length; i++) {
            if (!completion[i].equals(participant[i])) {
                return participant[i];
            }
        }
        return participant[participant.length - 1];
    }

    public String solution2(final String[] participant, final String[] completion) {
        final Map<String, Integer> map = new HashMap<>();
        String answer = "";

        for (int i = 0; i < participant.length; i++) {
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
        }

        for (int i = 0; i < completion.length; i++) {
            map.put(completion[i], map.getOrDefault(completion[i], 0) + 1);
        }

        for (final String s : map.keySet()) {
            if (map.get(s) % 2 == 1) {
                answer = s;
            }
        }
        return answer;
    }
}
