package easy2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_1200 {

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int min = getMinimum(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[i + 1]);
                result.add(list);
            }
        }
        print(result);
        return result;
    }

    private static int getMinimum(final int[] arr) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            //min = Math.min(min, arr[i + 1] - arr[i]);
            int temp = arr[i + 1] - arr[i];
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    private static void print(List<List<Integer>> list) {
        for (List<Integer> lists : list) {
            System.out.println(lists);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 10, 15};
        minimumAbsDifference(arr);
    }

}
