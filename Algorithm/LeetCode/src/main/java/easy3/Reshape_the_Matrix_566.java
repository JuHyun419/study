package easy3;

// TODO: 다른 풀이 확인
public class Reshape_the_Matrix_566 {
    public static int[][] matrixReshape2(int[][] mat, int r, int c) {
        int[][] reshaped = new int[r][c];
        int row = mat.length; //getting  size of rows of mat;
        int column = mat[0].length; //getting size of column of mat;

        if ((row * column) != (r * c)) return mat; // returned original bcuz reshaping is illegal;

        int row_num = 0, column_num = 0; // used to travse mat

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                reshaped[row_num][column_num] = mat[i][j];
                column_num++;

                if (column_num == c) {
                    column_num = 0; // end of the column
                    row_num++;
                }
            }

        }
        return reshaped;
    }

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        final int m = mat.length;
        final int n = mat[0].length;

        if (isNotPossibleReshaped((m * n), (r * c))) {
            return mat;
        }

        int[][] reshaped = new int[r][c];
        int[] temp = new int[m * n];

        copyValues(mat, temp);
        getValues(reshaped, temp);

        return reshaped;
    }

    private static boolean isNotPossibleReshaped(final int width1, final int width2) {
        return (width1 < width2) || (width1 > width2);
    }

    private static void copyValues(final int[][] from, final int[] to) {
        int index = 0;

        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[i].length; j++) {
                to[index++] = from[i][j];
            }
        }
    }

    private static void getValues(final int[][] reshaped, final int[] temp) {
        int index = 0;

        for (int i = 0; i < reshaped.length; i++) {
            for (int j = 0; j < reshaped[i].length; j++) {
                reshaped[i][j] = temp[index++];
            }
        }
    }

}
