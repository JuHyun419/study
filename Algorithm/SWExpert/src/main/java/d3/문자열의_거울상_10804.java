package d3;

import java.util.Scanner;

public class 문자열의_거울상_10804 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            String str = scanner.next();
            StringBuilder sb = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                if (str.charAt(i) == 'b') {
                    sb.append('d');
                } else if (str.charAt(i) == 'd') {
                    sb.append('b');
                } else if (str.charAt(i) == 'p') {
                    sb.append('q');
                } else {
                    sb.append('p');
                }
            }
            System.out.println("#" + tc + " " + sb);
        }
        scanner.close();
    }
}
