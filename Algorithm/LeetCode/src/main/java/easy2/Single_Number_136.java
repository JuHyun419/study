package easy2;

import java.util.HashMap;
import java.util.Map;

public class Single_Number_136 {

    public static int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        singleNumber(arr);
    }
}
