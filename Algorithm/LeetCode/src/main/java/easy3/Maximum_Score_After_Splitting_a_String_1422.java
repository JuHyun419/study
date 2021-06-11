package easy3;

public class Maximum_Score_After_Splitting_a_String_1422 {
    public static int maxScore(final String s) {
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            final String str1 = s.substring(0, i + 1);
            final String str2 = s.substring(i + 1);
            int leftZero = 0;
            int rightOne = 0;

            for (int j = 0; j < str1.length(); j++) leftZero += str1.charAt(j) == '0' ? 1 : 0;
            for (int j = 0; j < str2.length(); j++) rightOne += str2.charAt(j) == '1' ? 1 : 0;

            max = Math.max(max, (leftZero + rightOne));

        }
        return max;
    }

    public static void main(final String[] args) {
        final String str = "1111";
        maxScore(str);
    }

}
