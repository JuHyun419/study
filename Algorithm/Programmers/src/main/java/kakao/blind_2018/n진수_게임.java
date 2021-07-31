package kakao.blind_2018;

public class n진수_게임 {

    private static final char[] WORD = {'A', 'B', 'C', 'D', 'E', 'F'};

    public static String solution(int n, int t, int m, int p) {
        StringBuilder numbers = new StringBuilder("01");
        StringBuilder sb = new StringBuilder();
        int number = 2;
        final int length = t * m + p;

        // 튜브가 말해야 하는 숫자 저장
        while (numbers.length() <= length) {
            numbers.append(toDecimal(number++, n));
        }

        for (int i = 0; i < t; i++) {
            sb.append(numbers.charAt(p - 1)); // index는 0부터 시작하므로
            p += m;
        }
        return sb.toString();
    }

    /**
     * @param number: 10진수
     * @param n: 진수
     * @return - 10진수의 number을 n진수로 변환
     */
    private static String toDecimal(int number, int n) {
        String answer = "";
        while (number > 0) {
            int rest = number % n;
            answer = (rest >= 10) // 10 ~ 15는 대문자 A ~ F로 출력
                    ? WORD[rest - 10] + answer
                    : rest + answer;
            number /= n;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(16, 16, 2, 2));
    }
}
