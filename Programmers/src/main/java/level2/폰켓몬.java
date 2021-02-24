package level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class 폰켓몬 {

    public static int solution(int[] nums) {
       int halfLength = nums.length / 2;

       Set<Integer> set =
               Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));

       return Math.min(halfLength, set.size());

    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int[] nums2 = {2, 3, 2, 2, 3, 4};
        int[] nums3 = {2, 3, 3, 2, 3, 3, 5, 4};
        int[] nums4 = {2, 2, 3, 3, 2, 3};

        System.out.println(solution(nums));
        System.out.println(solution(nums2));
        System.out.println(solution(nums3));
        System.out.println(solution(nums4));
    }
}
