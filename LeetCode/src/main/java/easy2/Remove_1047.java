package easy2;

//TODO: X(최적화)
public class Remove_1047 {

    public static String removeDuplicates(final String s) {
        final StringBuilder sb = new StringBuilder(s);
        while (true) {
            boolean flag = true;
            for (int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.delete(i, i + 2);
                    flag = false;
                    break;
                }
            }
            if (flag) break;
        }
        return sb.toString();
    }

    public static void main(final String[] args) {
        final String str = "azxxzy";
        System.out.println(removeDuplicates(str));
    }

}
