package easy3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Move_Zeroes_283 {

    public static void moveZeroes(int[] nums) {
        final List<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                list.remove(i);
                list.add(0);
            }
        }
        nums = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes2(int[] nums) {
        final List<Integer> list = new ArrayList<>();
        int zeroCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            list.add(nums[i]);
        }

        for (int i = 0; i < zeroCount; i++) {
            list.add(0);
        }
        nums = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes3(final int[] nums) {
        final int n = nums.length;
        if (n == 1) return;
        int zeroes = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            } else {
                zeroes++;
            }
        }

        for (int k = n - zeroes; k < n; k++) {
            nums[k] = 0;
        }
    }

    public static void main(final String[] args) {
        final int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        moveZeroes2(nums);
    }

}
