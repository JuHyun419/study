package easy3;

public class Monotonic_Array_896 {
    public boolean isMonotonic(final int[] nums) {
        int increasing = 0;
        int decreasing = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) increasing ++;
            if (nums[i] > nums[i + 1]) decreasing ++;
        }

        return increasing == 0 || decreasing == 0;
    }

}
