package easy3;

public class Largest_Substring_Between_Two_Equal_Characters_1624 {

    public static int maxLengthBetweenEqualCharacters(final String s) {
        int longest = -1;
        for (int i = 0; i < s.length(); i++) {
            final int lastIndex = s.lastIndexOf(s.charAt(i));
            if (lastIndex != i && lastIndex != -1) {
                longest = Math.max(longest, lastIndex - i - 1);
            }
        }
        return longest;
    }

    public static void main(final String[] args) {
        final String s1 = "aa";
        final String s2 = "abca";
        final String s3 = "cbzxy";
        final String s4 = "cabbac";

        System.out.println(maxLengthBetweenEqualCharacters(s1));
        System.out.println(maxLengthBetweenEqualCharacters(s2));
        System.out.println(maxLengthBetweenEqualCharacters(s3));
        System.out.println(maxLengthBetweenEqualCharacters(s4));
    }

}
