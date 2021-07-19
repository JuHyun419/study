package d3;

import java.util.Scanner;

public class 의석이의_세로로_말해요_5356 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int maxLength = 0;
            String[] words = new String[5];

            for (int i = 0; i < words.length; i++) {
                final String word = scanner.next();
                maxLength = Math.max(maxLength, word.length());
                words[i] = word;
            }
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < maxLength; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i >= words[j].length()) {
                        continue;
                    }
                    sb.append(words[j].charAt(i));
                }
            }
            System.out.println("#" + tc + " " + sb.toString());
        }
        scanner.close();
    }
}
