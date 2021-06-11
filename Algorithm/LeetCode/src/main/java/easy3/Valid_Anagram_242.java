package easy3;

import java.util.Arrays;

public class Valid_Anagram_242 {
    public static boolean isAnagram(final String s, final String t) {
        final int[] alphabet1 = new int[26];
        final int[] alphabet2 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabet1[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            alphabet2[t.charAt(i) - 'a']++;
        }

        for (int i = 0; i < alphabet1.length; i++) {
            if (isNotAnagram(alphabet1[i], alphabet2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram2(final String s, final String t) {
        if (s.length() != t.length()) return false;

        final char[] str1 = s.toCharArray();
        final char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);
    }

    private static boolean isNotAnagram(final int i1, final int i2) {
        return i1 != i2;
    }

    public static void main(final String[] args) {
        final String s = "anagram";
        final String t = "nagaram";

        System.out.println(isAnagram(s, t));
    }

}
