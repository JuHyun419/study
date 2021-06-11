package easy3;

import java.util.*;

public class Rank_Transform_of_an_Array_1331 {
    public static int[] arrayRankTransform(final int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(i, arr[i]);
        }

        map = sortByValue(map);
        int rank = 1;
        final int[] ranks = new int[arr.length];

        for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ranks[entry.getKey()] = rank;
            if (entry.getValue() > 1) {
                rank ++;
            }
        }
        return ranks;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
        final List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        final Map<K, V> result = new LinkedHashMap<>();
        for (final Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(final String[] args) {
        final int[] arr = {40, 10, 20, 30};
        final Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        map.put(0, 40);
        for (final Integer integer : map.keySet()) {
            if (map.containsValue(map.get(integer))) {
                System.out.println(map.containsValue(map.get(integer)));
            }

        }
        arrayRankTransform(arr);
    }
}
