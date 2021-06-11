package easy2;

public class Calculate_Money_in_Leetcode_Bank_1716 {

    public static int totalMoney(int n) {
        int moneyMoney = 1;
        int increaseMoney = moneyMoney;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (isNextMonday(i)) {
                moneyMoney += 1;
                increaseMoney = moneyMoney;
            }

            sum += increaseMoney;
            increaseMoney += 1;

        }
        return sum;
    }

    private static boolean isNextMonday(final int day) {
        return day != 1 && day % 7 == 1;
    }

    public static void main(String[] args) {
        System.out.println(totalMoney(10));
    }

}
