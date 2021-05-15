package kit.sort;

import java.util.Arrays;

public class K번째수 {

    public static int[] solution(final int[] array, final int[][] commands) {
        final int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            final int[] copy = Arrays.copyOfRange(array, commands[i][0] -1, commands[i][1]);
            Arrays.sort(copy);
            answer[i] = copy[commands[i][2] - 1];
        }

        return answer;
    }

    public static void main(final String[] args) {
        final int[] array = {1, 5, 2, 6, 3, 7, 4};
        final int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        final int[] result = solution(array, commands);

        System.out.println(Arrays.toString(result));

    }

}
