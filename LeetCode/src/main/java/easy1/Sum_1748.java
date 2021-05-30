package easy1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sum_1748 {

    public int sumOfUnique(final int[] nums) {
        final List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        list.removeIf(i -> Collections.frequency(list, i) > 1);
        return list.stream().mapToInt(i -> i).sum();
    }

}
