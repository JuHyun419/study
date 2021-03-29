package easy2;

public class Reverse_557 {

    public static String reverseWords(String s) {
        String[] splitArr = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String str : splitArr) {
            String reverse = reverseEachWords(str);
            sb.append(reverse).append(" ");
        }
        return sb.toString().trim();
    }

    private static String reverseEachWords(final String str) {
        char[] chArr = str.toCharArray();

        for (int j = 0; j < chArr.length / 2; j++) {
            char temp = chArr[j];
            chArr[j] = chArr[chArr.length - j - 1];
            chArr[chArr.length - j - 1] = temp;
        }

        return toStr(chArr);
    }

    private static String toStr(final char[] c) {
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }

}
