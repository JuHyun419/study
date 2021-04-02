package easy2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class  Unique_1207 {

    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length == 2) return false;

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer i : map.keySet()) {
            int value = map.get(i);
            if (set.contains(value)) return false;
            set.add(value);
        }
        return true;
    }

}
