package easy3;

public class Consecutive_Characters_1446 {

    public static int maxPower(String s) {
        int consecutiveCount = 1;
        int max = 1;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                consecutiveCount++;
            } else {
                max = Math.max(max, consecutiveCount);
                consecutiveCount = 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String str = "cc";
        System.out.println(maxPower(str));
    }
}
