package easy1;

public class Matrix_1572 {

    public static int diagonalSum(int[][] mat) {
        int length = mat.length;
        if (length == 1) {
            return mat[0][0];
        }

        int x = 0;
        int y = length - 1;
        int result = 0;

        // ↘
        for (int i = 0; i < length; i++) {
            result += mat[i][i];
        }

        // ↙
        for (int i = 0; i < length; i++) {
            result += mat[x++][y--];
        }

        if (length % 2 != 0) {
            result -= mat[(length - 1) / 2][(length - 1) / 2];
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
