package string;

public class 문자열_압축2 {

    public static String solution(String s) {
        String answer = "";
        s += " ";
        int count = 1;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                answer += s.charAt(i);
                if (count > 1) answer += String.valueOf(count);
                count = 1;
            }
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                continue;
            }

            answer += s.charAt(i);
            if (count > 1) answer += String.valueOf(count);
            count = 1;
        }

        return answer;
    }

}
