package easy4;

// TODO:
public class Flood_Fill_733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor)
            return image;
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    public void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        int row = image.length;
        int col = image[0].length;

        // base condition
        if (r < 0 || r >= row || c < 0 || c >= col || image[r][c] != oldColor) {
            return;
        }

        image[r][c] = newColor;
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
    }
}
