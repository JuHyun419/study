package string;

public class Longest_Common_Prefix_14 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }

}
