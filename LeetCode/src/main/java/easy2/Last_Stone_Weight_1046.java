package easy2;

import java.util.*;
import java.util.stream.Collectors;

public class Last_Stone_Weight_1046 {

    // 3ms
    public static int lastStoneWeight(final int[] stones) {
        final List<Integer> list = Arrays.stream(stones).boxed().collect(Collectors.toList());

        while (list.size() > 1) {
            Collections.sort(list);
            final int max = list.get(list.size() - 1);
            final int secondMax = list.get(list.size() - 2);
            list.remove((Object) secondMax);
            list.remove((Object) max);
            if (max != secondMax) {
                list.add(max - secondMax);
            }
        }
        return list.size() == 0 ? 0 : list.get(0);
    }

    // 1ms
    public static int lastStoneWeight2(final int[] stones) {
        final Queue<Integer> queue = arrayToQueue(stones);

        while (queue.size() > 1) {
            final int max = queue.poll();
            final int secondMax = queue.poll();
            if (max != secondMax) {
                queue.add(max - secondMax);
            }
        }
        return queue.size() == 0 ? 0 : queue.poll();
    }

    private static PriorityQueue<Integer> arrayToQueue(final int[] array) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (final int arr : array) {
            queue.add(arr);
        }
        return queue;
    }

    public static void main(final String[] args) {
        final int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeight2(stones));
    }
}
