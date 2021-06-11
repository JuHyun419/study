package easy1;

public class Count_1688 {

    public static int numberOfMatches(int n) {
        int total = 0;

        while (n != 1) {
            total += n / 2;

            n = (n % 2 == 0)
                    ? n / 2
                    : (n + 1) / 2;
        }
        return total;
    }

    public static void main(String[] args) {

    }
}
