package easy4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pascals_Triangle_118 {
    public static List<List<Integer>> generate(int numRows) {
        return twoDArrayToList(getPascalArray(numRows));
    }

    private static int[][] getPascalArray(int row) {
        int[][] arr = new int[row][];
        for (int i = 0; i < row; i++) {
            arr[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                arr[i][j] = (j == 0 || j == i)
                        ? 1
                        : arr[i-1][j-1] + arr[i-1][j];
            }
        }
        return arr;
    }

    private static List<List<Integer>> twoDArrayToList(int[][] twoDArray) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] array : twoDArray) {
            List<Integer> list = toList(array);
            result.add(list);
        }
        return result;
    }

    private static List<Integer> toList(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    // 2DArray to List of List (1)
    public <T> List<T> twoDArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    // 2DArray to List of List (2)
    public <T> List<T> twoDArrayToList2(T[][] twoDArray) {
        return Arrays.stream(twoDArray)  //'array' is two-dimensional
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    // String 2DArray to List of List
    public List<List<String>> string2DArrayToList(String[][] twoDArray) {
        return Arrays.stream(twoDArray)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(getPascalArray(5)));
    }
}
