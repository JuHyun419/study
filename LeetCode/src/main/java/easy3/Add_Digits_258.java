package easy3;

public class Add_Digits_258 {

    public static int addDigits(int num) {
        while (!isOneDigit(num)) {
            num = addAllDigits(num);
        }
        return num;
    }

    private static boolean isOneDigit(final int num) {
        return String.valueOf(num).length() == 1;
    }

    private static int addAllDigits(int num) {
        int result = 0;
        while (num != 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(addDigits(0));
    }

}
