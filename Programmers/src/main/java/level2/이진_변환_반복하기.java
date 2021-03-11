package level2;

/**
 * 1. 문자열 s에서 0의 갯수 카운트
 * 2. 변환 횟수 ++
 * 3. s의 모든 0 제거
 * 4. 제거 후 s의 길이를 2진법 문자열로 변환
 */
public class 이진_변환_반복하기 {

    public static int[] solution(String s) {
        int count = 0;
        int removeCount = 0;

        while (!isOne(s)) {
            removeCount += getZeroCount(s);
            count++;
            s = deleteZero(s);
            s = toDecimal(s.length());
        }
        return new int[]{count, removeCount};
    }

    private static boolean isOne(String str) {
        return "1".equals(str);
    }

    private static int getZeroCount(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result++;
            }
        }
        return result;
    }

    private static String deleteZero(String s) {
        return s.replace("0", "");
    }

    private static String toDecimal(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 2);
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String str = "01110";
        int[] arr = solution(str);

        System.out.println(arr[0] + " " + arr[1]);
    }
}
