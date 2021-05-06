package easy2;

import java.util.Map;
import java.util.TreeMap;

public class Find_Lucky_Integer_in_an_Array_1394 {

    public static int findLucky(int[] arr) {
        Map<Integer, Integer> map = new TreeMap<>();
        int result = -1;

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer integer : map.keySet()) {
            int value = map.get(integer);
            if (integer == value) {
                result = value;
            }
        }
        return result;
    }

    public static int findLucky2(int[] arr) {
        int[] temp = new int[501];
        int max = -1;

        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (temp[arr[i]] == arr[i]) {
                max = Math.max(arr[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3};
        System.out.println(findLucky(arr));
    }
}
