package string;

public class Reverse_Vowels_of_a_String2_345 {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int index = 0;
        int reverseIndex = s.length() - 1;

        // Two Pointer Approach
        while (index < reverseIndex) {
            if (isVowels(chars[index]) && isVowels(chars[reverseIndex])) {
                char temp = chars[index];
                chars[index] = chars[reverseIndex];
                chars[reverseIndex] = temp;
                index++;
                reverseIndex--;
                continue;
            }
            if (!isVowels(chars[index])) {
                index++;
            }
            if (!isVowels(chars[reverseIndex])) {
                reverseIndex--;
            }
        }
        return String.valueOf(chars);
    }

    private static boolean isVowels(final char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
