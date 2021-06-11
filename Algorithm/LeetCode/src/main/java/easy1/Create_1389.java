package easy1;

import java.util.ArrayList;
import java.util.List;

public class Create_1389 {

    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

}
