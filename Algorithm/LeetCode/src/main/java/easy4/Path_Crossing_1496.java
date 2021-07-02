package easy4;

import java.util.ArrayList;
import java.util.List;

public class Path_Crossing_1496 {
    public static boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;

        List<String> list = new ArrayList<>();
        list.add(x + "," + y);

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'N') y++;
            else if (path.charAt(i) == 'S') y--;
            else if (path.charAt(i) == 'E') x++;
            else x--;

            if (list.contains(x + "," + y)) return true;
            list.add(x + "," + y);
        }
        return false;
    }

    public static void main(String[] args) {
        String path1 = "NES";
        String path2 = "NESWW";
        System.out.println(isPathCrossing(path1));
        System.out.println(isPathCrossing(path2));
    }
}
