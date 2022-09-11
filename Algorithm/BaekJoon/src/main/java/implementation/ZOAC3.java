package implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZOAC3 {

    private static final char[] keyboard1 = new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
    private static final char[] keyboard2 = new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
    private static final char[] keyboard3 = new char[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    private static Map<Character, Position> positions = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int time = 0;

        setKeyboardPosition();

        // 왼손, 오른손 first position
        char sl = scanner.next().charAt(0);
        char sr = scanner.next().charAt(0);

        // 왼손, 오른톤 (x,y) position
        int leftX = positions.get(sl).getX();
        int leftY = positions.get(sl).getY();
        int rightX = positions.get(sr).getX();
        int rightY = positions.get(sr).getY();

        String word = scanner.next();

        for (char ch : word.toCharArray()) {
            // 1. 자음인지 모음인지 찾기
            if (isVowel(ch)) {
                final int afterX = positions.get(ch).getX();
                final int afterY = positions.get(ch).getY();

                time += Math.abs(afterX - rightX) + Math.abs(afterY - rightY) + 1;
                rightX = afterX;
                rightY = afterY;
            } else {
                final int afterX = positions.get(ch).getX();
                final int afterY = positions.get(ch).getY();

                time += Math.abs(afterX - leftX) + Math.abs(afterY - leftY) + 1;
                leftX = afterX;
                leftY = afterY;
            } 
        }

        System.out.println(time);

        scanner.close();
    }

    /* 자음 모음 확인 */
    private static boolean isVowel(char ch) {
        final int x = positions.get(ch).getX();
        final int y = positions.get(ch).getY();

        if (x <= 1 && y <= 4) {
            return false;
        }

        if (x == 2 && y <= 3) {
            return false;
        }
        return true;
    }

    /* 키보드의 자음, 모음 별 x, y 값 설정 */
    private static void setKeyboardPosition() {
        int x = 0;
        int y = 0;

        for (char ch : keyboard1) {
            positions.put(ch, new Position(x, y));
            y++;
        }

        x = 1;
        y = 0;

        for (char ch : keyboard2) {
            positions.put(ch, new Position(x, y));
            y++;
        }

        x = 2;
        y = 0;

        for (char ch : keyboard3) {
            positions.put(ch, new Position(x, y));
            y++;
        }
    }

}

class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
