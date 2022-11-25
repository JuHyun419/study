package level0;

import java.util.Arrays;

public class OX퀴즈 {

    public static String[] solution(String[] quiz) {
        int length = quiz.length;
        String[] answer = new String[length];

        for (int i = 0; i < length; i++) {
            String[] array1 = quiz[i].split("=");

            // 우측 결과 값 공백 제거 후 int로 변환
            int result = Integer.parseInt(array1[1].trim());

            // '=' 기준 (연산이 존재하는) 좌측 항
            String[] left = array1[0].split(" ");

            System.out.println(Arrays.toString(left));

            int compare = ("+".equals(left[1]))
                    ? Integer.parseInt(left[0].trim()) + Integer.parseInt(left[2].trim())
                    : Integer.parseInt(left[0].trim()) - Integer.parseInt(left[2].trim());

            if (result == compare) {
                answer[i] = "O";
            } else {
                answer[i] = "X";
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] quiz = {"3 - 4 = -3", "5 + 6 = 11"};

        System.out.println(Arrays.toString(solution(quiz)));
    }

}
