package kit.hash;

import java.util.Arrays;

public class 완주하지_못한_선수 {

    public static String solution(final String[] participant, final String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        String answer = "";

        for (int i = 0; i < completion.length; i++) {
            if (!completion[i].equals(participant[i])) {
                answer = participant[i];
                break;
            }
        }
        return answer.isEmpty()
                ? participant[participant.length - 1]
                : answer;
    }

    public static void main(final String[] args) {
        final String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        final String[] completion = {"marina", "josipa", "nikola", "filipa"};
        System.out.println(solution(participant, completion));
    }

}
