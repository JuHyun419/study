package weeklychallenge;

public class Second {
    public static String solution(int[][] scores) {
        int[] counts = new int[scores.length]; // 평균 구할 때 인원 제외할 배열
        fillZeroMaxOrMinScore(scores, counts);
        int[] avg = getStudentAvg(scores, counts);
        return getStudentGrade(avg);
    }

    /* 자기 자신을 평가한 점수가 유일한 최고점 또는 유일한 최저점일 경우 0점으로 설정(평균 제외) */
    private static void fillZeroMaxOrMinScore(final int[][] scores, final int[] counts) {
        for (int i = 0; i < scores.length; i++) {
            int score = scores[i][i];
            int max = score;
            int min = score;
            int count = 1;
            for (int j = 0; j < scores.length; j++) {
                if (i != j && score == scores[j][i]) {
                    count++;
                    break;
                }
                max = Math.max(max, scores[j][i]);
                min = Math.min(min, scores[j][i]);
            }

            // 동일한 점수가 존재하면 평균에 포함
            if (count >= 2) {
                continue;
            }
            // 자기 자신을 평가한 점수가 유일한 최고점 또는 유일한 최저점이면 평균 제외
            if (score == max || score == min) {
                scores[i][i] = 0;
                counts[i] = -1;
            }
        }
    }

    /* 학생 별 평균 구하기 */
    private static int[] getStudentAvg(final int[][] scores, final int[] counts) {
        int[] avg = new int[scores.length];

        for (int i = 0; i < scores.length; i++) {
            int sum = 0;
            for (int j = 0; j < scores.length; j++) {
                sum += scores[j][i];
            }
            avg[i] = sum / (scores.length + counts[i]);
        }
        return avg;
    }

    /* 학생 별 학점 구하기 */
    private static String getStudentGrade(final int[] avg) {
        StringBuilder sb = new StringBuilder();
        for (int a : avg) {
            sb.append(a >= 90 ? "A" : a >= 80 ? "B" : a >= 70 ? "C" : a >= 50 ? "D" : "F");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] scores = {{50, 90}, {50, 87}};
        System.out.println(solution(scores));
    }
}
