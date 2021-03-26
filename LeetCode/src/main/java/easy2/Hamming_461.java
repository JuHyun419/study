package easy2;

public class Hamming_461 {

    // Using XOR Operation
//    public static int hammingDistance(int x, int y) {
//        String binary = Integer.toBinaryString(x ^ y);
//
//        int count = 0;
//        for (int i = 0; i < binary.length(); i++) {
//            if (binary.charAt(i) == '1') count++;
//        }
//
//        return count;
//    }

    public static int hammingDistance(int x, int y) {
        int result = 0;
        final String str1 = zeroFill(toInt(getBinaryNumber(x)));
        final String str2 = zeroFill(toInt(getBinaryNumber(y)));

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                result++;
            }
        }

        return result;
    }

    private static String getBinaryNumber(int num) {
        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            sb.append(num % 2);
            num /= 2;
        }

        return sb.reverse().toString();
    }

    private static int toInt(String str) {
        return Integer.parseInt(str);
    }

    private static String zeroFill(int num) {
        return String.format("%031d", num);
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;

        System.out.println(hammingDistance(x, y));
    }

}
