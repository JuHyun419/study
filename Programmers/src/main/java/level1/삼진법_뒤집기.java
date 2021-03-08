package level1;

public class 삼진법_뒤집기 {

    public static int solution(int n) {
        return toTenDecimal(toLong(getThreeDecimal(n)));
    }

    private static String getThreeDecimal(long num) {
        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            sb.append(num % 3);
            num /= 3;
        }
        return sb.toString();
    }

    private static int toTenDecimal(long num) {
        int mul = 1;
        int sum = 0;
        while (num != 0) {
            sum += (num % 10) * mul;
            mul *= 3;
            num /= 10;
        }
        return sum;
    }

    // int로 하면 int 범위를 벗어나서 런타임 오류 발생
    private static long toLong(String str) {
        return Long.parseLong(str);
    }

    public static void main(String[] args) {

        for (int i = 100000; i <= 500000; i++) {
            System.out.println(solution(i));
        }

    }
}
