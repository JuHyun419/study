package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minimum_Index_Sum_of_Two_Lists_599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        int min = 2001;

        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    map.put(list1[i], i + j);
                }
            }
        }

        for (String s : map.keySet()) {
            min = Math.min(min, map.get(s));
        }

        for (String s : map.keySet()) {
            if (min == map.get(s)) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }
}
