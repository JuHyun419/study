package easy4;

import java.util.Arrays;

public class Determine_Whether_Matrix_Can_Be_Obtained_By_Rotation_1886 {

    public static boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (Arrays.deepEquals(mat, target)) {
                return true;
            }
            mat = arrayRotate(mat);
        }

        return false;
    }

    private static int[][] arrayRotate(int[][] array) {
        int[][] rotate = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            int col = array.length - i - 1;
            for (int j = 0; j < array.length; j++) {
                rotate[j][col] = array[i][j];
            }
        }
        return rotate;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 1}, {0, 1}};
        int[][] target = {{1, 1}, {1, 0}};
        System.out.println(findRotation(mat, target));
    }
}
