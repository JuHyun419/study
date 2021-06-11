package easy3;

public class Reverse_Only_Letters_917 {

    public static String reverseOnlyLetters(final String str) {
        final StringBuilder sb = new StringBuilder();

        /* 1. 문자열 역순으로 문자만 넣기 */
        for (int i = str.length() - 1; i >= 0; i--) {
            if (isCharacter(str.charAt(i))) {
                sb.append(str.charAt(i));
            }
        }

        /* 2. 문자열 처음부터 문자가 아닌 기호 넣기 */
        for (int i = 0; i < str.length(); i++) {
            if (!isCharacter(str.charAt(i))) {
                sb.insert(i, str.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean isCharacter(final char ch) {
        return Character.isLetter(ch);
        // return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    public static void main(final String[] args) {
        final String str = "ab-cd";
        System.out.println(reverseOnlyLetters(str));

        final String str2 = "a-bC-dEf-ghIj";
        System.out.println(reverseOnlyLetters(str2));
    }
}