package medium1;

public class Custom_Sort_String_791 {
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : order.toCharArray()) {
            while (count[ch - 'a']-- > 0) {
                sb.append(ch);
            }
        }

        for (char ch : s.toCharArray()) {
            while (count[ch - 'a']-- > 0) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
