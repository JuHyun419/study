package kit.sort;

import java.util.Arrays;
import java.util.Collections;

public class H_Index {
    public int solution(final int[] citations) {
        int answer = 0;
        final int length = citations.length;
        Arrays.sort(citations);

        for (int i = 0; i < length; i++) {
            final int h = length - i;
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }

    private static int[] arrayReverse(final int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
