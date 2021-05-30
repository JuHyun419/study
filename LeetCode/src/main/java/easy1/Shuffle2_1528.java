package easy1;

public class Shuffle2_1528 {
    public static String restoreString(final String s, final int[] indices) {
        final int[] index = new int[indices.length];
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indices.length; i++) {
            index[indices[i]] = i;
        }

        for (int i = 0; i < index.length; i++) {
            sb.append(s.charAt(index[i]));
        }
        return sb.toString();
    }

    public static void main(final String[] args) {
        final int[] indices = {4, 5, 6, 7, 0, 2, 1, 3};
        System.out.println(restoreString("codeleet", indices));
    }

}
