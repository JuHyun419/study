package easy4;

public class Repeated_Substring_Pattern_459 {
    public static boolean repeatedSubstringPattern(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length() / 2; i++) {
            String str = sb.substring(0, i + 1);
            StringBuilder compare = new StringBuilder(str);
            for (int j = 0; j < sb.length() / str.length() - 1; j++) {
                compare.append(str);
            }
            if (s.equals(compare.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean repeatedSubstringPattern2(String s) {
        String str = s + s;
        String temp = str.substring(1, str.length() - 1);
        return temp.contains(s);
    }

    public static void main(String[] args) {
        String s1 = "abab";
        String s2 = "aba";
        String s3 = "abcabcabcabc";

        System.out.println(repeatedSubstringPattern2(s1));
        System.out.println(repeatedSubstringPattern2(s2));
        System.out.println(repeatedSubstringPattern2(s3));
    }
}
