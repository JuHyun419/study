package easy4;

public class Flood_Fill2_733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;
        dfs(image, sr, sc, oldColor, newColor);

        return image;
    }

    public void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        // base condition
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != oldColor) {
            return;
        }

        image[i][j] = newColor;
        dfs(image, i + 1, j, oldColor, newColor);
        dfs(image, i - 1, j, oldColor, newColor);
        dfs(image, i, j + 1, oldColor, newColor);
        dfs(image, i, j - 1, oldColor, newColor);
    }
}
