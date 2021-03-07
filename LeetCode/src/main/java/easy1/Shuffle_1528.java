package easy1;

// TODO: 20210307 일요일(X)
public class Shuffle_1528 {

    // 다른 풀이 - O(N)
    public static String restoreString2(String s, int[] indices) {
        char[] arr = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = s.charAt(i);
        }

        return String.valueOf(arr);
    }

    // 내 풀이ㅠㅠ - O(N^2)
    public static String restoreString(String s, int[] indices) {
        int[] index = new int[indices.length];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                if (i == indices[j]) {
                    index[i] = j;
                    break;
                }
            }
        }

        for (int i : index) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "codeleet";
        int[] indices = {4, 5, 6, 7, 0, 2, 1, 3};

        System.out.println(restoreString(s, indices));

    }
}
