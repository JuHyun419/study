package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 그룹_단어_체커 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int count = 0;

        for (int i = 0; i < N; i++) {
            String word = scanner.next();
            List<Character> list = new ArrayList<>();
            if (isGroupWord(word)) {
                count++;
            }
        }

        System.out.println(count);
        scanner.close();
    }

    private static boolean isGroupWord(String word) {
        List<Character> list = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if (list.contains(word.charAt(i))) {
                if (word.charAt(i - 1) != word.charAt(i)) {
                    return false;
                }
            }
            list.add(word.charAt(i));
        }
        return true;
    }

}
