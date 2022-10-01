package kakao.blind_2022;

import java.util.HashMap;
import java.util.Map;

public class 성격_유형_검사하기 {

    // choices 별 점수
    private static final Map<Integer, Integer> scores = Map.of(
            1, 3,
            2, 2,
            3, 1,
            5, 1,
            6, 2,
            7, 3
    );

    // 성격 별 점수
    private static Map<String, Integer> personalityScore = new HashMap<>();

    public static String solution(String[] survey, int[] choices) {
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == 4) continue;

            String personality = getPersonality(choices, i, survey);
            int score = scores.get(choices[i]);
            personalityScore.put(personality, personalityScore.getOrDefault(personality, 0) + score);
        }

        return getFirstType() + getSecondType() + getThirdType() + getFourthType();
    }

    /*
        비동의 선택지(choices가 4보다 작을 경우) -> survey[i]의 첫 번째 캐릭터
        동의 선택지 -> survey[i]의 두 번째 캐릭터
     */
    private static String getPersonality(int[] choices, int index, String[] survey) {
        return choices[index] < 4
                ? survey[index].substring(0, 1)
                : survey[index].substring(1);
    }

    /* 1번 지표 성격유형 구하기 */
    private static String getFirstType() {
        final int ryan = personalityScore.getOrDefault("R", 0);
        final int tube = personalityScore.getOrDefault("T", 0);

        return ryan >= tube ? "R" : "T";
    }

    /* 2번 지표 성격유형 구하기 */
    private static String getSecondType() {
        final int con = personalityScore.getOrDefault("C", 0);
        final int prodo = personalityScore.getOrDefault("F", 0);

        return con >= prodo ? "C" : "F";
    }

    /* 3번 지표 성격유형 구하기 */
    private static String getThirdType() {
        final int jayg = personalityScore.getOrDefault("J", 0);
        final int mooji = personalityScore.getOrDefault("M", 0);

        return jayg >= mooji ? "J" : "M";
    }

    /* 4번 지표 성격유형 구하기 */
    private static String getFourthType() {
        final int apeach = personalityScore.getOrDefault("A", 0);
        final int neo = personalityScore.getOrDefault("N", 0);

        return apeach >= neo ? "A" : "N";
    }

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 4, 4, 5};
        System.out.println(solution(survey, choices));
    }

}