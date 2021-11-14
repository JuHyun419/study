package medium1;

import java.util.*;

public class Letter_Case_Permutation_784 {

    static List<String> result = new ArrayList<>();

    public static List<String> letterCasePermutation(String s) {
        if (s.length() == 0) return result;
        char[] charArray = s.toCharArray();
        dfs(charArray, 0);
        return result;
    }

    private static void dfs(char[] charArray, int start) {
        result.add(new String(charArray));

        for (int i = start; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i])) continue;
            final char temp = charArray[i];
            charArray[i] = Character.isUpperCase(charArray[i])
                    ? Character.toLowerCase(charArray[i])
                    : Character.toUpperCase(charArray[i]);

            dfs(charArray, i + 1);
            charArray[i] = temp;
        }
    }

    public static void main(String[] args) {
        final String s = "a1b2";
        List<String> result = letterCasePermutation(s);
        result.forEach(System.out::println);

    }
}