package level0;

public class 문자열_밀기 {

    public static int solution(String A, String B) {
        if (A.equals(B)) return 0;

        int answer = 0;
        int length = A.length();

        for (int i = 0; i < A.length(); i++) {
            answer++;
            String newString = A.charAt(length - 1) + A.substring(0, length - 1);
            if (B.equals(newString)) {
                return answer;
            }
            A = newString;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution("hello", "ohell"));
    }

}
