package level1;

public class 다트_게임 {

    public static int solution(String dartResult) {
        int[] scores = new int[3];
        int index = -1;

        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if (ch >= '0' && ch <= '9') {
                index++;
                if (ch == '1' && dartResult.charAt(i + 1) == '0') {
                    scores[index] = 10;
                    i++;
                } else {
                    scores[index] = ch - '0';
                }
            } else if (ch == 'D') {
                scores[index] = scores[index] * scores[index];
            } else if (ch == 'T') {
                scores[index] = scores[index] * scores[index] * scores[index];
            } else if (ch == '*') {
                if (i != 2) { // 첫 번째 스타상이 아닐 때 -> 앞에 점수 * 2
                    scores[index - 1] *= 2;
                }
                scores[index] *= 2;
            } else if (ch == '#') {
                scores[index] *= -1;
            }
        }
        return scores[0] + scores[1] + scores[2];
    }

    public static void main(String[] args) {
        String str = "1D2S3T*";
        System.out.println(solution(str));
    }
}
