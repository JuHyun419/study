package easy1;

public class Running_1480 {

    // O(N^2)
    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;

            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            result[i] = sum;
        }

        return result;
    }

    // O(N)
    public static int[] runningSum2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] + nums[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 10, 1};
        int[] result = runningSum(nums);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
