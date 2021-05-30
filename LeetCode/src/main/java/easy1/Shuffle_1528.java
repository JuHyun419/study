package easy1;

public class Shuffle_1528 {

    // 다른 풀이 - O(N)
    public static String restoreString2(final String s, final int[] indices) {
        final char[] arr = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = s.charAt(i);
        }
        return String.valueOf(arr);
    }

    // 내 풀이ㅠㅠ - O(N^2)
    public static String restoreString(final String s, final int[] indices) {
        final int[] index = new int[indices.length];
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                if (i == indices[j]) {
                    index[i] = j;
                    break;
                }
            }
        }
        for (final int i : index) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(final String[] args) {
        final String s = "codeleet";
        final int[] indices = {4, 5, 6, 7, 0, 2, 1, 3};

        System.out.println(restoreString(s, indices));

    }
}
