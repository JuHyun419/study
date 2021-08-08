package string;

import java.util.ArrayList;
import java.util.List;

public class Reverse_Vowels_of_a_String_345 {
    public static String reverseVowels(String s) {
        List<Integer> vowels = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isVowels(s.charAt(i))) {
                vowels.add(i);
            }
        }

        int reverseIndex = vowels.size() - 1;
        for (int i = 0; i < vowels.size() / 2; i++) {
            int index1 = vowels.get(i);
            int index2 = vowels.get(reverseIndex);
            s = changeIndexChar(s, index1, index2);
            reverseIndex -= 1;
        }
        return s;
    }

    private static boolean isVowels(final char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    /**
     * str = "hello", index1 = 1, index2 = 4 일때
     * @return "holle"
     */
    private static String changeIndexChar(String str, int index1, int index2) {
        char ch1 = str.charAt(index1);
        char ch2 = str.charAt(index2);
        str = str.substring(0, index1) + ch2 + str.substring(index1 + 1);
        str = str.substring(0, index2) + ch1 + str.substring(index2 + 1);
        return str;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(reverseVowels(s));
    }

}
