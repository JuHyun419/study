package string;

public class Valid_Palindrome_125 {

    public static boolean isPalindrome2(String s) {
        s = removeNotCharacter(s).toLowerCase();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrome(String s) {
        s = removeNotCharacter(s).toLowerCase();
        System.out.println(s);
        int index = s.length() - 1;

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(index)) {
                return false;
            }
            index--;
        }
        return true;
    }

    private static String removeNotCharacter(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static void main(String[] args) {
        String str = "ab_-=+a";
        System.out.println(isPalindrome(str));
    }

}
