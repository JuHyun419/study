package easy5;

// TODO:
public class Maximum_Subarray_53 {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 현재 값(nums[i]) 가 더 크면 이전까지의 합은 다 버린다.
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(sum, max);
        }

        return max;
    }
}
