package easy2;

import java.util.*;
import java.util.stream.Collectors;

// TODO:
public class Last_Stone_Weight_1046 {

    public static int lastStoneWeight2(int[] stones) {

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            queue.add(stone);
        }

        while (queue.size() > 1) {
            int max1 = queue.poll();
            int max2 = queue.poll();

            if (max1 - max2 != 0) {
                queue.add(max1 - max2);
            }
        }

        return queue.size() == 0 ? 0 : queue.poll();
    }

    public static int lastStoneWeight(int[] stones) {

        List<Integer> list = toList(stones);

        while (list.size() > 1) {
            Collections.sort(list);

            int max1 = list.get(list.size() - 1);
            int max2 = list.get(list.size() - 2);
            list.remove((Object) max1);
            list.remove((Object) max2);

            int newWeight = max1 - max2;
            if (!isSameWeights(max1, max2)) {
                list.add(newWeight);
            }
        }

        return list.size() == 0 ? 0 : list.get(0);
    }

    private static List<Integer> toList(final int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .map(Integer::new)
                .collect(Collectors.toList());
    }

    private static boolean isSameWeights(final int var1, final int var2) {
        return var1 == var2;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 4, 1, 8, 1};

    }
}
