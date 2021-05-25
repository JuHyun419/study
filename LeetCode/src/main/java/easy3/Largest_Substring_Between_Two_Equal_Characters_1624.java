package easy3;

// TODO:
public class Largest_Substring_Between_Two_Equal_Characters_1624 {

    // O(n^2)
    public static int maxLengthBetweenEqualCharacters(final String s) {
        int longest = -1;

        for (int i = 0; i < s.length(); i++) {
            final char leftChar = s.charAt(i);

            for (int j = i + 1; j < s.length(); j++) {
                final char rightChar = s.charAt(j);
                if (leftChar == rightChar) {
                    final int length = (j - i) - 1;
                    longest = Math.max(longest, length);
                }
            }
        }
        return longest;
    }

    // O(n)
    public static int maxLengthBetweenEqualCharacters2(final String s) {
        int longest = -1;

        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            final int firstIndex = s.indexOf(ch);
            final int lastIndex = s.lastIndexOf(ch);

            if (lastIndex - firstIndex > 0) {
                longest = Math.max(longest, (lastIndex - firstIndex) - 1);
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
