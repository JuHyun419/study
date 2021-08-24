package string;

public class Sum_of_Digits_of_String_After_Convert_1945 {

    static String convert;

    public static int getLucky(String s, int k) {
        convert = converting(s);
        int result = 0;
        while (k > 0) {
            result = getSumDigits();
            k--;
        }
        return result;
    }

    private static String converting(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append((s.charAt(i) - 'a') + 1);
        }
        return sb.toString();
    }

    private static int getSumDigits() {
        int sum = 0;
        for (int i = 0; i < convert.length(); i++) {
            sum += Integer.parseInt(String.valueOf(convert.charAt(i)));
        }
        convert = String.valueOf(sum);
        return sum;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(getLucky(s, 2));
    }
}
