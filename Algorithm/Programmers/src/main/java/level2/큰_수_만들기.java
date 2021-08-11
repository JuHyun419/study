package level2;

// TODO: 8월 중순 이후에 다시 풀어볼 것
public class 큰_수_만들기 {
    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);

        for (int i = 0; i < sb.length() - 1 && k > 0; i++) {
            if (sb.charAt(i) < sb.charAt(i + 1)) {
                sb.deleteCharAt(i);
                i--;
                k--;
            }
        }
        if (k != 0) {
            sb.delete(sb.length() - k, sb.length());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String number = "1924";
        System.out.println(solution(number, 3));
    }
}
