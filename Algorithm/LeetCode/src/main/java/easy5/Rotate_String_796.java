package easy5;

// TODO: 풀이 확인
public class Rotate_String_796 {
    public static boolean rotateString(String s, String goal) {
        if (s.length() == 0 && goal.length() == 0) return true;
        if (s.length() == 0) return false;

        final String original = s;
        s = s.substring(1) + s.charAt(0);

        while (!s.equals(original)) {
            if (s.equals(goal)) {
                return true;
            }
            s = s.substring(1) + s.charAt(0);
        }
        return false;
    }

    public boolean rotateString2(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }

    public static void main(String[] args) {
        String s = "abcde";
        String goal = "cdeab";

        System.out.println(rotateString(s, goal));
    }
}
