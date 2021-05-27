package easy3;

import java.util.Arrays;

public class Find_the_Difference_389 {

    public char findTheDifference(final String s, final String t) {
        final char[] sArr = s.toCharArray();
        final char[] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        for (int i = 0; i < s.length(); i++) {
            if (sArr[i] != tArr[i]) {
                return tArr[i];
            }
        }

        return tArr[tArr.length - 1];
    }

}
