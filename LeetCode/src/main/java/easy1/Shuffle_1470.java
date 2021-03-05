package easy1;

public class Shuffle_1470 {

    public static int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int index = 0;

        for (int i = 0; i < n; i++) {
            result[index++] = nums[i];
            result[index++] = nums[i + n];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4, 3, 2, 1};
        int[] result = shuffle(nums, 4);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
