package easy5;

public class Check_if_One_String_Swap_Can_Make_Strings_Equals_1790 {
    public static boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        for (int i = 0; i < s1.length() - 1; i++) {
            String replace;
            for (int j = i + 1; j < s1.length(); j++) {
                replace = replaceCharAt(s1, i, j);
                if (replace.equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String replaceCharAt(String str, int pos1, int pos2) {
        char[] ch = str.toCharArray();
        char temp = ch[pos1];
        ch[pos1] = ch[pos2];
        ch[pos2] = temp;

        return String.valueOf(ch);
    }

    public static void main(String[] args) {
        String str = "bank";
        System.out.println(areAlmostEqual("bank", "kanb"));
    }
}
