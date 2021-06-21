package easy4;

// TODO:
public class Is_Subsequence_392 {

    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        if (s.length() > t.length()) return false;

        for (char c : t.toCharArray()) {
            if (s.charAt(0) == c) {
                s = s.substring(1);
            }

            if (s.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "abhgdc";
        System.out.println(isSubsequence(s, t));

    }
}
