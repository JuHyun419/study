package easy3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rank_Transform_of_an_Array2_1331 {
    public static int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) return new int[]{};
        int[] temp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);
        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(temp[0], rank);

        for (int i = 1; i < temp.length; i++) {
            if (isSameRank(temp[i - 1], temp[i])) continue;
            map.put(temp[i], ++rank);
        }

        int[] ranks = new int[arr.length];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = map.get(arr[i]);
        }
        return ranks;
    }

    private static boolean isSameRank(int integer1, int integer2) {
        return integer1 == integer2;
    }

    public static void main(String[] args) {
        int[] arr = {40, 10, 20, 30};
        int[] result = arrayRankTransform(arr);
        System.out.println(Arrays.toString(result));
    }
}
